/* Ricky Lin
   APCS1 Pd2
   HW15 -- Wayne's World
   2017-10-11 */

public class Coin
{
  //instance vars
  private double value;
  private String upFace;
  private String name;
  //private int flipCtr;
  //private int headsCtr;
  //private int tailsCtr;
  private float bias;

  public Coin()
  {
    assignValue(1.0);
    upFace = "heads"; //default constructor sets upFace to heads
    bias = 0.5;
  }

  public Coin(String newName)
  {
    this(); //calls default constr
    name = newName; //assign new name
  }

  public Coin(String newName, String currFace)
  {
    this(newName); //call prev overloaded constr
    upFace = currFace;
  }

  public String toString()
  {
    return name + " -- " + upFace;
  }

  public boolean equals(Coin other)
  {
    if (other.upFace == upFace)
    {
      return true;
    }
    return false;
  }

  public void assignValue(double val)
  {
    value = val;
  }

  public String flip()
  { //bias of 1.0 = always heads, 0.0 always tail
    if (Math.random() >= bias)
    {
      upFace = "tails";
      return upFace;
    }
    upFace = "heads";
    return upFace;
  }

}
