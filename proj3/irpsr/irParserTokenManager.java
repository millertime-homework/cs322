/* Generated By:JavaCC: Do not edit this line. irParserTokenManager.java */
package irpsr;
import ir.*;

/** Token Manager. */
public class irParserTokenManager implements irParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7ffffc0L) != 0L)
         {
            jjmatchedKind = 32;
            return 6;
         }
         return -1;
      case 1:
         if ((active0 & 0x7ffffc0L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 1;
            return 6;
         }
         return -1;
      case 2:
         if ((active0 & 0x77effc0L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 2;
            return 6;
         }
         if ((active0 & 0x810000L) != 0L)
            return 6;
         return -1;
      case 3:
         if ((active0 & 0x1ca600L) != 0L)
            return 6;
         if ((active0 & 0x76259c0L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 32;
               jjmatchedPos = 3;
            }
            return 6;
         }
         return -1;
      case 4:
         if ((active0 & 0x3621800L) != 0L)
            return 6;
         if ((active0 & 0x40061c0L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 4;
            return 6;
         }
         return -1;
      case 5:
         if ((active0 & 0x4006080L) != 0L)
            return 6;
         if ((active0 & 0x140L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 5;
            return 6;
         }
         return -1;
      case 6:
         if ((active0 & 0x140L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 6;
            return 6;
         }
         return -1;
      case 7:
         if ((active0 & 0x100L) != 0L)
            return 6;
         if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 7;
            return 6;
         }
         return -1;
      case 8:
         if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 8;
            return 6;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x8000000000000L);
      case 40:
         return jjStopAtPos(0, 33);
      case 41:
         return jjStopAtPos(0, 36);
      case 42:
         return jjStopAtPos(0, 49);
      case 43:
         return jjStopAtPos(0, 47);
      case 44:
         return jjStopAtPos(0, 35);
      case 45:
         return jjStopAtPos(0, 48);
      case 47:
         return jjStopAtPos(0, 50);
      case 60:
         jjmatchedKind = 41;
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 61:
         jjmatchedKind = 34;
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      case 62:
         jjmatchedKind = 43;
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x1042800L);
      case 69:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x2200000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x40L);
      case 74:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 77:
         return jjMoveStringLiteralDfa1_0(0x10200L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 80:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 82:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x4000000L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 86:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 91:
         return jjStopAtPos(0, 45);
      case 93:
         return jjStopAtPos(0, 46);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 123:
         return jjStopAtPos(0, 37);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x10000000000000L);
      case 125:
         return jjStopAtPos(0, 38);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 51);
         break;
      case 61:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(1, 39);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 40);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 42);
         else if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 44);
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0xd43000L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x94000L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x220000L);
      case 74:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 76:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000200L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x40L);
      case 83:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 84:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x100L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 124:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStopAtPos(1, 52);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x208000L);
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x42000L);
      case 77:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(2, 16, 6);
         return jjMoveStringLiteralDfa3_0(active0, 0x180400L);
      case 78:
         return jjMoveStringLiteralDfa3_0(active0, 0x1020000L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 82:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(2, 23, 6);
         return jjMoveStringLiteralDfa3_0(active0, 0x4400000L);
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 86:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 95:
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x2400000L);
      case 69:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(3, 9, 6);
         else if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(3, 20, 6);
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 73:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000L);
      case 76:
         if ((active0 & 0x40000L) != 0L)
         {
            jjmatchedKind = 18;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x202000L);
      case 77:
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 79:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 80:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(3, 10, 6);
         else if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 6);
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 81:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 6);
         break;
      case 83:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      case 95:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 68:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(4, 21, 6);
         break;
      case 76:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(4, 12, 6);
         break;
      case 77:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(4, 22, 6);
         break;
      case 78:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000L);
      case 80:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(4, 11, 6);
         else if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 6);
         break;
      case 82:
         return jjMoveStringLiteralDfa5_0(active0, 0x4040L);
      case 83:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 84:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(4, 24, 6);
         else if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(4, 25, 6);
         break;
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x100L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 71:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(5, 26, 6);
         break;
      case 78:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(5, 14, 6);
         break;
      case 79:
         return jjMoveStringLiteralDfa6_0(active0, 0x40L);
      case 84:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(5, 13, 6);
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x100L);
      case 115:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(5, 7, 6);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 71:
         return jjMoveStringLiteralDfa7_0(active0, 0x40L);
      case 103:
         return jjMoveStringLiteralDfa7_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa8_0(active0, 0x40L);
      case 115:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(7, 8, 6);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa9_0(active0, 0x40L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 77:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(9, 6, 6);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 13;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAddStates(0, 4);
                  }
                  else if (curChar == 34)
                     jjCheckNAdd(3);
                  else if (curChar == 46)
                     jjCheckNAdd(1);
                  break;
               case 0:
                  if (curChar == 46)
                     jjCheckNAdd(1);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 30)
                     kind = 30;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if ((0xfffffffbfffffbffL & l) != 0L)
                     jjCheckNAddTwoStates(3, 4);
                  break;
               case 4:
                  if (curChar == 34 && kind > 31)
                     kind = 31;
                  break;
               case 6:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAddStates(0, 4);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAdd(8);
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(9, 10);
                  break;
               case 10:
                  if (curChar != 46)
                     break;
                  if (kind > 30)
                     kind = 30;
                  jjCheckNAdd(11);
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 30)
                     kind = 30;
                  jjCheckNAdd(11);
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(12, 0);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(6);
                  break;
               case 3:
                  jjAddStates(5, 6);
                  break;
               case 6:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(6);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(5, 6);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 13 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   8, 9, 10, 12, 0, 3, 4, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, "\111\122\137\120\122\117\107\122\101\115", 
"\154\157\143\141\154\163", "\155\141\170\137\141\162\147\163", "\115\117\126\105", "\112\125\115\120", 
"\103\112\125\115\120", "\114\101\102\105\114", "\103\101\114\114\123\124", 
"\122\105\124\125\122\116", "\105\123\105\121", "\115\105\115", "\102\111\116\117\120", 
"\103\101\114\114", "\124\105\115\120", "\116\101\115\105", "\106\111\105\114\104", 
"\120\101\122\101\115", "\126\101\122", "\103\117\116\123\124", "\106\114\117\101\124", 
"\123\124\122\111\116\107", null, null, null, null, null, null, "\50", "\75", "\54", "\51", "\173", 
"\175", "\75\75", "\41\75", "\74", "\74\75", "\76", "\76\75", "\133", "\135", "\53", 
"\55", "\52", "\57", "\46\46", "\174\174", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x1fffffe7ffffc1L, 
};
static final long[] jjtoSkip = {
   0x3eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[13];
static private final int[] jjstateSet = new int[26];
private static final StringBuilder jjimage = new StringBuilder();
private static StringBuilder image = jjimage;
private static int jjimageLen;
private static int lengthOfMatch;
static protected char curChar;
/** Constructor. */
public irParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public irParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 13; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         TokenLexicalActions(matchedToken);
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 29 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    try {
      Integer.parseInt(matchedToken.image);
    } catch (Exception e) {
      throw new TokenMgrError("Lexical error at line "
        + matchedToken.beginLine + ", column " + matchedToken.beginColumn +
        ".  Integer value over 2^31-1", 0);
    }
         break;
      case 30 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    try {
      Float.parseFloat(matchedToken.image);
    } catch (Exception e) {
      throw new TokenMgrError("Lexical error at line "
        + matchedToken.beginLine + ", column " + matchedToken.beginColumn +
        ".  Float value over limit", 0);
    }
         break;
      case 31 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    if (matchedToken.image.length() > 257)
      throw new TokenMgrError("Lexical error at line "
        + matchedToken.beginLine + ", column " + matchedToken.beginColumn +
        ".  String length over 255", 0);
         break;
      case 32 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    if (matchedToken.image.length() > 255)
      throw new TokenMgrError("Lexical error at line "
        + matchedToken.beginLine + ", column " + matchedToken.beginColumn +
        ".  Id length over 255", 0);
         break;
      default :
         break;
   }
}
static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
