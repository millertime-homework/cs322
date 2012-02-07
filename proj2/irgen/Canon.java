// IR tree canonicalization for MINI v1.6. (Jingke Li)
//
package irgen;
import ir.*;

public class Canon implements IrVI {

  private STMT getStmt(EXP e) {
    if (e instanceof ESEQ)
      return ((ESEQ) e).stmt;
    return null;
  }

  private EXP getExp(EXP e) {
    if (e instanceof ESEQ)
      return ((ESEQ) e).exp;
    return e;
  }

  private STMT mergeStmts(STMT s1, STMT s2) {
    if (s1 == null) return s2;
    if (s2 == null) return s1;
    STMTlist sl = new STMTlist();
    sl.add(s1);
    sl.add(s2);
    return sl;
  }   

  public PROG visit(PROG t) {
    return new PROG(t.funcs.accept(this));
  }

  public FUNClist visit(FUNClist t) {
    FUNClist funcs = new FUNClist();
    for (int i=0; i<t.size(); i++) {
      FUNC s = ((FUNC) t.elementAt(i)).accept(this);
      funcs.add(s);
    }
    return funcs;
  }

  public FUNC visit(FUNC t) {
    return new FUNC(t.label, t.varCnt, t.argCnt, 
		    (STMTlist) t.stmts.accept(this));
  }

  public STMT visit(STMTlist t) {
    STMTlist stmts = new STMTlist();
    for (int i=0; i<t.size(); i++) {
      STMT s = ((STMT) t.elementAt(i)).accept(this);
      stmts.add(s);
    }
    return stmts;
  }

  public STMT visit(MOVE t) {
    EXP dst = t.dst.accept(this);
    EXP src = t.src.accept(this);
    STMT s = mergeStmts(getStmt(dst), getStmt(src));
    if (s != null)
      return mergeStmts(s, new MOVE(getExp(dst), getExp(src)));
    return t;
  }
  
  public STMT visit(JUMP t) { return t; }
  
  public STMT visit(CJUMP t) { 
    EXP left = t.left.accept(this);
    EXP right = t.right.accept(this);
    STMT s = mergeStmts(getStmt(left), getStmt(right));
    if (s != null)
      return mergeStmts(s, new CJUMP(t.op, getExp(left), 
				     getExp(right), t.target));
    return t;
  }

  public STMT visit(LABEL t) { return t; }

  public STMT visit(CALLST t) {
    EXP args = t.args.accept(this);
    STMT s = getStmt(args);
    if (s != null)
      return mergeStmts(s, new CALLST(t.func, (EXPlist) getExp(args)));
    return t;
  }

  public STMT visit(RETURN t) { 
    EXP exp = t.exp.accept(this);
    STMT s = getStmt(exp);
    if (s != null)
      return mergeStmts(s, new RETURN(getExp(exp)));
    return t; 
  }

  public EXP visit(EXPlist t) {
    STMT s = null;
    EXPlist args = new EXPlist();
    for (int i=0; i<t.size(); i++) {
      EXP e = ((EXP) t.elementAt(i)).accept(this);
      STMT s1 = getStmt(e);
      if (s1 != null) s = mergeStmts(s, s1);
      args.add(getExp(e));
    }
    if (s != null)
      return new ESEQ(s, args);
    return t;
  }

  public EXP visit(ESEQ t) {
    STMT stmt = t.stmt.accept(this);
    EXP exp = t.exp.accept(this);
    STMT s = mergeStmts(stmt, getStmt(exp));
    if (s != null)
      return new ESEQ(s, getExp(exp));
    return t; 
  }

  public EXP visit(MEM t) {
    EXP exp = t.exp.accept(this);
    STMT s = getStmt(exp);
    if (s != null)
      return new ESEQ(s, new MEM(getExp(exp)));
    return t;
  }

  public EXP visit(CALL t) {
    EXP args = t.args.accept(this);
    STMT s = getStmt(args);
    if (t.func.id != "malloc") {
      TEMP tmp = new TEMP();
      STMT s1 = new MOVE(tmp, new CALL(t.func, (EXPlist) getExp(args)));
      return new ESEQ(mergeStmts(s,s1), tmp);
    } else if (s != null) {
      return new ESEQ(s, new CALL(t.func, (EXPlist) getExp(args)));
    } else {
      return t;
    }
  }

  public EXP visit(BINOP t) {
    EXP left = t.left.accept(this);
    EXP right = t.right.accept(this);
    STMT s = mergeStmts(getStmt(left), getStmt(right));
    if (s != null)
      return new ESEQ(s, new BINOP(t.op, getExp(left), getExp(right)));
    return t;
  }

  public EXP visit(FIELD t) {
    EXP obj = t.obj.accept(this);
    STMT s = getStmt(obj);
    if (s != null)
      return new ESEQ(s, new FIELD(getExp(obj), t.idx));
    return t;
  }

  public EXP visit(NAME t)   { return t; }
  public EXP visit(TEMP t)   { return t; }
  public EXP visit(PARAM t)  { return t; }
  public EXP visit(VAR t)    { return t; }
  public EXP visit(CONST t)  { return t; }
  public EXP visit(FLOAT t)  { return t; }
  public EXP visit(STRING t) { return t; }
}

