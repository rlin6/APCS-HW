/* Ricky Lin
   APCS1 Pd2
   Hw#21 Karmacomma
   2017-10-23 */

public class Commafier
{
	public static String commafyR(int a)
	{
		//vars for last digits
		String end = "";

		// if no need for commas, return the number itself (base case)
		if (a < 1000)
		{
			return "" + a;
		}

		//last digits will equal the remainder when a is divided by 1000
		end += a % 1000;

		// Check to see if it ends with bunch of zeroes
		if (end.equals("0"))
		{
			end = "000";
		}

		//call commafyR recursively on the next section of a that needs commas and attach the commas and ending digits
		return commafyR(a / 1000) + "," + end;
	}

	public static String commafyF(int a)
	{

		//if number is less than 1000 return the number as string
		if (a < 1000)
		{
			return "" + a;
		}

		//create variables for initial value, the last portion needing comma, and a placeholder for that portion
		String first = "" + a;
		String comma1 = first.substring(first.length() - 3);
		String comma2 = comma1;

		//for loop initializing that beforeComma is equal to the length string without the last comma section that runs while beforeComma is greater than or equal to 0 and decrements by 3 each run to account for the commas being added in every 3 digits
		for (int beforeComma = first.length() - 4; beforeComma >= 0; beforeComma -= 3)
		{

			//if at the beginning of the number, comma2 becomes the first numbers that are not separated by a comma + a comma + the rest of the number
			if (beforeComma < 3)
			{
				comma2 = first.substring(0, beforeComma + 1) + "," + comma2;
			}

			// if in the middle, comma2 becomes the numbers up to beforeComma + a comma plus the end of the number
			else
			{
				comma2 = first.substring(beforeComma - 2, beforeComma + 1) + "," + comma2;
			}
		}
		return comma2;
	}

	public static void main (String[] args)
	{
		System.out.println(commafyR(1));
		System.out.println(commafyR(100));
		System.out.println(commafyR(1000));
		System.out.prinln(commafyF(1));
		System.out.println(commafyF(100));
		System.out.println(commafyF(1000));

		for (String s: args)
		{
			System.out.println(commafyR(Integer.parseInt(s)));
			System.out.println(commafyF(Integer.parseInt(s)));
		}
	}
}
