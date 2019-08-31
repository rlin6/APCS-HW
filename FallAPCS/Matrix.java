/* Ricky Lin
   APCS1 pd02
   HW#59 Make the Matrix Work For You
   2017-12-21R */

/***
 * class Matrix -- models a square matrix
 *
 * BACKGROUND:
 * A matrix is a rectangular array.
 * Its dimensions are numRows x numColumns.
 * Each element is indexed as (row,column): 
 *  eg,
 *   for 2 x 3 matrix M:
 *        -       -
 *   M =  | a b c |
 *        | d e f |
 *        -       -
 *   ... d is at position (2,1) or M[2,1] 
 *
 * TASK:
 * Implement methods below, categorize runtime of each. 
 * Test in main method.
 ***/

public class Matrix {
	
	//constant for default matrix size
    private final static int DEFAULT_SIZE = 2;

    private Object[][] _matrix;

    //default constructor intializes a DEFAULT_SIZE*DEFAULT_SIZE matrix
    public Matrix() {
		_matrix = new Object[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    //constructor intializes an a*a matrix
    public Matrix( int size ) {
		_matrix = new Object[size][size];
    }


    //return size of this matrix, where size is 1 dimension
    private int size() {
		return _matrix.length;
    }


    //return the item at the specified row & column   
    private Object get( int row, int col ) {
		return _matrix[row - 1][col - 1];
    }


	//return true if this matrix is empty, false otherwise
    private boolean isEmpty( int row, int col ) {
		return get(row, col) == null;
    }


    //overwrite item at specified row and column with newVal
    //return old value
    private Object set( int row, int col, Object newVal ) {
		Object retVal = _matrix[row - 1][col - 1];
		_matrix[row - 1][col - 1] = newVal;
		return retVal;
    }


    //return String representation of this matrix
    // (make it look like a matrix)
    public String toString() {
		String retStr = "";
		for( int row = 0; row < size(); row++ ) {
	    	retStr += "| ";
	    	for( int col = 0; col < size(); col++ ) {
				retStr += _matrix[row][col] + " "; //get(row+1,col+1)
	    	}
	    	retStr += "|\n";  //new row
		}
		return foo;
    }


    //override inherited equals method
    //criteria for equality: matrices have identical dimensions,
    // and identical values in each slot
    public boolean equals( Object rightSide ) {
		boolean ans = false;

		if (this == rightSide) foo = true;  
		// checks for aliases  ex. m1.equals(m1) is true

		else if ( rightSide instanceof Matrix && size() == ((Matrix)rightSide).size() ) {
	    	Matrix right = (Matrix) rightSide; //for cleaner code later
	    	ans = true;
	    	outer:
	    	for( int row = 0; row < size(); row++ ) {
				for( int col = 0; col < size(); col++ ) {
		    		if ( !isEmpty(row, col) && ( !get(row, col).equals(right.get(row, col) ) ) ) {  //if val at [row, col] is not empty and val at [row, col] is not equal to rightSide's [row, col], set ans to false and break loop
						ans = false;
						break outer;
		    		}
		    		else if ( !( isEmpty(row, col) && right.isEmpty(row, col) ) ) {  //if val at [row, col] is not empty and right's [row, col] is empty, set ans to false and break loop
						ans = false;
						break outer;
		    		}
				}
	    	}
		}	
		return ans;
    }//end equals()



    //swap two columns of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapColumns( int c1, int c2  ) {
	c1 = c1-1;
	c2 = c2-1;
    
	for( int i = 0; i < size(); i++ ) {
	    set( i, c1, set( i, c2, get(i,c1) ) );
	}
    }//O(n) b/c must visit n rows


    //swap two rows of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapRows( int r1, int r2  ) 
    {
	r1 = r1-1;
	r2 = r2-2;
	Object [] temp = _matrix[r1];
	_matrix[r1] = _matrix[r2];
	_matrix[r2] = temp; 
    }//O(1)


    //            PHASE 2
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //returns copy of row r
    public Object[] getRow( int r )
    {
	Object[] row = _matrix[r-1];  //instantiate new object[] holding the contents of the 1st column
	for (int i = 1; i < size(); i++) { //iterates through the entire row except the 1st element which is already correctly set
	    row[i] = _matrix[r-1][i];  //set the element in the index to the element in the next column of the same row
	}
	return row;
    }//O(n)

    //replaces row r with 1D array newRow
    //returns old row
    public Object[] setRow( int r, Object[] newRow )
    {
        Object[] temp = this.getRow(r);  //instantiates new object array holding the row being replaced
       	for (int i = 0; i < size(); i++) {  //iterates through all the columns of row r
       	    _matrix[r-1][i] = newRow[i];  //find the value at row r-1 and the current column and change it into the new element in newRow
       	}
	return temp;  //return the replaced row
    }//O(n)

    public Object[] setCol( int c, Object[] newCol )
    {
	Object[] temp = _matrix[c-1];  //instantiates new object array holding column being replaced
      	for (int i = 0; i < size(); i++) {  //iterates through all the rows of column c
	    _matrix[i][c-1] = newCol[i];  //find the value at the current row and column c-1 and change it to the new element in newCol
       	}
	return temp;
    }//O(n)

    //M[i,j] -> M[j,i] for all i,j
    public void transpose()
    {
	Object temp;      //holds temporarily the replaced value
	for (int i = 1; i < size(); i++) {  //iterates through all the rows except the 1st one because it will be swapped by its partner down the line
	    for (int x = 0; x < i; x++) {  //iterates through the column that's less than the current row number feat. Jason's staircase model in the QAF post
		temp = _matrix[i][x];  //store one swapper's value
		_matrix[i][x] = _matrix[x][i];  //1st swap
		_matrix[x][i] = temp;  //2nd swap
	    }
	}	    
    }//O(n2)
    
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
   
    
    //main method for testing
    public static void main( String[] args ) 
    {
	Matrix m1 = new Matrix(); // 2 x 2
	Matrix m2 = new Matrix(10); // 10 x 10
	System.out.println("m1 size: " + m1.size());
	System.out.println("m2 size: " + m2.size());
	System.out.println("m1 get(1,1) : " + m1.get(1,1));
	System.out.println("m1 isEmpty(1,1) : " + m1.isEmpty(1,1));	

	Matrix x = new Matrix(2); // 2x2
	System.out.println(x);
	x.set(1,1,"how"); 
	x.set(1,2,"now"); 
	x.set(2,1,"bro"); 
	x.set(2,2,"cow"); 
	System.out.println(x);

	Matrix m3 = new Matrix(3); //3x3
	System.out.println(m3);

	for (Object o: x.getRow(1)) {
	    System.out.println(o);  //expects how and now
	}

	for (Object o: x.getRow(2)) {
	    System.out.println(o);  //expects bro and cow
	}

	Object[] w = new Object[2];
	for (int i = 0; i < 2; i++) {
	    w[i] = i;
	    System.out.println(w[i]);  //expects [0,1]
	}
	
	System.out.println(x);

	for (Object o: x.setRow(1,w)) {
	    System.out.println(o);  //expects 0 and 1
	}

	System.out.println(x);  //expects |0 1| |bro cow|

	for (Object o: x.setCol(1,w)) {
	    System.out.println(o); //expects 0 and 1
	}

	System.out.println(x);  //expects |0 1| |1 cow|

	Matrix m4 = new Matrix(10);

	int counter = 0;  //holds the value being added into m4
	
	for (int i = 1; i < 11; i++) {  //2 for loops to add numbers from 0-99 into m4
	    for (int z = 1; z < 11; z++) {
		m4.set(i,z,counter);
		counter++;
	    }
	}

	System.out.println(m4); //expects |0 1 2 3 4 5 6 7 8 9| ..... ordered by row
	m4.transpose();
	System.out.println(m4); //expects |0 10 20 30....| |1 11 12 13...|... ordered by columns
	
    }//end main()

}//end class Matrix
