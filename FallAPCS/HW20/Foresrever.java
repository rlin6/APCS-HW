/* Ricky Lin
   APCS1 pd2
   HW#20: For the Lulz Love of Strings
   2017-10-19 */

public class Foresrever
{

	// method that takes int posts and makes a fence using a for loop
	public static String fenceF (int posts)
	{

		// variable holding the String of fence
		String fence = "";

		// for loop initializing counter = 1 that runs while the counter is less than or equal to posts and updates by adding one to the counter
		for (int counter = 1; counter <= posts; counter++)
		{

			// add one fence post for the base case of 1
			if (counter == 1)
			{
				fence += "|";
			}

			// otherwise, add --| to the initial starting fence post
			else
			{
				fence += "--|";
			}
		}

		// return the final string
		return fence;
	}

	// method that uses a for loop to take the last letter of str and adds it to a new string
	public static String reverseF (String str)
	{

		//new string variable
		String newString = "";

		// initialize counter to one, runs while the counter is less than the length of the string, and updates by adding one to the counter
		for (int counter = 0; counter < str.length(); counter++)
		{

			// diff holds the difference between the length of the string and the counter
			int diff = str.length() - counter;

			//when the counter is 0, add the last value to the newString
			if (counter == 0)
			{
				newString += str.substring(str.length() - 1);
			}

			// otherwise, add the string between the value of 1 less than the difference and the difference
			else
			{
				newString += str.substring((diff - 1), diff);
			}
		}

		//return the newString
		return newString;
	}

	//recursive version
	public static String reverseR (String str)
	{

		//create a newString to hold new String
		String newString = "";

		//if the string is more than 1 letter, return a shortened string with the last character gone
		if (str.length() >= 2)
		{
			newString = str.substring(0, str.length() - 1);
		}

		//base case: return that last letter
		else
		{
			return str.substring(str.length() - 1);
		}

		//return the last letter of the string plus the last letter of the edited string and so on
		return str.substring(str.length() - 1) + reverseR(newString);
	}

	//main method that prints out test cases of each method
	public static void main (String[] args)
	{
		System.out.println("==============fenceF Results==============");
		System.out.println(fenceF(1));   //expect "|"
		System.out.println(fenceF(0));   //expect ""
		System.out.println(fenceF(5));   //expect "|--|--|--|--|"
		System.out.println("==============reverseF Results==============");
		System.out.println(reverseF("desserts"));   //expect "stressed"
		System.out.println(reverseF("racecar"));   //expect "racecar"
		System.out.println(reverseF("hi"));   //expect "ih"
		System.out.println("==============reverseR Results==============");
		System.out.println(reverseR("desserts"));   //expect "stressed"
		System.out.println(reverseR("racecar"));   //expect "racecar"
		System.out.println(reverseR("hi"));   //expect "ih"
	}
}
