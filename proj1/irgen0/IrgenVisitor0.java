package irgen0;
import ir.*;
import ast.*;

public class IrgenVisitor0 implements TransVI {

    private NAME cWordSize; // a symbolic name

    public IrgenVisitor0() { cWordSize = new NAME("wSZ"); }
    
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
        FUNClist funcs = n.ml.accept(this);
        return funcs;
    }

    public FUNClist visit(MethodDeclList n) throws Exception {
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.add(n.elementAt(i).accept(this));
        return funcs;
    }

    public FUNC visit(MethodDecl n) throws Exception {
        String label = n.mid.s;
        STMTlist vars = n.vl.accept(this);
        vars.add(n.sl.accept(this));
        return new FUNC(label, 0, 0, vars);
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
        NAME label = new NAME(n.mid.s);
        EXPlist args = n.args.accept(this);
        return new CALLST(label, args);
    }
    
    public STMT visit(If n) throws Exception { 
        STMTlist if_block = new STMTlist();
        NAME false_name = new NAME();
        EXP if_expr = n.e.accept(this);
        // If false, go to label 'false name'
        CJUMP cj = new CJUMP(CJUMP.EQ, if_expr, new CONST(0), false_name);
        if_block.add(cj);
        // Execute the s1 when true.
        STMT s1 = n.s1.accept(this);
        if_block.add(s1);
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

    public EXP visit(Unop n) throws Exception {
        return new BINOP(BINOP.SUB, new CONST(1), n.e.accept(this));
    }

    public EXP visit(ArrayElm n) throws Exception {
        EXP index = n.idx.accept(this);
        BINOP add_one = new BINOP(BINOP.ADD, index, new CONST(1));
        BINOP sizeof = new BINOP(BINOP.MUL, add_one, cWordSize);
        Id address = (Id) n.array;
        NAME array_name = new NAME(address.s);
        return new MEM(new BINOP(BINOP.ADD, array_name, sizeof));
    }

    public EXP visit(ArrayLen n) throws Exception {
        Id address = (Id) n.array;
        return new MEM(new NAME(address.s));
    }

    public EXP visit(Field n) throws Exception {
        return new NAME(n.var.s);
    }

    public EXP visit(Call n) throws Exception {
        return new CALL(new NAME(n.mid.s), n.args.accept(this));
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
        EXPlist new_obj_exprs = new EXPlist();
        new_obj_exprs.add(new NAME(n.cid.s+"_obj_size"));
        return new CALL(new NAME("malloc"), new_obj_exprs);
    }

    public EXP visit(Id n) throws Exception {
        return new NAME(n.s);
    }

    public EXP visit(This n) {
        return new NAME("this");
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
