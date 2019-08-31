//Ricky Lin
//APCS1 pd2
//HW#38 -- Put It Together
//2017-11-22

/* I made a new array that would be either defaultly constructed as a 5 x 5 array or arguments can be given to make it a certain length and width. The array is filled up by random 3-4 letter words, leaving the rare chance of a repeat, by taking random letters from a string of letters and adding them together to make a word. I made a layout method that lays the columns of the array by putting space between each word and the rows on a different line. Then I made the swap method that would take the coordinates of the 2 words and swap them with each other with the help of a temporary holder. Finally, my main method made a new 10 x 10 array filled with random words. The array is then printed using layout. Then, the user gets to pick the row and column of the 1st word, which is then recorded int r1 and c1, and then the 2nd word, recorded into r2 and c2. It then runs swap to change the two locations with these variables and reprints the new array. */

import cs1.Keyboard;

public class Swapper
{
  private final String letters = "qwertyuiopasdfghjklzxcvbnm";  //letters to work with

  private String[][] a;   //Array

  public Swapper()
  { //def constructor populates
    a = populate(4,4);  //default 5 by 5 string
  }

  public String[][] populate(int row, int column)
  {
    String[][] p = new String[row][column];  //creates an array based on given dimensions
    for (int r = 0; r < p.length; r++)
    { //get to the row
      for (int c = 0; c < p[0].length; c++)
      { //get to column
        p[r][c] = generator();  //set cell to a generated word
      }
    }
    return p;
  }

  public String generator()
  {
    if (Math.random() > 0.5)
    { //50% 3 letters
      int one = (int) (Math.random() * 26); //stores a random value for a letter place
      int two = (int) (Math.random() * 26);
      int three = (int) (Math.random() * 26);
      return letters.substring(one, one + 1) + letters.substring(two, two + 1) + letters.substring(three, three + 1);  //return word made of 3 random letters
    }
    else
    {  //50% 4 letters
      int one = (int) (Math.random() * 26); //stores random value for letter place
      int two = (int) (Math.random() * 26);
      int three = (int) (Math.random() * 26);
      int four = (int) (Math.random() * 26);
      return letters.substring(one, one + 1) + letters.substring(two, two + 1) + letters.substring(three, three + 1) + letters.substring(four, four + 1);  //return word made of 4 random letters
    }
  }

  public void layout()
  {
    System.out.println();
    for (String[] row: a)
    {
      for (String word: row)
      { //look at each word
        System.out.print(word + " ");  //print out word with space between each one
      }
      System.out.println();  //move to next line
    }
  }

  public void swap(int r1, int r2, int c1, int c2)
  {
    String holder = a[r1][c1];  //holds the location for 1st word
    a[r1][c1] = a[r2][c2];  //moves 1st to 2nd
    a[r2][c2] = holder;  //moves 2nd to 1st
  }

  public static void main(String[] args) {
    int r1,r2,c1,c2;  //init placekeepers
    Swapper arr = new Swapper();  //creates up to 10 x 10 array
    arr.layout();  //print out the array
    System.out.println("\nPick 2 to switch");
    for (;;) {
      System.out.print("\nFirst One's Row: ");
      r1 = Keyboard.readInt() - 1 ;   //stores the first word's row number
      System.out.print("First One's Column: ");
      c1 = Keyboard.readInt() - 1 ;   //stores first word's column number
      System.out.print("Second One's Row: ");
      r2 = Keyboard.readInt() - 1 ;    //stores second word's row number
      System.out.print("Second One's Column: ");
      c2 = Keyboard.readInt() - 1 ;   //stores second word's column number
      arr.swap(r1,r2,c1,c2);     //swap based on stored values
      arr.layout();   //print new array
    }
  }
}
