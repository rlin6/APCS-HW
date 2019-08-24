/* Ricky Lin
   APCS1 Pd2
   HW13 -- May ah Hahv S'maur, Please?
   2017-10-10 */

public class StatsDriver
{
	public static void main (String[] args)
	{
		System.out.println("- - - - Testing Mean Methods - - - -");
		System.out.println(Stats.mean(5,8));
		System.out.println("... expecting 6");
		System.out.println(Stats.mean(6.5,8.88));
		System.out.println("... expecting 7.69");
		System.out.println(Stats.mean(6.5,8));
		System.out.println("... expecting 7.25");
		System.out.println("- - - - Testing Max Method - - - -");
		System.out.println(Stats.max(5,6));
		System.out.println("... expecting 6");
		System.out.println(Stats.max(5.9,6.202));
		System.out.println("... expecting 6.202");
		System.out.println(Stats.max(5,6.202));
		System.out.println("... expecting 6.202");

		System.out.println("- - - - Testing GeoMean Method - - - -");
		System.out.println(Stats.geoMean(5,6));
		System.out.println("... expecting 5");
		System.out.println(Stats.geoMean(5.90,6.63));
		System.out.println("... expecting 6.25435848");
		System.out.println(Stats.geoMean(5,6.89));
		System.out.println("... expecting 5.869412236");
	}
}
