/* Ricky Lin
   APCS1 pd2
   HW02 -- Howdy, <VeryInterestingPerson>!!!
   2017-09-17 */

public class Greet
{
  public static void greet(String name)
  {
    System.out.println("Hello, " + name + ", how do you do?"); //method that prints Hello, [parameter], how do you do?
  }

  public static void main(String[] args)
  {
    greet("Bob"); //calls greet method with name as parameter
    greet("George");
    greet("Jorge");
  }
}
