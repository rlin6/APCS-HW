/*
Ricky Lin
APCS2 pd02
HW #33: What a Racket
2018-04-13 F
*/

/*****************************************************
 * class Scheme
 * Simulates a rudimentary Scheme interpreter
 *
 * ALGORITHM for EVALUATING A SCHEME EXPRESSION:
 *  My algorithm starts by spliting the String by blank space and then pushing the string into the stack in reverse, excluding the first parenthesis because it's useless information.
 *  Then, I create a new ArrayList stack and popped the operator out as a variable. Depending on what the operator is, evaluate will call a specific operator in unload, which will work on the remaining stack.
 *  Next, in unload, I create an int value to do calculations on and a string to add to in the end. I then run a while loop and only stops when it sees an closing parenthesis.
 *  In the loop, i have 3 if statements for each operator. Each if statement works the same, just with different operations: Check to see if number after operator is ( or number
 *  If parenthesis, check inside parenthesis to see which operation it is and recursive call unload with that operator as a parameter.
 *  If number, check to see if it's the first number by checking if retVal is 0. If it's the first nubmer, change retVal to that number. If it's not, perform the operation on retVal with parsed Ints.
 *  Finally, once you find an ending parenthesis, end the while loop and pop out that parenthesis so that the outer unloading wouldn't stop at the inner unloading parenthesis. Then return the concatenation of the string and retVal.
 *
 * STACK OF CHOICE: ArrayList
 * b/c ... It seems much simplier to me to keep track of all the elements in an ArrayList by index instead of using references and pointers in Linked List to get to an element
 ******************************************************/

public class Scheme
{
  /******************************************************
   * precond:  Assumes expr is a valid Scheme (prefix) expression,
   *           with whitespace separating all operators, parens, and
   *           integer operands.
   * postcond: Returns the simplified value of the expression, as a String
   * eg,
   *           evaluate( "( + 4 3 )" ) -> 7
   *	         evaluate( "( + 4 ( * 2 5 ) 3 )" ) -> 17
   ******************************************************/
  public static String evaluate( String expr )
  {
    Stack<String> newStack = new ALStack<String>();  //instantiate new stack
    String[] arr = expr.split("\\s+");  //split the string by space
    int ctr = arr.length; //counter for while loop run
    while ( ctr > 1 ) {
      newStack.push( arr[ ctr - 1 ] );  //push all but first string in
      ctr--;  //decrement counter
    }
    String op = newStack.pop();  //check the operation to perform
    if ( op.equals("+") ) {
      return unload( 1, newStack );  //run addition
    }
    else if ( op.equals("-") ) {
      return unload( 2, newStack );  //run subtraction
    }
    else {
      return unload( 3, newStack );  //run multiplication
    }
  }//end evaluate()


  /******************************************************
   * precond:  Assumes top of input stack is a number.
   * postcond: Performs op on nums until closing paren is seen thru peek().
   *           Returns the result of operating on sequence of operands.
   *           Ops: + is 1, - is 2, * is 3
   ******************************************************/
  public static String unload( int op, Stack<String> numbers )
  {
    int retVal = 0;  //int to perform calculations on
    String Val = ""; //string to concatenate in the end
    while ( !( numbers.peek().equals(")") ) ) {  //while you haven't reach the closing parenthesis
      if ( op == 1) {  //addition
        String next = numbers.pop();  //save element after operator
        if ( next.equals("(") ) {   //if it's an inner parenthesis
          String inner = numbers.pop();  //check the operator of that parenthesis
          if ( inner.equals("+") ) {
            retVal += Integer.parseInt( unload( 1, numbers ) );  //recursive call of addition
          }
          else if ( inner.equals("-") ) {
            retVal += Integer.parseInt( unload( 2, numbers ) ); //recursive call of subtraction
          }
          else {
            retVal += Integer.parseInt( unload( 3, numbers ) ); //recursive call of multiplcation
          }
        }
        else {  //if it's a number
          if (retVal == 0) {  //if it's the first number
            retVal = Integer.parseInt( next );  //set retVal to that number
          }
          else {
            retVal += Integer.parseInt( next );  //perform operation to retVal
          }
        }
      }
      else if ( op == 2) {  //same exact thing as before except with subtraction
        String next = numbers.pop();
        if ( next.equals("(") ) {
          String inner = numbers.pop();
          if ( inner.equals("+") ) {
            retVal -= Integer.parseInt( unload( 1, numbers ) );
          }
          else if ( inner.equals("-") ) {
            retVal -= Integer.parseInt( unload( 2, numbers ) );
          }
          else {
            retVal -= Integer.parseInt( unload( 3, numbers ) );
          }
        }
        else {
          if (retVal == 0) {
            retVal = Integer.parseInt( next );
          }
          else {
            retVal -= Integer.parseInt( next );
          }
        }
      }
      else {  //multiplication version
        String next = numbers.pop();
        if ( next.equals("(") ) {
          String inner = numbers.pop();
          if ( inner.equals("+") ) {
            retVal *= Integer.parseInt( unload( 1, numbers ) );
          }
          else if ( inner.equals("-") ) {
            retVal *= Integer.parseInt( unload( 2, numbers ) );
          }
          else {
            retVal *= Integer.parseInt( unload( 3, numbers ) );
          }
        }
        else {
          if (retVal == 0) {
            retVal = Integer.parseInt( next );
          }
          else {
            retVal *= Integer.parseInt( next );
          }
        }
      }
    }
    numbers.pop();  //remove the closing parenthesis so that when evalutating inner parenthesis, the inner closing parenthesis won't also stop the outside parenthesis call
    return Val + retVal;  //concatenate answer
  } //end unload()


  /*
  //optional check-to-see-if-its-a-number helper fxn:
  public static boolean isNumber( String s ) {
  try {
  Integer.parseInt(s);
  return true;
	}
  catch( NumberFormatException e ) {
  return false;
	}
  }
  */


  //main method for testing
  public static void main( String[] args )
  {
    String zoo0 = "( - 5 1 )";
    System.out.println( evaluate( zoo0 ) );

      String zoo1 = "( + 4 3 )";
      System.out.println(zoo1);
      System.out.println("zoo1 eval'd: " + evaluate(zoo1) );
      //...7

      String zoo2 = "( + 4 ( * 2 5 ) 3 )";
      System.out.println(zoo2);
      System.out.println("zoo2 eval'd: " + evaluate(zoo2) );
      //...17

      String zoo3 = "( + 4 ( * 2 5 ) 6 3 ( - 56 50 ) )";
      System.out.println(zoo3);
      System.out.println("zoo3 eval'd: " + evaluate(zoo3) );
      //...29

      String zoo4 = "( - 1 2 3 )";
      System.out.println(zoo4);
      System.out.println("zoo4 eval'd: " + evaluate(zoo4) );
      //...-4
      /*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v

      ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/
  }//main

}//end class Scheme
