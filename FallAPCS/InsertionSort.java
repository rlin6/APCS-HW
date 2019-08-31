/* Ricky Lin
   APCS1 pd02
   HW#52 -- Poker Face
   2017-12-13W */

/***************************************
 *  class InsertionSort -- implements InsertionSort algorithm
 ***************************************/

import java.util.ArrayList;

public class InsertionSort
{
    //~~~~~~~~~~~~~~~~~~~ HELPER METHODS ~~~~~~~~~~~~~~~~~~~
    //precond: lo < hi && size > 0
    //postcond: returns an ArrayList of random integers
    //          from lo to hi, inclusive
    public static ArrayList populate( int size, int lo, int hi ) {
		ArrayList<Integer> retAL = new ArrayList<Integer>();
		while( size > 0 ) {
	    	//     offset + rand int on interval [lo,hi]
	    	retAL.add( lo + (int)( (hi-lo+1) * Math.random() ) );
	    	size--;
		}
	return retAL;
    }

    //randomly rearrange elements of an ArrayList
    public static void shuffle( ArrayList al ) {
		int randomIndex;
		for( int index = al.size()-1; index > 0; index-- ) {
	    	//pick an index at random
	    	randomIndex = (int)( (index+1) * Math.random() );
	    	//swap the values at position i and randomIndex
	    	al.set( index, al.set( randomIndex, al.get(index) ) );
		}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // VOID version of InsertionSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void insertionSortV( ArrayList<Comparable> data ) {
		int track;  //init new track variable to keep track of the element you are tracking at the moment 
		for (int pSize = 1; pSize < data.size(); pSize++) {   //creates the sorted zone pSize that increases with each pass starting at 1
	    	track = pSize;   //set the tracked variable to the element right after the sorted zone                                     
	    	for (int neighbor = pSize - 1; neighbor >= 0; neighbor--) {   //iterates through the sorted zone starting from the one closest to the tracked element
				if (data.get(track).compareTo(data.get(neighbor)) < 0) {   //if the tracked variable is smaller than its previous neighbor
		    		data.set(track, data.set(neighbor, data.get(track)));  //swap the two elements
		    		track--;   //since the tracked element has moved one index back, decrement track
				}
	    	}
		}
    }//end insertionSortV


    // ArrayList-returning insertionSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Comparable> insertionSort( ArrayList<Comparable> input ) {
		ArrayList<Comparable> copy = new ArrayList<Comparable>();  //init a new arraylist
		for (int index = 0; index < input.size(); index++) {   //iterates through entire array
	    	copy.add(input.get(index));   //copy all the elements into a new array                
		}
		insertionSortV(copy);  //insertion sort the new array
		return copy;  //return the new array
    }//end insertionSort

    public static void main( String [] args ) {
   
		/*===============for VOID methods============= */
		System.out.println("\n*** Testing sort-in-place (void) version... *** ");
		ArrayList glen = new ArrayList<Integer>();
		glen.add(7);
		glen.add(1);
		glen.add(5);
		glen.add(12);
		glen.add(3);
		System.out.println( "\nArrayList glen before sorting:\n" + glen );
		insertionSortV(glen);
		System.out.println( "\nArrayList glen after sorting:\n" + glen );
		
		
		ArrayList coco = populate( 10, 1, 1000 );
		System.out.println( "\nArrayList coco before sorting:\n" + coco );
		insertionSortV(coco);
		System.out.println( "\nArrayList coco after sorting:\n" + coco );

		/*===============for AL RETURNING methods============= */
		System.out.println( "*** Testing non-void version... *** " );
		ArrayList blen = new ArrayList<Integer>();
		blen.add(7);
		blen.add(1);
		blen.add(5);
		blen.add(12);
		blen.add(3);
		System.out.println( "\nArrayList blen before sorting:\n" + blen );
		ArrayList blenSorted = insertionSort( blen );
		System.out.println( "\nsorted version of ArrayList blen:\n" 
		+ blenSorted );
		System.out.println( "\nArrayList blen after sorting:\n" + blen );

		ArrayList bobo = populate( 10, 1, 1000 );
		System.out.println( "\nArrayList bobo before sorting:\n" + bobo );
		ArrayList boboSorted = insertionSort( bobo );
		System.out.println( "\nsorted version of ArrayList bobo:\n" 
					+ boboSorted );
		System.out.println( "\nArrayList bobo after sorting:\n" + bobo );
		System.out.println( bobo );
		
    }//end main

}//end class InsertionSort
