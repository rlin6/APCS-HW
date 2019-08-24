/* Ricky Lin
   APCS1 pd2 
   HW#16 Get It While You Can
   2017-10-12 */

public class Coin 
{
    
    //Create all the instance variables
    private double value;
    private String upFace;
    private String name;
    private int flipCtr;
    private int headsCtr;
    private int tailsCtr;
    private double bias;
    
    //Default constructor
    public Coin () 
    {
	    upFace = "heads";
	    bias = 0.5;
    }

    //1st Overloaded
    public Coin (String denom) 
    {
        this();  //call default
        name = denom;
        assignValue();
    }

    //2nd Overloaded
    public Coin (String denom, String face) 
    {
	    this(denom);  //call overloaded
	    upFace = face;
    }

    //Accessors
    public String getupFace() 
    {
	    return upFace;
    }
    
    public int getflipCtr() 
    {
	    return flipCtr;
    }

    public int getValue() 
    {
	    return value;
    }

    public int getheadsCtr() 
    {
	    return headsCtr;
    }

    public int gettailsCtr() 
    {
	    return tailsCtr;
    }

    //mutators
    public int setheadsCtr(int x) 
    {
	    int old = headsCtr;
        headsCtr = x;
	    return old;
    }

    public int settailsCtr(int y) 
    {
	    int old = tailsCtr;
        tailsCtr = y;
	    return old;
    }
    
    public int setflipCtr(int z) 
    {
	    int old = flipCtr;
        flipCtr = z;
	    return old;
    }
    
    //Flip the coin based on bias probability and add 1 counter to flip counter and the face it lands on
    public String flip() 
    {
        double ran = Math.random();
        
        if (ran < 0.5) 
        {
		    upFace = "heads";
		    headsCtr += 1;
        }
        
        else 
        {
	        upFace = "tails";
	        tailsCtr += 1;
	    }
    
        flipCtr += 1;
        return upFace;
    }

    // toString to return the name -- heads/tails
    public String toString() 
    {
	    return (name + "--" + upFace);
    }

    // equals method using the instance of coin as a parameter
    public Boolean equals (Coin other) 
    {
	    return upFace.equals(other.upFace);
    }

    // assignValue to give each coin a value 
    public double assignValue() 
    {
        if (name.equals("penny")) 
        {
	        value = 0.01;
	    }
        else if (name.equals("nickel")) 
        {
	        value = 0.05;
	    }
        else if (name.equals("dime")) 
        {
	        value = 0.10;
        }   
        else if (name.equals("quarter")) 
        {
	        value = 0.25;
	    }
        else if (name.equals("half dollar")) 
        {
	        value = 0.50;
	    }
        else if (name.equals("dollar")) 
        {
	        value = 1.00;
	    }
	    return value;
    }

    public void reset() 
    {
        flipCtr = 0;
        tailsCtr = 0;
        headsCtr = 0;
    }

    public void flipInfo() 
    {
    	System.out.println("============================");
    	System.out.println("Number of tails: " + tailsCtr);
    	System.out.println("Number of heads: " + headsCtr);
    	System.out.println("Number of flips: " + flipCtr);
    }
} 
    
