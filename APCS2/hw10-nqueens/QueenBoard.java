/***
Ricky lin
APCS2 pd02
HW #10: [Freddie Mercury, Brian May, Roger Taylor, John Deacon] x n
2018-02-28 W

 * class QueenBoard
 * Generates solutions for N-Queens problem.
 */

public class QueenBoard
{
    private int[][] _board;
    private int _size;  //keeps track of the size

    public QueenBoard( int size )
    {
	_board = new int[size][size];
  _size = size;
    }


    /***
     * precondition: board is filled with 0's only.
     * postcondition:
     * If a solution is found, board shows position of N queens,
     * returns true.
     * If no solution, board is filled with 0's,
     * returns false.
     */
    public boolean solve()
    {
	return solveH(0);  //calls the recursion starting from the first column
    }


    /**
     *Helper method for solve.
     */
    private boolean solveH( int col )
    {
      if (col == _size) {  //if you reach end of the board successfully you are finished and return true
        return true;
      }
      for (int r = 0; r < _size; r++) {  //iterates through each row
        if (addQueen(r,col)) {  //if you can add a queen at the location, add it
          addQueen(r,col);
          if (solveH(col + 1)) { //recursive call to the next column, if it is solved in the next column, return true, if it is not solved, continue with the iteration loop
            return true;
          }
          removeQueen(r,col); //if the recursive call fails, remove that queen and move to the next row
        }
      }
	return false;  //if it's not solvable
    }


    public void printSolution()
    {
	/** Print board, a la toString...
	    Except:
	    all negs and 0's replaced with underscore
	    all 1's replaced with 'Q'
	*/
	String sol = "";
  for (int r = 0; r < _size; r++) {
    for (int c = 0; c < _size; c++) {
      if (_board[r][c] == 1) {
        sol += "Q";
      }
      else {
        sol += "-";
      }
      sol += "  ";
    }
    sol += "\n";
  }
System.out.println(sol);
}


    //================= YE OLDE SEPARATOR =================

    /***
     * If the location is on the board, doesn't have a queen, and isn't in range of one, it will place a queen at the location and turn all the locations it is able to reach to the right of it into a negative number
     * precondition: The location is on the board and there is not already a queen there or it's not a location that they can attack
     * postcondition: returns true if the queen is placed and false otherwise
     */
    private boolean addQueen(int row, int col){
	if(_board[row][col] != 0){
	    return false;
	}
	_board[row][col] = 1;
	int offset = 1;
	while(col+offset < _board[row].length){
	    _board[row][col+offset]--;
	    if(row - offset >= 0){
		_board[row-offset][col+offset]--;
	    }
	    if(row + offset < _board.length){
		_board[row+offset][col+offset]--;
	    }
	    offset++;
	}
	return true;
    }


    /***
     * Removes a queen from the board and increases all the areas that it could strike by one to free up those locations
     * precondition: There's a queen at the location
     * postcondition: Return true if the queen is removed, false otherwise
     */
    private boolean removeQueen(int row, int col){
	if ( _board[row][col] != 1 ) {
	    return false;
	}
	_board[row][col] = 0;
	int offset = 1;

	while( col+offset < _board[row].length ) {
	    _board[row][col+offset]++;
	    if( row - offset >= 0 ) {
		_board[row-offset][col+offset]++;
	    }
	    if( row + offset < _board.length ) {
		_board[row+offset][col+offset]++;
	    }
	    offset++;
	}
	return true;
    }


    /***
     * Prints out the entire board
     * precondition: There is a board
     * postcondition: Returns the board as a string
     */
    public String  toString()
    {
	String ans = "";
	for( int r = 0; r < _board.length; r++ ) {
	    for( int c = 0; c < _board[0].length; c++ ) {
		ans += _board[r][c]+"\t";
	    }
	    ans += "\n";
	}
	return ans;
    }


    //main method for testing...
    public static void main( String[] args )
    {
	QueenBoard b = new QueenBoard(5);
	System.out.println(b);
	b.addQueen(3,0);
	b.addQueen(0,1);
	System.out.println(b);
	b.removeQueen(3,0);
	System.out.println(b);

  for (int x = 2; x <= 8; x++) {  //test run size 2-8
    QueenBoard c = new QueenBoard(x);
    c.printSolution();
    c.solve();
    c.printSolution();
  }
    }

}//end class
