/* Team IShouldNotCodeThis
   Ricky Lin, Soojin Choi, Shayan Chowdhury
   APCS1 pd2
   HW23 -- Imetay Otay Ineshay
   2017-10-25 Sun */

/***
 * class Pig
 * a Pig Latin translator
 * ~~~~~~~~~~~~~~~~~~~ SKELETON ~~~~~~~~~~~~~~~~~~~
 *           9
 *     ,--.-'-,--.
 *     \  /-~-\  /
 *    / )' a a `( \
 *   ( (  ,---.  ) )
 *    \ `(_o_o_)' /
 *     \   `-'   /
 *      | |---| |
 *      [_]   [_]
 *      PROTIP: Make this class compilable first,
 *      then develop and test one method at a time.
 *      NEVER STRAY TOO FAR FROM COMPILABILITY/RUNNABILITY!
 ***/
/*
  To-do:
  - Capitalization
      Check if the original word was capitalized and changes the new word to
      fit the convention. ~changeCapital method
  - Punctuation
      For end punctuation, we had the engToPig check the last index character
      if it was a letter or not. If not, it changed the word and added the
      punctuation at then end.
  - The letter y
      Checks to see if the first letter is y which makes it a consonant. If
      it is after the vowel, there is no need to check. Otherwise it is a vowel.
  */

public class Pig
{
    //Q: How does this initialization make your life easier?
    private static final String VOWELS = "aeiou";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";


    /*=====================================
    boolean hasA(String,String) -- checks for a letter in a String
    pre:  w != null, letter.length() == 1
    post: hasA("cat", "a") -> true
    hasA("cat", "p")       -> false
    =====================================*/
    public static boolean hasA(String w, String letter)
    {
        w = w.toLowerCase(); // convert string to lower case
        letter = letter.toLowerCase(); //convert the letter to lower case

        if ((w != null) && (letter.length() == 1))
        { //if the string is not null and the letter is actually 1 letter long
            return (w.indexOf(letter)) != -1; // return the boolean of whether or not the letter is in w (if != -1 meaning it's in the string)
        }

        return false; // otherwise return false
    } //end hasA()

    /*=====================================
    boolean isAVowel(String) -- tells whether a letter is a vowel
    precondition: letter.length() == 1
    =====================================*/
    public static boolean isAVowel(String letter)
    {
        letter = letter.toLowerCase(); // convert the letter to lowercase

        if (letter.length() == 1)
        { // if the letter is only a letter long
            return ((VOWELS.indexOf(letter)) != -1);
        } // return the truth value of whether or not letter is in vowels

        return false;  //otherwise return false
    }

    /*=====================================
    int countVowels(String) -- counts vowels in a String
    pre:  w != null
    post: countVowels("meatball") -> 3
    =====================================*/
    public static int countVowels(String w)
    {
        w = w.toLowerCase(); // convert the string to lowercase

        if (w != null) { //if w is not null
        int counter = 0;  //initialize counter

            for (int x = 0; x < w.length(); x++)
            { // for loop that initializes int x=0, runs while x is less than the length of w, and increments every run
                String checking = w.substring(x, x + 1); // creates a new string of that letter you are on
                if (isAVowel(checking))
                { //if that letter is a vowel
                counter += 1; // increase the vowel counter by one
                }
            }
            return counter; // return how many vowels there are
        }

        return -1; // if there are no vowels, return -1
    }

    /*=====================================
    boolean hasAVowel(String) -- tells whether a String has a vowel
    pre:  w != null
    post: hasAVowel("cat") -> true
    hasAVowel("zzz")       -> false
    =====================================*/
    public static boolean hasAVowel(String w)
    {
        w = w.toLowerCase(); //changes w into lowercase letters.

        if (w != null)
        { //checks if w is an empty string
            if (countVowels(w) > 0)
            { //if count vowels is more than one, there is a vowel.
                return true;
            }

            else
            { //otherwise there are no vowels; returns false.
                return false;
            }
        }
        return false;
    }

    /*=====================================
    String allVowels(String) -- returns vowels in a String
    pre:  w != null
    post: allVowels("meatball") -> "eaa"
    =====================================*/
    public static String allVowels(String w)
    {
        w = w.toLowerCase(); //changes w into lowercase letters.
        if ((w != null) && (hasAVowel(w)))
        { // if w is not null and has vowels
            String collection = "";
            for (int x = 0; x < w.length(); x++)
            {
                String curr = w.substring(x, x + 1); //it checks letter by letter
                if (isAVowel(curr))
                { //if it is a vowel or not.
                    collection += curr; //if it is, it is added to the string of vowels.
                }
            }
            return collection; //and returns the list of vowels
        }
        return "";
    }

    //pre: w != null
    //post: firstVowel("") --> ""
    //      firstVowel("zzz") --> ""
    //      firstVowel("meatball") --> "e"
    public static String firstVowel(String w)
    {
        if (w != null)
        { //if w is not null
            if (! (allVowels(w).equals("")) )
            { // if all the vowels is not equal to an empty string.
                return allVowels(w).substring(0,1); //return the first vowel in the list.
            }
        }
        return "";
    }

    //pre: w != null and w.length() > 0
    //post: beginsWithVowel("apple")  --> true
    //      beginsWithVowel("strong") --> false
    public static boolean beginsWithVowel(String w)
    {
        if ((w != null) && (w.length() > 0))
        { //if w is not null and is not a length of 0
            String firstLetter = w.substring(0,1); //takes the first letter of w
            return isAVowel(firstLetter); //checks if it is a vowel or not.
        }
        return false;
    }

    public static String engToPig(String s)
    {
        String collection = "";

        while (s.indexOf(" ") != -1)
        { //checks whether or not the string has a space.
            int x = s.indexOf(" "); //sets x to the index of the space.
            collection += wordToPig(s.substring(0, x)) + " "; //adds the word converted to pig latin and a space
            s = s.substring(x + 1); //updates the original string without the first word or the space.
        }

        //This if sorts through the punctuation at the end of the sentence. It also is used to convert single words into pig latin.
        if (ALPHABET.indexOf(s.substring(s.length() - 1, s.length())) == -1 && VOWELS.indexOf(s.substring(s.length() - 1, s.length())) == -1)
        {
            String lastWord = wordToPig(s.substring(0,s.length()-1)); //takes the last word without the punctuation and converts to pig latin
            collection += lastWord + s.substring(s.length()-1, s.length());//adds the last word and the puctuation to the collection.
        }

        else
        {
            collection += wordToPig(s);//if no puncutation, just converts the last word to pig latin.
        }

        return collection;
    }

    public static void main( String[] args )
    {
        //test cases with predicted outcome
        System.out.println("= = = = = Testing hasA = = = = =");
        System.out.println(hasA("cat","p"));//expecting false
        System.out.println(hasA("cat","a"));//expecting true
        System.out.println("= = = = = Testing isAVowel = = = = =");
        System.out.println(isAVowel("d"));//expecting false
        System.out.println(isAVowel("a"));//expecting true
        System.out.println("= = = = = Testing countVowels = = = = =");
        System.out.println(countVowels("meatball"));//expecting 3
        System.out.println(countVowels("catcomba"));//expecting 3
        System.out.println("= = = = = Testing hasAVowel = = = = =");
        System.out.println(hasAVowel("cat"));//expecting true
        System.out.println(hasAVowel("zzz"));//expecting false
        System.out.println("= = = = = Testing allVowels = = = = =");
        System.out.println(allVowels("meatball"));//expecting eaa
        System.out.println(allVowels("catcomba"));//expecting aoa
        System.out.println(firstVowel("catcomba"));//expecting a
        System.out.println(firstVowel("ythw"));//expecting ""
        System.out.println("= = = = = Testing firstVowel = = = = =");
        System.out.println(firstVowel(""));//expecting ""
        System.out.println(firstVowel("zzz"));//expecting ""
        System.out.println(firstVowel("meatball"));//expecting "e"
        System.out.println("= = = = = Testing beginsWithVowel = = = = =");
        System.out.println(beginsWithVowel("apple"));//expecting true
        System.out.println(beginsWithVowel("strong"));//expecting false
        System.out.println("= = = = = Testing engToPig = = = = =");
        System.out.println(engToPig("Apple"));//expecting Appleway
        System.out.println(engToPig("Strong"));//expecting Ongstray
        System.out.println(engToPig("Tryst"));//expecting Ysttray
        System.out.println(engToPig("Yellow"));//expecting Ellowyay
        System.out.println(engToPig("Candy"));//expecting Andycay
        System.out.println(engToPig("Apply"));//expecting Applyway
        System.out.println(engToPig("Have a good day!"));//expecting Avehay away oodgay ayday!
    }//end main()

}//end class Pig
