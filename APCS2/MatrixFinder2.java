//Team: Anything (Ricky Lin, Susan Lin, Bo Hui Lu)
//APCS2 pd2
//Hw03--I am still searching
//2018-02-01

public class MatrixFinder {
    /*
      method summary:
      
      params: int[][] and int

      if target found, returns a string of coordinates
      if target not found, return "(-1,-1)"

      by using the bottom left corner as a start place for the search, 
      if the current int is greater than target, we move up a row. 
      if the current int is less than target, we move right a column. 

      the bottom left or top right corner provides a position where either 
      but not both the row and column increases/decreases. 

      if the row is at the topmost or if the column is at the rightmost, 
      then the target does not exist within the given array. 
     */

    public static String finder(int[][] a, int target){

	//we're using bottom left corner as our start point
	int col = 0;
	int row = a.length -1;
	while( target != a[row][col]) {

	    if(a[row][col] > target)
		row--;

	    else
		col++;
		    
	    //prevent infinte looping/break out of loop
	    if(row < 0 || col > a.length -1)
		return "(-1,-1)";
	    
	}//ends loop
	
	return "(" + row + "," + col + ")";	
    }


    public static void main(String[] args){

	int[][] test = {
	    {1,2,5},
	    {3,6,7},
	    {4,8,9},
	};

	//test cases
	System.out.println("testing testing testing");
	System.out.println(finder(test,6)); //random test case
	System.out.println(finder(test,5)); //worst case
	System.out.println(finder(test,10));//will it break out of loop properly

	int[][] testRepeat = {
	    {1,2,3},
	    {2,3,4},
	    {3,4,5},
	};
	System.out.println("Testing case where there are multiple occurences of the target");
	long x = 0;
	long y = 0;
	for (int i = 0; i < 1000000000; i++) {
	    x += System.currentTimeMillis();
	    System.out.println(finder(testRepeat,2)); //testing special case
	    //System.out.println(finder(testRepeat,3)); //testing special case
	    //System.out.println(finder(testRepeat,4)); //testing special case
	    y += System.currentTimeMillis();
	}
	long f = (y - x) / 1000000000;
	System.out.println(f);
    }






    
    
}
