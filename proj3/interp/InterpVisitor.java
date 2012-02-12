package interp;

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
}

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