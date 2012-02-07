package irgen;
import ir.*;
import ast.*;
import symbol.*;
import typechk.*;

public class IrgenVisitor implements TransVI {

    private Table symTable;
    private TypeVisitor typeChecker;
    private NAME cWordSize; // a symbolic name
    private ClassRec currClass;
    private MethodRec currMethod;
    private int maxArgCnt;

    public IrgenVisitor(Table tab, TypeVisitor tv) {
        symTable = tab;
        typeChecker = tv;
        cWordSize = new NAME("wSZ");
        currClass = null;
        currMethod = null;
        maxArgCnt = 0;
    }

    public PROG visit(Program n) throws Exception { 
        FUNClist funcs = n.cl.accept(this);
        return new PROG(funcs);
    }
    
    // Declarations
    public FUNClist visit(ClassDeclList n) throws Exception { 
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.addAll(n.elementAt(i).accept(this)); 
        return funcs;
    }

    public FUNClist visit(ClassDecl n) throws Exception { 
        currClass = symTable.getClass(n.cid);
        FUNClist funcs = n.ml.accept(this);
        currClass = null;
        return funcs;
    }

    public FUNClist visit(MethodDeclList n) throws Exception {
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.add(n.elementAt(i).accept(this));
        return funcs;
    }

    public FUNC visit(MethodDecl n) throws Exception {
        currMethod = currClass.getMethod(n.mid);
        if (currMethod == null)
            System.out.println("Warning: currMethod is null for method: "
                               + n.mid.s);
        String label = symTable.uniqueMethodName(currClass, n.mid);
        if (n.mid.s.equals("main"))
            label = "main";
        STMTlist vars = n.vl.accept(this);
        vars.add(n.sl.accept(this));
        int currmethodlocals = currMethod.localCnt();
        currMethod = null;
        int mAC = maxArgCnt;
        maxArgCnt = 0;
        return new FUNC(label, currmethodlocals, mAC, vars);
    }

    public STMTlist visit(VarDeclList n) throws Exception {
        STMTlist stmts = new STMTlist();
        for (int i = 0; i < n.size(); i++) {
            STMT tmp = n.elementAt(i).accept(this);
            if (tmp != null)
                stmts.add(tmp);
        }
        return stmts;
    }

    public STMT visit(VarDecl n) throws Exception {
        if (n.e != null) {
            EXP dst = n.var.accept(this);
            EXP src = n.e.accept(this);
            return new MOVE(dst, src);
        }
        else
            return null;
    }

    // Types --- no need to process these nodes

    // Statements
    public STMTlist visit(StmtList n) throws Exception {
        STMTlist stmts = new STMTlist();
        for (int i = 0; i < n.size(); i++)
            stmts.add(n.elementAt(i).accept(this));
        return stmts;
    }

    public STMT visit(Block n) throws Exception { 
        return n.sl.accept(this);
    }

    public STMT visit(Assign n) throws Exception {
        EXP lhs = n.lhs.accept(this); 
        EXP rhs = n.rhs.accept(this); 
        return new MOVE(lhs, rhs);
    }

    public STMT visit(CallStmt n) throws Exception {
        int argCnt = n.args.size();
        // Call statement's arg count + 1 for passing access link
        if (maxArgCnt <= argCnt)
            maxArgCnt = argCnt + 1;
        VarRec local_var;
        ClassRec obj_class = null;
        if (n.obj instanceof Id) {
            local_var = currMethod.getLocal((Id) n.obj);
            ObjType var_obj = (ObjType) local_var.type();
            Id obj_id = var_obj.cid;
            obj_class = symTable.getClass(obj_id);
        }
        else if (n.obj instanceof This)
            obj_class = currClass;
        NAME label = new NAME(symTable.uniqueMethodName(obj_class, n.mid));
        EXPlist el = new EXPlist();
        el.add(n.obj.accept(this));
        el.addAll(n.args.accept(this));
        return new CALLST(label, el);
    }
    
    public STMT visit(If n) throws Exception { 
        STMTlist if_block = new STMTlist();
        NAME false_name = new NAME();
        if (n.e instanceof Relop) {
            EXP e1 = ((Relop) n.e).e1.accept(this);
            EXP e2 = ((Relop) n.e).e2.accept(this);
            int op = ((Relop) n.e).op;
            if_block.add(new CJUMP(invop(op), e1, e2, false_name));
        }
        else {
            EXP if_expr = n.e.accept(this);
            if_block.add(new CJUMP(CJUMP.EQ, if_expr, new CONST(0), 
                                   false_name));
        }
        // Execute the s1 when true.
        if_block.add(n.s1.accept(this));
        // Is there an else statement?
        // If not, we simply need a label to jump to when the expression is
        // false.
        if (n.s2 == null) {
            if_block.add(new LABEL(false_name));
        }
        // When there is an else statement, we need to create an additional
        // label to get past the else statement when the expression is true,
        // and we insert the else statement between the jump and the new label.
        else {
            NAME done_name = new NAME();
            JUMP j = new JUMP(done_name);
            if_block.add(j);
            if_block.add(new LABEL(false_name));
            STMT s2 = n.s2.accept(this);
            if_block.add(s2);
            if_block.add(new LABEL(done_name));
        }
        return if_block;
    }

    public STMT visit(While n) throws Exception { 
        STMTlist while_block = new STMTlist();
        NAME start_name = new NAME();
        while_block.add(new LABEL(start_name));
        NAME done_name = new NAME();
        EXP while_expr = n.e.accept(this);
        CJUMP cj = new CJUMP(CJUMP.EQ, while_expr, new CONST(0), done_name);
        while_block.add(cj);
        STMT s = n.s.accept(this);
        while_block.add(s);
        JUMP j = new JUMP(start_name);
        while_block.add(j);
        while_block.add(new LABEL(done_name));
        return while_block;
    }

    public STMT visit(Print n) throws Exception {
        NAME print = new NAME("print");
        EXPlist print_expr = new EXPlist();
        if (n.e != null)
            print_expr.add(n.e.accept(this));
        return new CALLST(print, print_expr);
    }

    public STMT visit(Return n) throws Exception {
        return new RETURN(n.e.accept(this));
    }

    // Expressions
    public EXPlist visit(ExpList n) throws Exception {
        EXPlist exprs = new EXPlist();
        for (int i = 0; i < n.size(); i++)
            exprs.add(n.elementAt(i).accept(this));
        return exprs;
    }

    public EXP visit(Binop n) throws Exception {
        EXP e1 = n.e1.accept(this);
        EXP e2 = n.e2.accept(this);
        /*if (n.op == Binop.AND) {
            STMTlist and_block = new STMTlist();
            TEMP result = new TEMP();
            MOVE set_false = new MOVE(result, new CONST(0));
            and_block.add(set_false);
            NAME done_name = new NAME();
            CJUMP cj_lhs = new CJUMP(0, e1, new CONST(0), done_name);
            and_block.add(cj_lhs);
            CJUMP cj_rhs = new CJUMP(0, e2, new CONST(0), done_name);
            and_block.add(cj_rhs);
            MOVE set_true = new MOVE(result, new CONST(1));
            and_block.add(set_true);
            and_block.add(new LABEL(done_name));
            return new ESEQ(and_block, result);
        } else if (n.op == Binop.OR) {
            STMTlist or_block = new STMTlist();
            TEMP result = new TEMP();
            MOVE set_true = new MOVE(result, new CONST(1));
            or_block.add(set_true);
            NAME done_name = new NAME();
            CJUMP cj_lhs = new CJUMP(0, e1, new CONST(1), done_name);
            or_block.add(cj_lhs);
            CJUMP cj_rhs = new CJUMP(0, e2, new CONST(1), done_name);
            or_block.add(cj_rhs);
            MOVE set_false = new MOVE(result, new CONST(0));
            or_block.add(set_false);
            or_block.add(new LABEL(done_name));
            return new ESEQ(or_block, result);
            } else*/
        return new BINOP(n.op, e1, e2);
    }

    public EXP visit(Relop n) throws Exception {
        EXP e1 = n.e1.accept(this);
        EXP e2 = n.e2.accept(this);
        STMTlist relop_expr = new STMTlist();
        TEMP result = new TEMP();
        MOVE set_true = new MOVE(result, new CONST(1));
        relop_expr.add(set_true);
        NAME done_name = new NAME();
        relop_expr.add(new CJUMP(n.op, e1, e2, done_name));
        relop_expr.add(new MOVE(result, new CONST(0)));
        relop_expr.add(new LABEL(done_name));
        return new ESEQ(relop_expr, result);
    }

    public int invop(int op) {
        switch(op) {
        case CJUMP.EQ: return CJUMP.NE;
        case CJUMP.NE: return CJUMP.EQ;
        case CJUMP.LT: return CJUMP.GE;
        case CJUMP.LE: return CJUMP.GT;
        case CJUMP.GT: return CJUMP.LE;
        case CJUMP.GE: return CJUMP.LT;
        default: return -1;
        }
    }

    public EXP visit(Unop n) throws Exception {
        if (n.e instanceof BoolVal) {
            int bool_num = ((BoolVal) n.e).b ? 1 : 0;
            return new CONST(1 - bool_num);
        } else
            return new BINOP(BINOP.SUB, new CONST(1), n.e.accept(this));
    }

    public EXP visit(ArrayElm n) throws Exception {
        EXP offset = null;
        if (n.idx instanceof IntVal) {
            int offset_number = ((IntVal) n.idx).i;
            if (offset_number == 0)
                offset = cWordSize;
            else
                offset = new BINOP(BINOP.MUL, new CONST(offset_number+1), 
                                   cWordSize);
        } else {
            BINOP fffuuu = new BINOP(BINOP.ADD, n.idx.accept(this), 
                                     new CONST(1));
            offset = new BINOP(BINOP.MUL, fffuuu, cWordSize);
        }
        Id array_id = (Id) n.array;
        EXP array_exp = new VAR(0);
        VarRec array_var = currMethod.getLocal(array_id);
        if (array_var != null)
            array_exp = new VAR(array_var.idx());
        else {
            array_var = currMethod.getParam(array_id);
            if (array_var != null)
                array_exp = new PARAM(array_var.idx());
            else {
                array_var = currClass.getClassVar(array_id);
                if (array_var != null)
                    array_exp = new FIELD(new PARAM(0), array_var.idx()-1);
            }
        }
        return new MEM(new BINOP(BINOP.ADD, array_exp, offset));
    }

    public EXP visit(ArrayLen n) throws Exception {
        Id array_id = (Id) n.array;
        int idx = 0;
        VarRec vr = currMethod.getLocal(array_id);
        if (vr != null)
            idx = vr.idx();
        else {
            vr = currMethod.getParam(array_id);
            if (vr != null)
                idx = vr.idx();
            else {
                vr = currClass.getClassVar(array_id);
                if (vr != null)
                    idx = vr.idx();
            }
        }
        return new MEM(new VAR(idx));
    }

    public EXP visit(Field n) throws Exception {
        ClassRec obj_class;
        if (n.obj instanceof Id) {
            VarRec obj_var = currMethod.getLocal((Id) n.obj);
            ObjType obj_type = (ObjType) obj_var.type();
            Id obj_id = obj_type.cid;
            obj_class = symTable.getClass(obj_id);
        } else
            obj_class = currClass;
        VarRec v = obj_class.getClassVar(n.var);
        if (v == null)
            v = obj_class.parent().getClassVar(n.var);
        return new FIELD(n.obj.accept(this), v.idx() - 1);
    }

    public EXP visit(Call n) throws Exception {
        int argCnt = n.args.size();
        // Call's arg count + 1 for passing access link
        if (maxArgCnt <= argCnt)
            maxArgCnt = argCnt + 1;
        VarRec local_var;
        ClassRec obj_class = null;
        if (n.obj instanceof Id) {
            local_var = currMethod.getLocal((Id) n.obj);
            ObjType var_obj = (ObjType) local_var.type();
            Id obj_id = var_obj.cid;
            obj_class = symTable.getClass(obj_id);
        }
        else if (n.obj instanceof This)
            obj_class = currClass;
        NAME label = new NAME(symTable.uniqueMethodName(obj_class, n.mid));
        EXPlist el = new EXPlist();
        el.add(n.obj.accept(this));
        el.addAll(n.args.accept(this));
        return new CALL(label, el);
    }

    public EXP visit(NewArray n) throws Exception {
        STMTlist array_init = new STMTlist();
        TEMP array_location = new TEMP();
        NAME malloc = new NAME("malloc");
        // Create a call to malloc with the total size of the array
        EXPlist mc_list = new EXPlist();
        mc_list.add(new BINOP(BINOP.MUL, new CONST(n.size+1), cWordSize));
        MOVE malloc_call = new MOVE(array_location, new CALL(malloc, mc_list));
        array_init.add(malloc_call);
        // Write the length into the first element of the array
        MOVE set_size = new MOVE(new MEM(array_location), new CONST(n.size));
        array_init.add(set_size);
        TEMP iterator = new TEMP();
        BINOP get_last = new BINOP(BINOP.MUL, new CONST(n.size), cWordSize);
        BINOP last_elem = new BINOP(BINOP.ADD, array_location, get_last);
        // Create an iterator, pointing to the end of the array
        array_init.add(new MOVE(iterator, last_elem));
        NAME zero_loop = new NAME();
        // Start of the loop to go through the array
        array_init.add(new LABEL(zero_loop));
        // Store a zero at the address the iterator is pointing at
        array_init.add(new MOVE(new MEM(iterator), new CONST(0)));
        BINOP decrement = new BINOP(BINOP.SUB, iterator, cWordSize);
        // Decrement the pointer to the previous element of the array
        array_init.add(new MOVE(iterator, decrement));
        // Check if we're at the beginning, if not jump through the loop again
        array_init.add(new CJUMP(CJUMP.GT, iterator, array_location, zero_loop));
        return new ESEQ(array_init, array_location);
    }

    public EXP visit(NewObj n) throws Exception {
        // get the object's ClassRec from symbol table
        ClassRec tmp = currClass;
        currClass = symTable.getClass(n.cid);
        ExpList inits = new ExpList();
        int obj_vars = currClass.varCnt();
        ClassRec currParent = currClass.parent();
        if (currParent != null) {
            obj_vars += currParent.varCnt();
            for (int i = 0; i < currParent.varCnt(); i++)
                inits.add(currParent.getClassVarAt(i).init());
        }
        for (int i = 0; i < currClass.varCnt(); i++)
            inits.add(currClass.getClassVarAt(i).init());
        STMTlist stmts = new STMTlist();
        TEMP obj = new TEMP();
        EXPlist malloc_exprs = new EXPlist();
        if (obj_vars > 1)
            malloc_exprs.add(new BINOP(BINOP.MUL, new CONST(obj_vars), cWordSize));
        else
            malloc_exprs.add(cWordSize);
        stmts.add(new MOVE(obj, (new CALL(new NAME("malloc"), malloc_exprs))));
        for (int i = 0; i < inits.size(); i++) {
            EXP dst;
            EXP src;
            if (inits.elementAt(i) != null) {
                src = inits.elementAt(i).accept(this);
                if (src instanceof FIELD)
                    ((FIELD) src).obj = obj;
                else if (src instanceof BINOP)
                    ((FIELD) ((BINOP) src).left).obj = obj;
            }
            else
                src = new CONST(0);
            if (i == 0)
                dst = obj;
            else if (i == 1)
                dst = new BINOP(BINOP.ADD, obj, cWordSize);
            else
                dst = new BINOP(BINOP.ADD, obj, 
                                new BINOP(BINOP.MUL, new CONST(i), cWordSize));
            stmts.add(new MOVE(new MEM(dst), src));
        }
        currClass = tmp;
        return new ESEQ(stmts, obj);
    }

    public EXP visit(Id n) throws Exception {
        VarRec v = currMethod.getLocal(n);
        if (v != null)
            return new VAR(v.idx());
        else {
            v = currMethod.getParam(n);
            if (v != null)
                return new PARAM(v.idx());
            else {
                v = currClass.getClassVar(n);
                if (v != null)
                    return new FIELD(new PARAM(0), v.idx() - 1);
                else
                    return new VAR(0);
            }
        }
    }

    public EXP visit(This n) {
        return new PARAM(0);
    }

    // Base values
    public EXP visit(IntVal n) {
        return new CONST(n.i);
    }

    public EXP visit(FloatVal n) {
        return new FLOAT(n.f);
    }

    public EXP visit(BoolVal n) {
        return new CONST(n.b ? 1 : 0);
    }

    public EXP visit(StrVal n) {
        return new STRING(n.s);
    }
}