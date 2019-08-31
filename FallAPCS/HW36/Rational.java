/* Ricky Lin
APCS1 pd2
HW36 -- Be Rational
2017-11-20 */

public class Rational
{
  private int numerator;
  private int denominator;

  public Rational()
  { //default constructor
    numerator = 0;
    denominator = 1;
  }

  public Rational(int p, int q)
  {
    this(); //call default constr
    if (q == 0) {  //if deno = 0
      System.out.println("Invalid Rational");
    }

    else
    {  //set fraction to values
      numerator = p;
      denominator = q;
    }
  }

  public String toString()
  {
    return numerator + "/" + denominator;
  }

  public double floatValue()
  {
    return numerator / (double) denominator;
  }

  public void multiply(Rational other)
  {
    numerator *= other.numerator;
    denominator *= other.denominator;
  }

  public void divide(Rational other)
  {
    //Division is the same as multiplying by the inverse
    if (other.numerator == 0)
    {
      System.out.println("Cannot divide by zero");
      return;
    }

    numerator *= other.denominator;
    denominator *= other.numerator;
  }

  public static void main(String[] args)
  {

    System.out.println("======= Rational one = new Rational() =======");
    Rational one = new Rational();
    System.out.println(one); //expect 0/1

    System.out.println("======= Rational two = new Rational(1,3) =======");
    Rational two = new Rational(1,3);
    System.out.println(two); //expect 1/3

    System.out.println("======= Rational three = new Rational(1337,0) =======");
    Rational three = new Rational(1337,0);
    System.out.println(three); //expect 0/1

    System.out.println("======= two.multiply(one) =======");
    two.multiply(one);
    System.out.println(two); // 0/3

    System.out.println("======= three.divide(one) =======");
    three.divide(one); // Cannot divide by zero
    System.out.println(three); // 0/3
    System.out.println("======= Rational four = new Rational(2,7) =======");
    Rational four = new Rational(2,7);
    System.out.println(four);
    System.out.println("======= four.floatValue() =======");
    System.out.println(four.floatValue());
  }
}
