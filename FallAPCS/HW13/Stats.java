/* Ricky Lin
   APCS1 Pd2
   HW13 -- May ah Hahv S'maur, Please?
   2017-10-10 */

public class Stats
{
	public static int mean(int a, int b)
	{
		//returns int mean of a and b
		int meanab = (a + b) / 2;
		return meanab;
	}

	public static double mean(double a, double b)
	{
		//returns int mean of a and b
		double abmean = (a + b) / 2;
		return abmean; //returns double mean of a and b
	}

	public static int max(int a, int b)
	{
		//if a larger, return a. Otherwise return b
		if (a >= b)
		{
			return a;
		}
		return b;
	}

	public static int max(int a, int b, int c)
	{
		return max(max(a, b), c);
	}

	public static double max(double a, double b)
	{
		//double version of max
		if (a >= b)
		{
			return a;
		}
		return b;
	}

	public static double max(double a, double b, double c)
	{
		//return max of 3
		return max(max(a, b), c)
	}

	public static int geoMean(int a, int b)
	{
		//multiplies a & b before taking the square root of it. Type-casted to int
		return (int) Math.sqrt(a * b);
	}

	public static int geoMean(int a, int b, int c)
	{
		//geo mean of 3 ints
		return (int) Math.power(a * b * c, (1.0 / 3));
	}

	public static double geoMean(double a, double b)
	{
		//double version without typecasting
		return Math.sqrt(a * b);
	}

	public static double geoMean(double a, double b, double c)
	{
		//geo mean of 3 doubles
		return Math.power(a * b * c, (1.0 / 3));
	}

	public static void main (String[] args)
	{
		System.out.println("- - - - Testing Mean Methods - - - -");
		System.out.println(mean(5,8));
		System.out.println("... expecting 6");
		System.out.println(mean(6.5,8.88));
		System.out.println("... expecting 7.69");
		System.out.println(mean(6.5,8));
		System.out.println("... expecting 7.25");

		System.out.println("- - - - Testing Max Method - - - -");
		System.out.println(max(5,6));
		System.out.println("... expecting 6");
		System.out.println(max(5.9,6.202));
		System.out.println("... expecting 6.202");
		System.out.println(max(5,6.202));
		System.out.println("... expecting 6.202");

		System.out.println("- - - - Testing GeoMean Method - - - -");
		System.out.println(geoMean(5,6));
		System.out.println("... expecting 5");
		System.out.println(geoMean(5.90,6.63));
		System.out.println("... expecting 6.25435848");
		System.out.println(geoMean(5,6.89));
		System.out.println("... expecting 5.869412236");
	}
}
