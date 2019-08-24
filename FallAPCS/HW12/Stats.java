/* Ricky Lin
   APCS1 Pd2
   HW12 -- stAtistically sPeaking
   2017-10-5 */

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

	public static double max(double a, double b)
	{
		//double version of max
		if (a >= b)
		{
			return a;
		}
		return b;
	}

	public static int geoMean(int a, int b)
	{
		//multiplies a & b before taking the square root of it. Type-casted to int
		int geoMeanab = (int) Math.sqrt(a * b);
		return geoMeanab;
	}

	public static double geoMean(double a, double b)
	{
		//double version without typecasting
		double abgeoMean = Math.sqrt(a * b);
		return abgeoMean;
	}

	public static void main (String[] args)
	{
		System.out.println(mean(7,6)); //expect 6
		System.out.println(mean(7.0,6.0)); //expect 6.5

		System.out.println(max(7,6)); //expect 7
		System.out.println(max(7.0,6.0)); //expect 7.0

		System.out.println(geoMean(7,6)); //expect 6
		System.out.println(geoMean(7.0,6.0)); //expect 6.something
	}
}
