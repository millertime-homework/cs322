/* Generated By:JavaCC: Do not edit this line. irParser.java */
package irpsr;
import ir.*;
public class irParser implements irParserConstants {

  static final public PROG Program() throws ParseException {
  FUNClist funcs=new FUNClist(); FUNC seg;
    jj_consume_token(kwPROG);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      seg = FUNC();
                              funcs.addElement(seg);
    }
    jj_consume_token(0);
    {if (true) return new PROG(funcs);}
    throw new Error("Missing return statement in function");
  }

  static final public FUNC FUNC() throws ParseException {
  Token t; int vc, ac; STMTlist sl=new STMTlist(); STMT s;
    t = jj_consume_token(ID);
    jj_consume_token(33);
    jj_consume_token(kwLOCAL);
    jj_consume_token(34);
    vc = INT();
    jj_consume_token(35);
    jj_consume_token(kwMAXARG);
    jj_consume_token(34);
    ac = INT();
    jj_consume_token(36);
    jj_consume_token(37);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 45:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      s = STMT();
                     sl.add(s);
    }
    jj_consume_token(38);
    {if (true) return new FUNC(t.image,vc,ac,sl);}
    throw new Error("Missing return statement in function");
  }

  static final public int relopCode() throws ParseException {
  int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 39:
      jj_consume_token(39);
           n = ir.CJUMP.EQ;
      break;
    case 40:
      jj_consume_token(40);
           n = ir.CJUMP.NE;
      break;
    case 41:
      jj_consume_token(41);
           n = ir.CJUMP.LT;
      break;
    case 42:
      jj_consume_token(42);
           n = ir.CJUMP.LE;
      break;
    case 43:
      jj_consume_token(43);
           n = ir.CJUMP.GT;
      break;
    case 44:
      jj_consume_token(44);
           n = ir.CJUMP.GE;
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public STMT STMT() throws ParseException {
  Token t; int n; STMT s; EXP e1=null, e2, e3;
  EXPlist el=new EXPlist();
    jj_consume_token(45);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case kwMOVE:
      jj_consume_token(kwMOVE);
      e1 = EXP();
      e2 = EXP();
                                          s = new MOVE(e1,e2);
      break;
    case kwJUMP:
      jj_consume_token(kwJUMP);
      e1 = EXP();
                                          s = new JUMP((NAME)e1);
      break;
    case kwCJUMP:
      jj_consume_token(kwCJUMP);
      n = relopCode();
      e1 = EXP();
      e2 = EXP();
      e3 = EXP();
                                          s = new CJUMP(n,e1,e2,(NAME)e3);
      break;
    case kwLABEL:
      jj_consume_token(kwLABEL);
      t = jj_consume_token(ID);
                                          s = new LABEL(t.image);
      break;
    case kwCALLST:
      jj_consume_token(kwCALLST);
      e1 = EXP();
      jj_consume_token(33);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 33:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_3;
        }
        e2 = EXP();
                                           el.add(e2);
      }
      jj_consume_token(36);
                                          s = new CALLST((NAME)e1,el);
      break;
    case kwRETURN:
      jj_consume_token(kwRETURN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 33:
        e1 = EXP();
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
                                          s = new RETURN(e1);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(46);
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  static final public int binopCode() throws ParseException {
  int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 47:
      jj_consume_token(47);
           n = ir.BINOP.ADD;
      break;
    case 48:
      jj_consume_token(48);
           n = ir.BINOP.SUB;
      break;
    case 49:
      jj_consume_token(49);
           n = ir.BINOP.MUL;
      break;
    case 50:
      jj_consume_token(50);
           n = ir.BINOP.DIV;
      break;
    case 51:
      jj_consume_token(51);
           n = ir.BINOP.AND;
      break;
    case 52:
      jj_consume_token(52);
           n = ir.BINOP.OR;
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public EXP EXP() throws ParseException {
  Token t; int n; float f; String str;
  STMT s; EXP e, e2; EXPlist el=new EXPlist();
    jj_consume_token(33);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case kwESEQ:
      jj_consume_token(kwESEQ);
      s = STMT();
      e = EXP();
                                          e = new ESEQ(s,e);
      break;
    case kwMEM:
      jj_consume_token(kwMEM);
      e = EXP();
                                          e = new MEM(e);
      break;
    case kwBINOP:
      jj_consume_token(kwBINOP);
      n = binopCode();
      e = EXP();
      e2 = EXP();
                                                  e = new BINOP(n,e,e2);
      break;
    case kwCALL:
      jj_consume_token(kwCALL);
      e = EXP();
      jj_consume_token(33);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 33:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_4;
        }
        e2 = EXP();
                                          el.add(e2);
      }
      jj_consume_token(36);
                                          e = new CALL((NAME)e,el);
      break;
    case kwTEMP:
      jj_consume_token(kwTEMP);
      n = INT();
                                          e = new TEMP(n);
      break;
    case kwNAME:
      jj_consume_token(kwNAME);
      t = jj_consume_token(ID);
                                          e = new NAME(t.image);
      break;
    case kwFIELD:
      jj_consume_token(kwFIELD);
      e = EXP();
      n = INT();
                                          e = new FIELD(e,n);
      break;
    case kwPARAM:
      jj_consume_token(kwPARAM);
      n = INT();
                                          e = new PARAM(n);
      break;
    case kwVAR:
      jj_consume_token(kwVAR);
      n = INT();
                                          e = new VAR(n);
      break;
    case kwCONST:
      jj_consume_token(kwCONST);
      n = INT();
                                          e = new CONST(n);
      break;
    case kwFLOAT:
      jj_consume_token(kwFLOAT);
      f = FLO();
                                          e = new FLOAT(f);
      break;
    case kwSTRING:
      jj_consume_token(kwSTRING);
      str = STR();
                                          e = new STRING(str);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(36);
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public int INT() throws ParseException {
  Token t;
    t = jj_consume_token(INTVAL);
               {if (true) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  static final public float FLO() throws ParseException {
  Token t;
    t = jj_consume_token(FLOATVAL);
                 {if (true) return Float.parseFloat(t.image);}
    throw new Error("Missing return statement in function");
  }

  static final public String STR() throws ParseException {
  Token t; String s;
    t = jj_consume_token(STRVAL);
               s=t.image; {if (true) return s.substring(1,s.length()-1);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public irParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x0,0x7e00,0x0,0x0,0x7ff8000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x1,0x2000,0x1f80,0x2,0x2,0x0,0x1f8000,0x2,0x0,};
   }

  /** Constructor with InputStream. */
  public irParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public irParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new irParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public irParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new irParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public irParser(irParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(irParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[53];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 53; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}