package codegen;

import ir.*;

public class CodegenVisitor implements CodeVI {
    private final int maxTemps = 512;
    private final int maxStrs = 32;
    private final int wordSize = Sparc.WORDSIZE;
    private Reg[] tempReg = new Reg[maxTemps];     // hold temp's reg assignment
    private String[] strBuf = new String[maxStrs]; // hold string literals
    private int tempCnt = 0;
    private int strCnt = 2;                        // two pre-defined strings

    public CodegenVisitor() {
        strBuf[0] = "L$0:\t.asciz \"Array bounds check error\\n\""; 
        strBuf[1] = "L$1:\t.asciz \"%d\\n\"";
    }

    public void visit(PROG p) throws Exception {
        p.funcs.accept(this);
        for (int i = 1; i < strCnt; i++)
            Sparc.emitString(strBuf[i]);
        Sparc.printStats();
    }

    public void visit(FUNC f) throws Exception {
        int framesize = Sparc.frameSize(f.varCnt, f.argCnt);
        if (f.label.equals("main"))
            Sparc.emit0(".global main"); 
        Sparc.emit0(".align 4");
        Sparc.emitString(f.label + ":");
        Sparc.emitNonInst("!locals=" + f.varCnt + 
                          ", max_args=" + f.argCnt + "\n");
        Sparc.emit0("save %sp,-" + framesize + ",%sp");
        f.stmts.accept(this);
        Sparc.freeAllRegs();
        // ...?
        Sparc.emit0("ret");
        Sparc.emit0("restore");
        Sparc.emitNonInst("\n");
    }

    public void visit(FUNClist fl) throws Exception {
        for (int i=0; i<fl.size(); i++)
            fl.elementAt(i).accept(this);
    }

    //public void visit(STMT t) throws Exception {}
    public void visit(STMTlist sl) throws Exception {
        for (int i=0; i<sl.size(); i++) {
            STMT s = sl.elementAt(i);
            Sparc.emitNonInst("!");
            s.dump();
            s.accept(this);
        }
    }

    public void visit(MOVE s) throws Exception {
        Operand src = s.src.accept(this);
        Reg r = Sparc.getReg();
        //toReg(src, r);
        // generate code for s.src and bring result to a reg r
        // I DON'T KNOW WHAT I'M DOING
        if (s.dst instanceof TEMP) {
            Operand dst = s.dst.accept(this);
            Sparc.emit2("mov", src, dst);
            // freeOperand(src);
        } else if (s.dst instanceof MEM) {
            Operand dst = ((MEM) s.dst).exp.accept(this);
            Sparc.emitStore(r, dst);
            // ... WHAT GOES HERE???
        } else {
            Operand dst = s.dst.accept(this);
            if (dst == null)
                throw new Exception("WHAT IS HAPPENING");
            Sparc.emitStore(r, dst);
        }
        Sparc.freeReg(r);
    }

    public void visit(JUMP s) throws Exception {
        if (s.target instanceof NAME) {
            Sparc.emit0("ba " + ((NAME) s.target).id);
            Sparc.emit0("nop");
        } else
            throw new CodegenException("JUMP target not a NAME");
    }
    
    public void visit(CJUMP s) throws Exception {
        // bring operands to regs r1 and r2
        Operand left = s.left.accept(this);
        Reg r1 = Sparc.getReg();
        toReg(left, r1);
        Reg r2 = Sparc.getReg();
        Operand right = s.right.accept(this);
        toReg(right, r2);
        Sparc.emit2("cmp", r1, r2);
        String label = s.target.id;
        Sparc.emit0(relopCode(s.op) + " " + label);
        Sparc.emit0("nop");
        Sparc.freeReg(r1);
        Sparc.freeReg(r2);
    }

    private String relopCode(int op) {
        switch (op) {
        case CJUMP.EQ: return "be";
        case CJUMP.NE: return "bne";
        case CJUMP.LT: return "bl";
        case CJUMP.LE: return "ble";
        case CJUMP.GT: return "bg";
        case CJUMP.GE: return "bge";
        }
        return "NOOB";
    }

    public void visit(LABEL t) throws Exception {
        Sparc.emitNonInst(t.lab+":\n");
    }

    public void visit(CALLST s) throws Exception {
        String fname = s.func.id;
        if (fname.equals("print"))
            genPrint(s.args);
        else if (fname.equals("error"))
            // genError();
            nop();
        else
            genCall(fname, s.args);
    }

    void genCall(String label, EXPlist args) throws Exception {
        for (int i=0; i<args.size(); i++) {
            // load arg_i to a reg, then store it to
            // the i-th param slot in stack frame
        }
        Sparc.emit0("call " + label);
        Sparc.emit0("nop");
    }

    void genReturn(RETURN s) throws Exception {
        if (s.exp != null)
            // generate code for s.exp and bring result to RegRV
            nop();
        Sparc.emit0("ret");
        Sparc.emit0("restore");
    }

    void nop() {
        return;
    }

    void genPrint(EXPlist args) throws Exception {
        if (args != null && args.size() == 1
            && (args.elementAt(0) instanceof STRING)) { 
            // print a string
            String str = ((STRING) args.elementAt(0)).s;
            String lab = "L$" + strCnt;
            strBuf[strCnt++] = lab + ":\t.asciz \"" + str + "\\n\"";
            Sparc.getReg(Sparc.regO0);
            Sparc.emit0("sethi %hi(" + lab + "),%o0");
            Sparc.emit0("or %o0, %lo(" + lab + "),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO0);
        } else if (args != null && args.size() == 1
                   && (args.elementAt(0) instanceof CONST)) {
            // print an integer: pass two arguments to printf, 
            // one control string at L$1 and one integer
            int val = ((CONST) args.elementAt(0)).val;
            Sparc.getReg(Sparc.regO1);
            Immed m = new Immed(val);
            toReg(m, Sparc.regO1);
            Sparc.getReg(Sparc.regO0);
            Sparc.emit0("sethi %hi(L$1),%o0");
            Sparc.emit0("or %o0, %lo(L$1),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO0);
            Sparc.freeReg(Sparc.regO1);
        } else if (args != null && args.size() == 1
                   && (args.elementAt(0) instanceof VAR)) {
            Operand addr = args.elementAt(0).accept(this);
            Sparc.getReg(Sparc.regO1);
            Sparc.emitLoad(addr, Sparc.regO1);
            Sparc.getReg(Sparc.regO0);
            Sparc.emit0("sethi %hi(L$1),%o0");
            Sparc.emit0("or %o0, %lo(L$1),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO0);
            Sparc.freeReg(Sparc.regO1);
        } else {
            String lab = "L$" + strCnt;
            strBuf[strCnt++] = lab + ":\t.asciz \"\\n\"";
            Sparc.getReg(Sparc.regO0);
            Sparc.emit0("sethi %hi(" + lab + "),%o0");
            Sparc.emit0("or %o0, %lo(" + lab + "),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO0);
        }
    }

    public void visit(RETURN t) throws Exception {
        Sparc.emit0("ret");
        Sparc.emit0("return");
    }

    //public Operand visit(EXP t) throws Exception {}
    public Operand visit(EXPlist t) throws Exception { 
        throw new Exception("EXPLIST");
    }
    
    public Operand visit(ESEQ t) throws Exception { throw new Exception("ESEQ"); }
    public Operand visit(MEM t) throws Exception { throw new Exception("MEM"); }
    public Operand visit(CALL t) throws Exception { throw new Exception("CALL"); }

    public Operand visit(BINOP e) throws Exception {
        // Generate code for the two operands, and bring them to 
        // registers r1 and r2; allocate a register r3 for result ...
        Operand left = e.left.accept(this);
        Reg r1 = Sparc.getReg();
        toReg(left, r1);
        Operand right = e.right.accept(this);
        Reg r2 = Sparc.getReg();
        toReg(right, r2);
        if (e.op == BINOP.DIV)
            Sparc.emit3("wr", Sparc.regG0, Sparc.regG0, Sparc.regY);
        Sparc.emit3(binopCode(e.op), r1, r2, r1);
        Sparc.freeReg(r1);
        Sparc.freeReg(r2);
        return r1;
    }

    private String binopCode(int op) {
        switch (op) {
        case BINOP.ADD: return "add";
        case BINOP.SUB: return "sub";
        case BINOP.MUL: return "smul";
        case BINOP.DIV: return "sdiv";
        case BINOP.AND: return "and";
        case BINOP.OR:  return "or";
        }
        return "IDIOT";
    }

    public Operand visit(NAME t) throws Exception { throw new Exception("NAME"); }
    
    public Operand visit(TEMP t) throws Exception {
        if (tempReg[t.num] == null) {
            Reg r = Sparc.getReg();
            tempReg[t.num] = r;
            r.status = Reg.TEMP;
            Sparc.emitNonInst("!>> Temp t" + t.num + " assigned to reg " +
                              r.name + "\n");
        }
        return tempReg[t.num];
    }

    public Operand visit(FIELD t) throws Exception { throw new Exception("FIELD"); }
    public Operand visit(PARAM t) throws Exception { throw new Exception("PARAM"); }
    
    public Operand visit(VAR t) throws Exception {
        return new RegOff(Sparc.regFP, - t.idx * wordSize);
    }

    public Operand visit(CONST t) throws Exception {
        return new Immed(t.val);
    }
    
    public Operand visit(FLOAT t) throws Exception { throw new Exception("FLOAT"); }
    public Operand visit(STRING t) throws Exception { throw new Exception("STRING"); }

    // load operand t to reg r
    void toReg(Operand t, Reg r) throws Exception {
        if (t instanceof Immed) {
            if (((Immed) t).is13b)
                Sparc.emit2("mov", t, r);
            else
                Sparc.emit2("set", t, r);
        } else if (t instanceof RegOff) {
            Sparc.emitLoad(t, r);
            // ...
        } else { // t instanceof Reg
            Sparc.emit2("mov", t, r);
        }
    }
}