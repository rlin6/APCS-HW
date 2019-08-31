/* Ricky Lin
   APCS1 pd02
   HW#56 His Toe Grammar
   2017-12-19T */

/*====================================================================
  An AP-style question, for practice:
  Write the StatPrinter class below. The StatPrinter Object receives an
  ArrayList of nonnegative integers, then builds a frequency ArrayList in which
  the index values are the data and the entry at the index is the frequency.
  For example, if the received data is    2,3,2,5,1,3    then the frequency 
  list would be [0,1,2,2,0,1]. This is read as 0 zeroes, 1 one, 2 twos,
  2 threes, 0 fours, 1 five. The size of the frequency list is the equal to
  the maximum value of the data.

  A capability of the class is to calculate local modes from the frequency 
  list. A local mode is a value that is greater than the value at index-1 
  and greater than the value at index+1. A local mode is never at the end
  points of the list. For example, if the frequency list is [1,2,1,4,2,3,5] 
  then the local modes are 2 and 4.

  This class is also capable of printing a histogram of the frequencies, using
  '*'s to indicate a frequency amount. To print a histogram, the user specifies
  the longest sequence of '*'s used and then all other values are printed in 
  proportion to this value. For example, if longest bar is 10 and the frequency
  list is [1,2,1,4,2,3,5] then the histogram printed looks like this:

  0 : **
  1 : ****
  2 : **
  3 : ********
  4 : ****
  5 : ******
  6 : **********

  For each method, state run time efficiency using Big O notation.

  Tips for Awesome:
  * Keys to Success are so named for a reason.
  * Look over all fxns, think a bit, decide which to tackle first.
  ( Simplest?  Prerequisites? . . . )
  * Develop 1 fxn at a time, test it, then move to next.
  * For coding today, what extra code do you need to get past compiler?
  ====================================================================*/

import java.util.ArrayList;

public class StatPrinter {

    // instance variable for frequencies of each integer in input ArrayList
    private ArrayList <Integer> _frequency;


    //*************** QUESTION 02 **************************
    //precond:  data.size() > 0, each entry b/t 0,100 inclusive
    //postcond: _frequency.size() set to max(data) + 1
    //          _frequency.get(i) returns frequency of i in data
    //eg, for data [2,3,2,5,2,3]
    //  _frequency would be [0,0,3,2,0,1]
    public StatPrinter( ArrayList<Integer> data ) 
    { 	int counter = 0;     //init a counter to keep track of each entry's frequency
	_frequency = new ArrayList<Integer>();  //init _frequency as an ArrayList<Integer>
        for (int x = 0; x <= max(data); x++) {  //runs and adds the same number of elements as the max data
	    for (int w = 0; w < data.size(); w++) {  //iterates through entire array
		if (data.get(w) == x) {   //if the value at index w is equal to the entry you are gathering frequency for
		    counter += 1;  //increase the frequency of the entry
		}
	    }
	    _frequency.add(counter);  //add that frequency into the array
	    counter = 0;  //reset the counter
	}
    }


    //*************** QUESTION 01 **************************
    //precond:  data.size() > 0
    //postcond: returns largest integer in data
    public Integer max( ArrayList <Integer> data ) 
    { 
       	int maxi = 0;   //init an int to keep track of the largest element
	for (int i = 0; i < data.size(); i++) {  //iterates through entire array
	    if (maxi < data.get(i)) {  //if the element at i is bigger than the current max 
		maxi = data.get(i);  //i becomes the new max
	    }
	}
	return maxi;  //return the max
    }


    //*************** QUESTION 03 **************************
    //postcond: returns true if i > 0 and i < _frequency.size() - 1
    //          and _frequency.get( i - 1 ) < _frequency.get( i )
    //          and _frequency.get( i + 1 ) < _frequency.get( i )
    //          Otherwise, returns false
    //eg, for _frequency [1,2,1,5,5,8,2,4]
    //    2 and 8 are local modes, so
    //    isLocalMode(0) -> false
    //    isLocalMode(1) -> true
    //    isLocalMode(5) -> true
    public boolean isLocalMode( int i ) 
    { 
	return ((i > 0) && (i < _frequency.size() - 1) && (_frequency.get(i-1) < _frequency.get(i)) && (_frequency.get(i+1) < _frequency.get(i)));
	//returns true if the element is not the first and last and its value is greater than the one before and after it
    }
    


    //*************** QUESTION 04 **************************
    //postcond: returns list of modes in _frequency
    public ArrayList<Integer> getLocalModes() 
    {
	ArrayList<Integer> l = new ArrayList<Integer>();  //inits a new arraylist<integer> to hold list of modes
	for (int i = 1; i < _frequency.size()-1; i++) {  //iterates through the 1 to 2nd to last index 
	    if (isLocalMode(i)) {  //if its a local mode
		l.add(i);  //add it to the array
	    }
	}
	return l;	//return the array
    }


    //*************** QUESTION 05 **************************
    //precond:  longestBar > 0
    public void printHistogram( int longestBar ) 
    {
	int prop = (longestBar) / (max(_frequency));  //create an int to keep track of the proportion of the longestBar to the longest sequence 
	if ( prop == 0) {  //if the proportion is 0
	    prop = 1;  //set it to 1 to prevent dividing by zero errors
	}
	for (int i = 0; i < _frequency.size()-1; i++) {  //iterates through the entire array except the last element
	    if (_frequency.get(i) == 0) {  //if the frequency of this index is 0
		System.out.println(i + " : ");  //simply print index : with nothing else and move onto next line 
	    }
	    else {
		System.out.print(i + " : ");  //otherwise print index : and stay on that line to add * after it
		for (int x = 1; x <= (prop * _frequency.get(i)); x++) {  //iterates through the number of * you need to add, which is just the proportion times the frequency 
		    if (x == (prop * _frequency.get(i))) { //when you're on the last *
			System.out.println("*");  //add the * and move onto the next line
		    }
		    else {
			System.out.print("*");  //otherwise add the * and continue on the same line
		    }
		}
	    }
	}
	System.out.print(_frequency.size() + " : ");  //prints the last index:
	for (int i = 0; i <= longestBar; i++) {  //adds the longestBar number of * into the line
	    if (i == longestBar) {  //if this is the last *
		System.out.println("*");  //print that * and move onto next line
	    } 
	    else {
		System.out.print("*");  //otherwise print that * and stay on the same line
	    }
	}
    }
}//end class StatPrinter
