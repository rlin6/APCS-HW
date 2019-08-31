/* Ricky Lin 
   APCS1 pd02
   HW#51 Selection
   2017-12-12T */

/***************************************
 * class SelectionSort -- implements SelectionSort algorithm
 ***************************************/

import java.util.ArrayList;

public class SelectionSort 
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
	for( int i = al.size()-1; i > 0; i-- ) {
	    //pick an index at random
	    randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
	    al.set( i, al.set( randomIndex, al.get(i) ) );
	}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // VOID version of SelectionSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void selectionSortV( ArrayList<Comparable> data ) 
    {
	int smallest = 0;                     //create new variable to hold the index of the smallest value thus far
	for (int i = 0; i < data.size(); i++) {  //outer loop that iterates through entire array
	    smallest = i;                       //set the smallest index to the current value which is technically the smallest one right now since it's the only value
	    for (int x = i + 1; x < data.size(); x++) {   //iterate through the array starting from the value after the one you're comparing b/c the ones before i should already be the smallest values
		if (data.get(smallest).compareTo(data.get(x)) > 0) {  //if the current smallest index does not hold its title and is bigger than the element you are comparing it to right now
		    smallest = x;   //dethrone the king and the current index you're comparing becomes the new smallest
		}
	    }
	    data.set(i, data.set(smallest, data.get(i)));   //after reaching the end of the inner loop, you should have a smallest index that you want to swap with the current index you're on
	}
    }//end selectionSortV


    // ArrayList-returning selectionSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Comparable>
	selectionSort( ArrayList<Comparable> input ) 
    {
	ArrayList<Comparable> copy = new ArrayList<Comparable>();   //instantiate a new ArrayList<Comparable> instead of creating an alias
	for (int i = 0; i < input.size(); i++) {    //iterates through the entire array
	    copy.add(input.get(i));                //add each element into a new array copy
	}
	selectionSortV(copy);                     //run the algo on the copy instead of the original input
	return copy;                             //return the newly sorted copy
    }//end selectionSort 


    public static void main( String [] args ) 
    {
       
	ArrayList glen = new ArrayList<Integer>();
	glen.add(7);
	glen.add(1);
	glen.add(5);
	glen.add(12);
	glen.add(3);
	System.out.println( "ArrayList glen before sorting:\n" + glen );
	selectionSortV(glen);
	System.out.println( "ArrayList glen after sorting:\n" + glen );

	/*===============for VOID methods=============
	  ArrayList coco = populate( 10, 1, 1000 );
	  System.out.println( "ArrayList coco before sorting:\n" + coco );
	  selectionSortV(coco);
	  System.out.println( "ArrayList coco after sorting:\n" + coco );
	  ============================================*/

	/*==========for AL-returning methods==========

	  ArrayList glen = new ArrayList<Integer>();
	  glen.add(7);
	  glen.add(1);
	  glen.add(5);
	  glen.add(12);
	  glen.add(3);
	  System.out.println( "ArrayList glen before sorting:\n" + glen );
	  ArrayList glenSorted = selectionSort( glen );
	  System.out.println( "sorted version of ArrayList glen:\n" 
	  + glenSorted );
	  System.out.println( "ArrayList glen after sorting:\n" + glen );
	  ============================================*/
	ArrayList coco = populate( 10, 1, 1000 );
	System.out.println( "ArrayList coco before sorting:\n" + coco );
	ArrayList cocoSorted = selectionSort( coco );
	System.out.println( "sorted version of ArrayList coco:\n" 
			    + cocoSorted );
	System.out.println( "ArrayList coco after sorting:\n" + coco );
	System.out.println( coco );
     

    }//end main

}//end class SelectionSort
