/*
Ricky Lin
APCS2 pd02
HW #31: Stack: What Is It Good For?
2018-04-11 T
*/
/*****************************************************
 * skeleton for class LatKtS
 * Driver class for Latkes.
 * Uses a stack to reverse a text string, check for sets of matching parens.
 *****************************************************/

public class LatKtS
{

  /**********************************************************
   * precondition:  input string has length > 0
   * postcondition: returns reversed string s
   *                flip("desserts") -> "stressed"
   **********************************************************/
  public static String flip( String s )
  {
    String retVal = "";  //store reversed string
    Latkes h = new Latkes( s.length() ); //new stack with _stackSize the length of string
    while ( s.length() > 0 ) {   //while there's still characters in the string
      h.push(s.substring(0,1));  //push the first character into the stack
      s = s.substring(1);  //remove the first character by setting the string to everything after it
    }
    while ( !( h.isEmpty() ) )  {  //if the stack is not empty
      retVal += h.pop();            //add the popped value into the reverse string
    }
    return retVal;    //return said string
  }//end flip()


  /**********************************************************
   * precondition:  s contains only the characters {,},(,),[,]
   * postcondition: allMatched( "({}[()])" )    -> true
   *                allMatched( "([)]" )        -> false
   *                allMatched( "" )            -> true
   **********************************************************/
  public static boolean allMatched( String s )
  {
      int counter = 0;
      Latkes h = new Latkes( s.length() );
      while ( s.length() > 0 ) {
        h.push( s.substring(0,1) );
        s = s.substring(1);
        while ( counter < s.length() ) {
          if ( h.peek().equals(s.substring( counter,counter+1 ) ) ) {
            h.pop();
          }
          counter++;
        }
        counter = 0;
      }
      return h.isEmpty();
  }//end allMatched()

  //main method to test
  public static void main( String[] args )
  {
    System.out.println(flip("stressed"));
    System.out.println(allMatched( "({}[()])" )); //true
    System.out.println(allMatched( "([)]" ) ); //false
    System.out.println(allMatched( "(){([])}" ) ); //true
    System.out.println(allMatched( "](){([])}" ) ); //false
    System.out.println(allMatched( "(){([])}(" ) ); //false
    System.out.println(allMatched( "()[[]]{{{{((([])))}}}}" ) ); //true
    /*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v

      ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
  }

}//end class LatKtS
