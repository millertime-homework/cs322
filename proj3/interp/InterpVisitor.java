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
    
    private FUNC findFunc(String name) throws Exception {
        for (int i = 0; i < funcs.size(); i++) {
            FUNC currentFunc = funcs.elementAt(i);
            if (name.equals(currentFunc.label))
                return currentFunc;
        }
        throw new Exception("Function "+name+" not found.");
    }

    public void visit(PROG p) throws Exception {
        funcs = p.funcs;
        FUNC mf = findFunc("main");
        mf.accept(this);
    }

    public void visit(FUNClist f) throws Exception {}
    
    public void visit(FUNC f) throws Exception {
        STMTlist tmp = stmts;
        stmts = f.stmts;
        sp = sp - f.varCnt - f.argCnt - 1;
        f.stmts.accept(this);
        sp = sp + f.varCnt + f.argCnt + 1;
        stmts = tmp;
    }

    public int visit(MOVE s) throws Exception {
        int val = s.src.accept(this);
        if (s.dst instanceof TEMP) {
            temps[((TEMP) s.dst).num] = val;
        }
        else if (s.dst instanceof MEM) {
            int index = ((MEM) s.dst).exp.accept(this);
            heap[index] = val;
        }
        else if (s.dst instanceof FIELD) {
            FIELD f = (FIELD) s.dst;
            int index = f.obj.accept(this);
            heap[index + f.idx] = val;
        }
        else if (s.dst instanceof PARAM) {
            stack[fp + ((PARAM) s.dst).idx + 1] = val;
        }
        else if (s.dst instanceof VAR) {
            stack[fp - ((VAR) s.dst).idx] = val;
        }
        else {
            throw new Exception("Unrecognized RHS expression in MOVE");
        }
        return STATUS_DEFAULT;
    }

    public int visit(LABEL l) throws Exception {
        return STATUS_DEFAULT;
    }

    public int visit(RETURN r) throws Exception {
        if (r.exp != null)
            retVal = r.exp.accept(this);
        return STATUS_RETURN;
    }

    public int visit(EXPlist e) throws Exception {
        return STATUS_DEFAULT;
    }

    public int visit(ESEQ e) throws Exception {
        return STATUS_DEFAULT;
    }

    public int visit(BINOP e) throws Exception {
        int lval = e.left.accept(this);
        int rval = e.right.accept(this);
        switch (e.op) {
        case BINOP.ADD: return lval + rval;
        case BINOP.SUB: return lval - rval;
        case BINOP.MUL: return lval * rval;
        case BINOP.DIV: return lval / rval;
        case BINOP.AND: return lval * rval; // 1*1=1, 1*0=0
        case BINOP.OR:  return (lval == 1 || rval == 1)? 1 : 0;
        default:        throw new Exception("You're an IDIOT!"+
                                            " That's not an op!");
        }
    }

    public int visit(TEMP t) throws Exception {
        return temps[t.num];
    }

    public int visit(MEM m) throws Exception {
        int index = m.exp.accept(this);
        return heap[index];
    }

    public int visit(FIELD f) throws Exception {
        int index = f.obj.accept(this);
        return heap[index + f.idx];
    }

    public int visit(PARAM p) throws Exception {
        return stack[fp + p.idx + 1];       
    }

    public int visit(VAR v) throws Exception {
        return stack[fp - v.idx];
    }

    public int visit(CONST c) throws Exception {
        return c.val;
    }
    
    public int visit(FLOAT f) throws Exception {
        return (int) f.val;
    }

    public int visit(NAME n) throws Exception {
        // word size
        return 1;
    }

    public int visit(STRING s) throws Exception {
        return Integer.parseInt(s.s);
    }

    private int findStmtIdx(NAME target) throws Exception {
        for (int i = 0; i < stmts.size(); i++) {
            STMT currentStmt = stmts.elementAt(i);
            if (currentStmt instanceof LABEL) {
                if (target.id.equals(((LABEL) currentStmt).lab))
                    return i;
            }
        }
        throw new Exception("Label "+target.id+" not found.");
    }

    /*
      JUMP? YOU WANT JUMP?
      
      Get up, pack it in, let me begin
      I came to win, battle me that's a sin
      I won't tear the sack up, punk you'd better back up
      Try and play the role and the whole crew will act up

      Get up, stand up, come on, throw your hands up
      If you've got the feelin' jump across the ceilin'
      Muggs is a funk fest, someone's talkin' junk
      Yo, I'll bust 'em in the eye and then I'll take the punks home

      Feel it, funk it, amps it are junkin'
      And I got more rhymes than there's cops that are dunkin'
      Donuts shop, sure 'nuff I got props from the kids on the Hill
      Plus my mom and my pops
      I came to get down, I came to get down
      So get out your seats and jump around

      Jump around, jump up and get down
      Jump around, jump around
      Jump up and get down
      Jump up, jump up and get down

      Jump, jump, jump
      Jump, jump, jump
      Jump, jump, jump
      Jump, jump, jump
      Jump, jump, jump
      Jump, jump

      I'll serve your ass like John McEnroe
      If your girl steps up, I'm smackin' the hoe
      Word to your moms I came to drop bombs
      I got more rhymes than the Bible's got Psalms

      And just like the Prodigal Son I've returned
      Anyone steppin' to me you'll get burned
      'Cause I got lyrics and you ain't got none
      So if you come to battle bring a shotgun

      But if you do you're a fool, 'cause I duel to the death
      Try and step to me you'll take your last breath
      [- From: http://www.elyrics.net/read/h/house-of-pain-lyrics/jump-around-lyrics.html -]
      I gots the skill, come get your fill
      'Cause when I shoot ta give, I shoot to kill
      I came to get down, I came to get down
      So get out your seats and jump around

      Jump around, jump up and get down
      Jump around, jump around
      Jump up and get down
      Jump up, jump up and get down
      Jump, jump

      Listen to the sound that pounds, I jump around
      I'm no clown, I get down
      To the funk, listen to the wig out
      And step to the rear, dear, 'cause I'm here

      The P to the E to the T E rockin'
      The runs in your stockin'
      So hon, put the lock in
      Chillin' with the House Of Pain
      Blood stains the ground, huh, I jump around

      I'm the cream of the crop, I rise to the top
      I never eat a pig 'cause a pig is a cop
      Or better yet a Terminator, like Arnold Schwarzenegger
      Try'n to play me out like as if my name was Sega

      But I ain't going out like no punk bitch
      Get used to one style and you know I might switch
      It up up and around, then buck, buck you down
      Put out your head then you wake up in the Dawn of the Dead

      I'm comin' to get ya, comin' to get ya
      Spittin' out lyrics homie I'll wet ya
      I came to get down, I came to get down
      So get out your seats and jump around

      Jump around, jump up and get down
      Jump around, jump around
      Jump up and get down
      Jump up, jump up and get down

      Jump, jump, jump
      Jump, jump, jump
      Jump, jump, jump
    */

    public int visit(JUMP j) throws Exception {
        return findStmtIdx(j.target);
    }
    
    public int visit(CJUMP cj) throws Exception {
        int lval = cj.left.accept(this);
        int rval = cj.right.accept(this);
        boolean result;
        switch (cj.op) {
        case CJUMP.EQ: result = lval == rval; break;
        case CJUMP.NE: result = lval != rval; break;
        case CJUMP.LT: result = lval <  rval; break;
        case CJUMP.LE: result = lval <= rval; break;
        case CJUMP.GT: result = lval >  rval; break;
        case CJUMP.GE: result = lval >= rval; break;
        default:       throw new Exception("You're an IDIOT!"+
                                           " That's not an op!");
        }
        if (result)
            return findStmtIdx(cj.target);
        else
            return STATUS_DEFAULT;
    }

    public int visit(CALL c) throws Exception {
        String fname = c.func.id;
        if (fname.equals("malloc")) {
            hp = hp - c.args.elementAt(0).accept(this);
            retVal = hp;
        } else {
            for (int i = 0; i < c.args.size(); i++)
                stack[sp + i + 1] = c.args.elementAt(i).accept(this);
            stack[sp] = fp;
            fp = sp;
            FUNC f = findFunc(c.func.id);
            f.accept(this);
            sp = fp;
            fp = stack[sp];
        }
        return retVal;
    }

    public int visit(CALLST s) throws Exception {
        String fname = s.func.id;
        if (fname.equals("print")) {
            if (s.args.size() == 1) {
                EXP firstExp = s.args.elementAt(0);
                if (firstExp instanceof STRING)
                    System.out.println(((STRING) firstExp).s);
                else
                    System.out.println(firstExp.accept(this));
            }
            else
                System.out.println();
        } else {
            for (int i = 0; i < s.args.size(); i++)
                stack[sp + i + 1] = s.args.elementAt(i).accept(this);
            stack[sp] = fp;
            fp = sp;
            FUNC f = findFunc(s.func.id);
            f.accept(this);
            sp = fp;
            fp = stack[sp];
        }
        return STATUS_DEFAULT;
    }
}