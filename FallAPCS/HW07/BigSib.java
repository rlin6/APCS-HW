/* Ricky Lin
   APCS1 pd2
   HW07 -- On the Origins of a BigSib
   2017-09-26 */

public class BigSib
{
  private static String helloMsg;

  public BigSib()
  {  //default constructor msg is hello
    helloMsg = "Hello ";
  }

  public BigSib(String msg)
  {
    helloMsg = msg;
  }

  public void setHelloMsg(String msg)
  { //mutator that changes instance variable
    helloMsg = msg;
  }

  public String greet(String name)
  { //returns the set helloMsg with the given name
    return helloMsg + name;
  }
}
