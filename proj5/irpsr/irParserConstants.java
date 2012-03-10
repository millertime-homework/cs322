/* Generated By:JavaCC: Do not edit this line. irParserConstants.java */
package irpsr;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface irParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int kwPROG = 6;
  /** RegularExpression Id. */
  int kwLOCAL = 7;
  /** RegularExpression Id. */
  int kwMAXARG = 8;
  /** RegularExpression Id. */
  int kwMOVE = 9;
  /** RegularExpression Id. */
  int kwJUMP = 10;
  /** RegularExpression Id. */
  int kwCJUMP = 11;
  /** RegularExpression Id. */
  int kwLABEL = 12;
  /** RegularExpression Id. */
  int kwCALLST = 13;
  /** RegularExpression Id. */
  int kwRETURN = 14;
  /** RegularExpression Id. */
  int kwESEQ = 15;
  /** RegularExpression Id. */
  int kwMEM = 16;
  /** RegularExpression Id. */
  int kwBINOP = 17;
  /** RegularExpression Id. */
  int kwCALL = 18;
  /** RegularExpression Id. */
  int kwTEMP = 19;
  /** RegularExpression Id. */
  int kwNAME = 20;
  /** RegularExpression Id. */
  int kwFIELD = 21;
  /** RegularExpression Id. */
  int kwPARAM = 22;
  /** RegularExpression Id. */
  int kwVAR = 23;
  /** RegularExpression Id. */
  int kwCONST = 24;
  /** RegularExpression Id. */
  int kwFLOAT = 25;
  /** RegularExpression Id. */
  int kwSTRING = 26;
  /** RegularExpression Id. */
  int DIGIT = 27;
  /** RegularExpression Id. */
  int LETTER = 28;
  /** RegularExpression Id. */
  int INTVAL = 29;
  /** RegularExpression Id. */
  int FLOATVAL = 30;
  /** RegularExpression Id. */
  int STRVAL = 31;
  /** RegularExpression Id. */
  int ID = 32;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"IR_PROGRAM\"",
    "\"locals\"",
    "\"max_args\"",
    "\"MOVE\"",
    "\"JUMP\"",
    "\"CJUMP\"",
    "\"LABEL\"",
    "\"CALLST\"",
    "\"RETURN\"",
    "\"ESEQ\"",
    "\"MEM\"",
    "\"BINOP\"",
    "\"CALL\"",
    "\"TEMP\"",
    "\"NAME\"",
    "\"FIELD\"",
    "\"PARAM\"",
    "\"VAR\"",
    "\"CONST\"",
    "\"FLOAT\"",
    "\"STRING\"",
    "<DIGIT>",
    "<LETTER>",
    "<INTVAL>",
    "<FLOATVAL>",
    "<STRVAL>",
    "<ID>",
    "\"(\"",
    "\"=\"",
    "\",\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"==\"",
    "\"!=\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"[\"",
    "\"]\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"&&\"",
    "\"||\"",
  };

}