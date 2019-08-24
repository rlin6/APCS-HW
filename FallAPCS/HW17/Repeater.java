/* Ricky Lin
   APCS1 pd.2
   HW#17: Do I repeat myself? While and Recursions
   2017-10-16 */

public class Repeater
{
	public static String fenceW(int numPosts)
	{

		//String to be returned, when numPosts is 0, it will simply return empty string
		String fence = "";

		//while loop that runs while numPosts is greater than 0
		while (numPosts > 0)
		{
			//base case: when numPosts is 1, add a single |
			if (numPosts == 1)
			{
				fence += "|";
			}

			//otherwise add |--
			else
			{
				fence += "|--";
			}

			//decreases the numPosts for another run in the while loop
			numPosts -= 1;
		}

		return fence;
	}

	public static String fenceR(int numPosts)
	{
		//Recursive that runs while numPosts is greater than 1
		if (numPosts > 0)
		{
			if (numPosts == 1)
			{
				//base case: returns | at the end of recursive
				return "|";
			}
		}

		// adds --| to the numPosts before it
		return fenceR(numPosts - 1) + "--|";
	}

	public static void main(String[] args)
	{
		//test cases for the two methods
		System.out.println(fenceR(6));
		System.out.println(fenceW(0));
		System.out.println(fenceR(1));
		System.out.println(fenceW(4));
	}
}
