//Honk Honk Radish - Ricky Lin and Wubin Peco
//APCS-2 pd. 2
//HW07 - A Man, A Plan, A Canal: Panama!
//2018-02-13
//happy valentines day <3

public class Resrever {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	//=============HELPER FUNCTIONS===================
	public static String pickRandomChar() {
		int random = (int)(Math.random() * 24);
		return ALPHABET.substring(random, random + 1);
	}

	public static String genRandomString(int size) {
		String retString = "";
		for (int i = 0; i < size; i++) {
			retString += pickRandomChar();
		}
		return retString;
	}
	//=================================================

	public static String linReverse(String input) {
		String retStr = "";	//declare empty return string

		for (int i = 0; i < input.length(); i++) {
			retStr += input.substring(input.length() - i - 1, input.length() - i);	//loop thru string backwards, adding each char to the return string
		}
		return retStr;
	}
	//Runs in O(n) time because it simply loops through the string backwards, one iteration for each letter/character in the string
	
/*	public static String logReverse(String input) {

		if (input.length() <= 1) {
			return input;
		} else {
			String firstHalf = "";
			String secondHalf = "";
			
			for (int i = 0; i < input.length() - 1; i++) {
				if (i <  input.length() / 2) 
					firstHalf += input.substring(i, i + 1);
				else
					secondHalf += input.substring(i, i + 1);
			}
			return logReverse(firstHalf) + logReverse(secondHalf);
		}
	}
*/
 	//It is NOT possible for it to run in O(log n), with base 2, because, as discussed on the handy-dandy QAF,
	//even if we do split as we did in merge sort, the problem arises when we try to add the strings together
	//Adding strings is a constant time process, but as Raunauk said, we repeat it n times, where n is the length of the string
	//Thus, it can only truly be run in O(n) complexity :)
	
	public static void main (String[] args) throws InterruptedException {

		System.out.println("stressed");
		System.out.println("=======LINEAR REVERSAL======");
		System.out.println(linReverse("stressed"));
		
		/* BAR OF NOT WORKING CODE!
		System.out.println("stressed");
		System.out.println("=======LOG REVERSAL=======");
		System.out.println(logReverse("stressed"));
		*/

		System.out.println("Reversing twenty random strings!");
		Thread.sleep(1000);		//the pause is for the user to register what was being printed and see its reversal as to confirm that it truly reverses strings

		for (int i = 0; i < 20; i++) {
			String temp = genRandomString(i);
			System.out.println("\nString size " + i + "\n" + temp);
			Thread.sleep(1000);
			System.out.println("======REVERSING=======");
			Thread.sleep(1000);
			System.out.println(linReverse(temp));
			Thread.sleep(1000);
		}
		
	}
}

/*      @@@@@@           @@@@@@
      @@@@@@@@@@       @@@@@@@@@@
    @@@@@@@@@@@@@@   @@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@
 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
      @@@@@@@@@@@@@@@@@@@@@@@@@@@
        @@@@@@@@@@@@@@@@@@@@@@@
          @@@@@@@@@@@@@@@@@@@
            @@@@@@@@@@@@@@@
              @@@@@@@@@@@
                @@@@@@@
                  @@@
                   @
*/ 
