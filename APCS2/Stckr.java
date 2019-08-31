/*****************************************************
 * class Stckr
 * driver/tester for Stack implementations (Linked-list-based, ArrayList-based)
 *****************************************************/

public class Stckr
{
    public static void main( String[] args )
    {
	Stack cakes = new ALStack<String>();
  //Stack<Z> cakes = new LLStack<Z>();
  System.out.println("Printing isEmpty at initialization: Expect True");
  System.out.println( cakes.isEmpty() ); //expect true
  cakes.push("jack");
  cakes.push("queen");
  cakes.push("king");
  cakes.push("ace");
  System.out.println("Printing isEmpty after pushing: Expect False");
  System.out.println( cakes.isEmpty() ); //expect false

  while (!cakes.isEmpty()) {
    System.out.println( "peek() and pop() should be same");
    System.out.println( "peek() " + cakes.peek() );
    System.out.println( "pop() " + cakes.pop() );
  }

  System.out.println("Printing isEmpty at end: Expect True");
  System.out.println( cakes.isEmpty() ); //expect true

    }//end main

}//end class
