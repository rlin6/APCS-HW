/* Ricky Lin, Soojin Choi Team EucLIT
   APCS1 pd2
   HW19 -- gcd 3 ways
   2017-10-18 W */   

public class Stats 
{

    //---------- Methods -------------
    //mean takes in two integers and adds the two and divides the sum by 2 ;
    //Returns the integer or double of the mean.
	public static int mean(int a, int b) 
	{
		int average = (a + b);
		return average / 2;
	}
	
	public static double mean(double a, double b) 
	{
		double average = (a + b);
		average /= 2;
		return average;
	}
	
    //max takes in two integers or doubles and returns the greater of the two.
	public static int max(int a, int b) 
	{
		if (a > b) 
		{
			return a;
		}
		return b;
    }
	
	public static double max(double a, double b)
	{
		if (a > b) 
		{
			return a;
		}
		return b;
	}
		
    //The new max that takes and compares three ints or doubles. Returns the max
	public static int max(int a, int b, int c) 
	{
		int maxAB = max(a, b);
		int maxABC = max(maxAB, c);
		return maxABC;
	}
	
	public static double max(double a, double b, double c) 
	{
		double maxAB = max(a, b);
		double maxABC = max(maxAB, c);
		return maxABC;
	}
	
    /* geoMean takes in two integers. The two integers are multiplied;
       Take the square root and trunkated to an integer.*/
	public static int geoMean(int a, int b) 
	{
		int average = (a * b);
		average = (int) Math.sqrt(average);
		return average;
	}

    /* geoMean takes two doubles. Returns the geometric mean and returned in its original form.*/
	public static double geoMean(double a, double b) 
	{
		double average = (a * b);
		average = Math.sqrt(average);
		return average;
    }
    
	public static int geoMean(int a, int b, int c) 
	{
		int product = a * b * c;
		return (int) Math.pow(product, (1.0/3.0));
	}
	
	public static double geoMean(double a, double b, double c) 
	{
		double product = a * b * c;
		return Math.pow(product, (1.0/3.0));
    }

    //Checks to see if any, if not both, are equal to zero. 
	public static Boolean checkForZero(int x, int y) 
	{
		if ((x == 0) || (y == 0)) 
		{
			return true;
		}
		return false;
	}
	
    /*Takes in 2 ints to find the common divisor. Checks each number going up from one until it hits the smaller of the two values. MaxDivisor keeps updating if a number not one is a divisor.*/
	public static int gcd(int a, int b) 
	{
		int divisor = 1;
		int MaxDivisor = 1;
		
		if (checkForZero(a, b))
		{
			return 0;
		}

		else 
		{
			while (divisor <= Math.min(a, b)) 
			{
				if (((a % divisor) == 0) && ((b % divisor) == 0)) 
				{
			    	MaxDivisor = divisor;
					divisor += 1;
				}
				else 
				{
			    	divisor += 1;
				}
			}
		
			return MaxDivisor;
		}
    }

    /*Takes in two ints and checks if the bigger number is divisible by the smaller number. if it is divisible, then the smaller number is returned. Otherwise the smaller number is subltracted by the bigger number becoming the second var. Both the original smaller number and new number are then sent back to be checked until one reaches zero.*/
	public static int gcdER(int a, int b) 
	{
		
		int holder = a; //to keep the orignal value of a 
		a = max(a, b);
		b = Math.min(holder, b);
		
		if (b == 0) 
		{
			return 0;
		}
		
		else 
		{
			
			if ((a % b) == 0) 
			{
				return b;
			}
			
			else 
			{
				a = a - b;
				
				if ((a == 0) || (b == 0)) {
					if (b == 0) 
					{
						return a;
					}
					
					else 
					{
						return b;
					}
				}
				
				else 
				{
			    	return gcdER(a,b);
				}
		    }
		}
	}
	
    /*Takes in two numbers and the bigger is divisible by the smaller number. If this is false, it continues to subtract the smaller from the bigger number and runs until it reaches 0. If one of the number reaches 0, the other number is the greatest common divisor.*/
	public static int gcdEW(int a, int b)
	{

		int holder = a; //keeps the original value of a
		a = max(a, b);
		b = Math.min(holder, b);
		
		if (b == 0) 
		{
			return 0;
		}
		
		if ((a % b) == 0) 
		{
			return b;
		}
		
		else 
		{
			while ((a != 0) && (b != 0)) 
			{
				holder = b;
				b = a - b;
				a = max (holder, b);// a is always the highest number
				b = Math.min(holder,b);//set b to the smaller of the two.
				//System.out.println(a + "---" + b);
				//System.out.println("*****************8");
		    }
			
			if (a == 0) 
			{
				return b;
			}
			
			else 
			{
				return a;
			}
		}
    }
 
    //Main Method 
	public static void main(String [] args) 
	{
		System.out.println("- - - - Testing Mean Methods - - - -");
		System.out.println(mean (5,8));
		System.out.println("... expecting 6");
		System.out.println(mean (6.5,8.88));
		System.out.println("... expecting 7.69");
		System.out.println(mean (6.5,8));
		System.out.println("... expecting 7.25");
		
		System.out.println("- - - - Testing Max Method - - - -");
		System.out.println(max (5,6));
		System.out.println("... expecting 6");
		System.out.println(max (5.9,6.202));
		System.out.println("... expecting 6.202");
		System.out.println(max (5,6.202));
		System.out.println("... expecting 6.202");
		
		System.out.println("- - - - Testing GeoMean Method - - - -");
		System.out.println(geoMean (5,6));
		System.out.println("... expecting 5");
		System.out.println(geoMean (5.90,6.63));
		System.out.println("... expecting 6.25435848");
		System.out.println(geoMean (5,6.89));
		System.out.println("... expecting 5.869412236");	       	
		
		System.out.println("========== Testing gcd ===========");
		System.out.println(gcd(15,20));
		System.out.println(gcd(20,15));
		System.out.println(gcd(15,17));
		System.out.println(gcd(15,0));
		
		System.out.println("========== Testing gcdER ===========");
    	System.out.println(gcdER(15,20));
		System.out.println(gcdER(20,15));
		System.out.println(gcdER(15,17));
		System.out.println(gcdER(15,0));
		
		System.out.println("========== Testing gcdEW ===========");
		System.out.println(gcdEW(15,20));
		System.out.println(gcdEW(20,15));
		System.out.println(gcdEW(15,17));
		System.out.println(gcdEW(15,0));	
    }   
}
