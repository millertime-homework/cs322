package interp;

import ir.*;

public class InterpVisitor implements IntVI {
    private final int maxTemps = 512;
    private final int maxStack = 2048;
    private final int maxHeap = 4096;
    private final int wordSize = 1;
    private final int STATUS_DEFAULT = -1;
    private final int STATUS_RETURN = -2;
    private int[] temps = new int[maxTemps];
    private int[] stack = new int[maxStack];
    private int[] heap = new int[maxHeap];

    // stack starts from high index; reserve 1 slot
    private int sp = maxStack - 2; 

    // for main class's (PARAM 0)
    private int fp = maxStack - 2;

    // heap also starts from high index 
    private int hp = maxHeap - 1; 

    // special storage for return value
    private int retVal = 0; 

    // keeping a copy of input program's funcs
    private FUNClist funcs = null; 

    // keeping a copy of current statement list
    private STMTlist stmts = null; 

    public InterpVisitor() {}

    public int visit(STMTlist sl) throws Exception {
        int ret = STATUS_DEFAULT;
        int i = 0;
        while (i < sl.size()) {
            int next = ((STMT) sl.elementAt(i)).accept(this);
            if (next == STATUS_RETURN) {
                ret = STATUS_RETURN;
                break;
            }
            i = (next >= 0) ? next : i+1;
        }
        return ret;
    }
    
    public void visit(PROG p) throws Exception {
        funcs = p.funcs;
        FUNC mf = findFunc("main");
        mf.accept(this);
    }
    
    public void visit(FUNC f) throws Exception {
        stmts = f.stmts;
        sp = sp - f.varCnt - f.argCnt - 1;
        f.stmts.accept(this);
        sp = sp + f.varCnt + f.argCnt + 1;
    }

    public int visit(MOVE s) throws Exception {
        int val = s.src.accept(this);
        if (s.dst instanceof TEMP) {
            temps[((TEMP) s.dst).num] = val;
        }
        else if (s.dst instanceof MEM) {
            // ...
        }
        // TEMP, MEM, FIELD, PARAM, or VAR
        else if (s.dst instanceof FIELD) {
            // ...
        }
        else if (s.dst instanceof PARAM) {
            // ...
        }
        else if (s.dst instanceof VAR) {
            // ...
        }
        else {
            throw new Exception("Unrecognized RHS expression in MOVE");
        }
        return STATUS_DEFAULT;
    }

    public int visit(JUMP s) throws Exception {
        return findStmtIdx(s.target);
    }
    
    public int visit(BINOP e) throws Exception {
        // evaluate both operands to lval and rval switch (e.op) {
    case BINOP.ADD: return lval + rval;
        // ...
    }

    public int visit(CALLST s) throws Exception {
        // ...
        if (fname.equals("print")) {
            // ... 
            // Call System.out.println()
        } else {
            //...
            // evaluate args
            stack[sp] = fp;
            fp = sp;
            f.accept(this);
            sp = fp;
            fp = stack[sp];
            // ...
        }
        // ...
    }
}