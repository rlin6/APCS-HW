/* Ricky Lin
   APCS1 pd2
   HW06 -- On BigSib Individuality and the Elimination of Radio Fuzz
   2017-09-25 */

public class Greet
{
  public static void main(String[] args)
  {
    String greeting;

    BigSib richard = new BigSib(); //create an instance of BigSib named Richard
    richard.setHelloMsg("Word up "); //use mutator method to change private variable helloMsg to a certain msg
    greeting = richard.greet("freshmen"); //set the variable greeting to what the method greet would return given the new helloMsg and name parameter
    System.out.println(greeting); //return the msg

    richard.setHelloMsg("Salutations ");
    greeting = richard.greet("Dr. Spaceman");
    System.out.println(greeting);

    richard.setHelloMsg("Hey ya ");
    greeting = richard.greet("Kong Fooey");
    System.out.println(greeting);

    richard.setHelloMsg("Sup ");
    greeting = richard.greet("mom");
    System.out.println(greeting);
  }
}
