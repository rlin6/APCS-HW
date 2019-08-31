/******************************
 * class Rational v.3
 * (SKELETON)
 * stores a rational number (p/q s.t. p,q ints && q!=0)
 * facilitates
 * multiplication
 * division
 * addition
 * subtraction
 * reduction
 * comparison
 ******************************/

/* Ricky Lin
APCS1 pd2
HW44 -- implementing an interface, boolean short-circuiting
2017-12-01 */

public class Rational implements Comparable
{
  //instance var for num & denom
  private int numerator;
  private int denominator;

  public Rational()
  { //default constructor -> 0/1
    numerator = 0;
    denominator = 1;
  }

  public Rational(int p, int q)
  {
    this(); //call default constr

    if (q != 0)
    { //if deno != 0
      numerator = p;
      denominator = q;
    }

    else
    { //invalid denom -> print msg and set fraction to default
      System.out.println("Invalid denominator. Set to 0/1.");
    }
  }

  public String toString()
  {
    //return String representation of fraction
    return numerator + "/" + denominator;
  }

  public double floatValue()
  {
    //return the floating point value of fraction
    return numerator / (double) denominator;
  }

  public void multiply(Rational other)
  {
    //multiply the two numerators & denominators w/o reducing
    numerator *= other.numerator;
    denominator *= other.denominator;
  }

  public void divide(Rational other)
  {
    //Division is the same as multiplying by the inverse
    if (other.numerator != 0)
    {
      numerator *= other.denominator;
      denominator *= other.numerator;
    }

    else
    {
      System.out.println("Cannot divide by zero. Values not changed.");
    }
  }

  public void add(Rational other)
  {
    numerator = (numerator * other.denominator) + (other.numerator * denominator);
    denominator *= other.denominator;
  }

  public void subtract(Rational other)
  {
    numerator = (numerator * other.denominator) - (other.numerator * denominator);
    denominator *= other.denominator;
  }

  public void reduce()
  {
    int g = gcd();
    numerator /= g;
    denominator /= g;
  }

  public int gcd()
  {
    return gcd(numerator, denominator);
  }

  public static int gcd(int n, int d)
  {
    int a = n;
    int b = d;
    int x;

    while( a % b != 0 )
    {
      x = a;
      a = b;
      b = x % b;
    }

    return b;
  }

  /*********************
  * boolean equals(Object) -- tells whether 2 Objs are equivalent
  * pre:  other is an instance of class Rational
  * post: Returns true if this and other are aliases (pointers to same
  *   Object), or if this and other have matching attribute values.
  *   (which in this case indicates equivalent fractions)
  *********************/
  public boolean equals( Object other )
  {
    //First, reduce both fractions.
    //...thus allowing for direct comparison of attributes
    reduce();
    return (compareTo(other) == 0);
  }//end equals()


  /*********************
  * int compareTo(Object) -- tell which of two Rationals is greater
  *  pre:
  *  post: Throw exception if input not an instance of class Rational.
  *    Return 0 if this Rational equiv to Rational argument.
  *    Return negative integer if this < other.
  *    Return positive integer if this > other.
  *********************/
  public int compareTo( Object other )
  {
    int firstNum = 0;
    int secNum = 0;
    if (!(other instanceof Rational)) {
      throw new ClassCastException("Input is not a Rational");
    }
    firstNum = _numerator * ((Rational)other)._denominator;
    secNum = ((Rational)other)._numerator * _denominator;
    return firstNum - secNum;
  }

  public static void main(String[] args)
  {
    Rational r = new Rational( 3, 7 );
    Rational s = new Rational();
    Rational t = new Rational( 8, 5 );
    Rational u = new Rational( 1, 2 );
    Rational v = new Rational( 2, 3 );
    Rational w = new Rational( 8, 12 );
    Rational x = new Rational( 8, 12 );
    String y = "yoo";
    System.out.println("r: " + r );
    System.out.println("s: " +  s );
    System.out.println("t: " +  t );
    System.out.println( r + " as a floating pt approximation: "
    + r.floatValue() );
    System.out.println( s + " as a floating pt approximation: "
    + s.floatValue() );
    System.out.println( t + " as a floating pt approximation: "
    + t.floatValue() );
    System.out.print( r + " * " + t + " = ");
    r.multiply(t);
    System.out.println(r);
    System.out.print( r + " / " + t + " = ");
    r.divide(t);
    System.out.println(r);
    System.out.print( u + " + " + v + " = ");
    u.add(v);
    System.out.println(u);
    System.out.print( u + " - " + v + " = ");
    u.subtract(v);
    System.out.println(u);
    System.out.println( "\nNow testing static gcd...");
    System.out.println("expect 1");
    System.out.print("actual:\t");
    System.out.println( Rational.gcd(100,9) );
    System.out.println("expect 5");
    System.out.print("actual:\t");
    System.out.println( Rational.gcd(245,25) );
    System.out.println( "\nNow testing non-static gcd...");
    System.out.println("Rational r...");
    System.out.println( r );
    System.out.println("expect 40");
    System.out.print("actual:\t");
    System.out.println( r.gcd() );
    System.out.println("Rational t...");
    System.out.println( t );
    System.out.println("expect 1");
    System.out.print("actual:\t");
    System.out.println( t.gcd() );
    System.out.println("Rational w...");
    System.out.println( w );
    System.out.println("expect 4");
    System.out.print("actual:\t");
    System.out.println( w.gcd() );
    System.out.println( "\nNow testing reduce...");
    System.out.println("Rational r...");
    System.out.println( r );
    System.out.print( r + " in reduced form = ");
    r.reduce();
    System.out.println(r);
    System.out.println( "r > t: " +  r.compareTo(t) );
    System.out.println( "r > s: " +  r.compareTo(s) );
    System.out.println( "s > t: " +  s.compareTo(t) );
    //uncommenting the line below should trigger a runtime error
    //System.out.println( "s > y: " +  s.compareTo(y) );
    System.out.println( "v.equals(v): " + v.equals(v) );
    System.out.println( "v.equals(w): " + v.equals(w) );
    System.out.println( "w.equals(x): " + w.equals(x) );
  }
}
