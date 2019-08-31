/******************************
 * class TwoDimArray
 * (skeleton)
 * practice working with 2D arrays
 ******************************/

/* Ricky Lin
   APCS1 pd2
   HW34 -- 2D arrays
   2017-11-17 */

public class TwoDimArray
{
  //postcond: prints each row of 2D int array a on its own line
  //          uses a FOR loop
  public static void print1(int[][] a)
  {
    for (int i = 0; i < a.length; i++)
    { //look into each row of array
      for (int w = 0; w < a[i].length; w++)
      { //looks into each number in that row
        System.out.println(a[i][w]); //prints out the number identified by row and column
      }
    }
  }

  //postcond: prints each row of 2D int array a on its own line
  //          uses a FOREACH loop
  public static void print2( int[][] a )
  {
    for (int[] s: a)
    {              //looks into each row in 2-D array
      for (int k: s)
      {            //looks into each number in said row
        System.out.println(k);  //prints out each number
      }
    }
  }

  //postcond: returns sum of all items in 2D int array a
  public static int sum1( int[][] a )
  {
    int sum = 0; //holds sum
    for (int [] s: a)
    { //looks into each column in 2-D array
      for (int k: s)
      { //looks into each number in each column
        sum += k; //adds number to sum
      }
    }
    return sum;
  }

  //postcond: returns sum of all items in 2D int array a
  //          * uses helper fxn sumRow
  public static int sum2( int [][] m )
  {
    int sum = 0; //holds sum
    for (int i = 0; i < m.length; i++)
    { //looks into each row of m
      sum += sumRow(i,m); //adds the sum of each row into the sum
    }
    return sum;
  }


  //postcond: returns sum of all items on row r of 2D int array a
  //          uses a FOR loop
  public static int sumRow( int r, int[][] a )
  {
    int sum = 0; //holds sum
    for (int w = 0; w < a[r].length; w++)
    { //looks into row r
      sum += (a[r][w]); //adds the numbers in row r to sum
    }
    return sum;
  }


  //postcond: returns sum of all items on row r of 2D int array a
  //          uses a FOREACH loop
  public static int sumRow2(int r, int[][] m)
  {
    int sum = 0; //holds sum
    for (int s: m[r])
    { //looks at the numbers in row r
      sum += s; //add to sum
    }
    return sum;
  }

  public static void main( String [] args )
  {
    int [][] m1 = new int[4][2];
    int [][] m2 = { {2,4,6}, {3,5,7} };
    int [][] m3 = { {2}, {4,6}, {1,3,5} };
    print1(m1);  //expect 8 0s
    print1(m2);  //expect 2 4 6 3 5 7
    print1(m3);  //expect 2 4 6 1 3 5

    print2(m1);  //expect 8 0s
    print2(m2);  //expect 2 4 6 3 5 7
    print2(m3);  //expect 2 4 6 1 3 5

    System.out.print("testing sum1...\n");
    System.out.println("sum m1 : " + sum1(m1));  //expect 0
    System.out.println("sum m2 : " + sum1(m2));  //expect 27
    System.out.println("sum m3 : " + sum1(m3));  //expect 21

    System.out.print("testing sum2...\n");
    System.out.println("sum m1 : " + sum2(m1));  //expect 0
    System.out.println("sum m2 : " + sum2(m2));  //expect 27
    System.out.println("sum m3 : " + sum2(m3));  //expect 21
  }
}//end class TwoDimArray
