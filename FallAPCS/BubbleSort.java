/******************************
 * class BubbleSort -- implements bubblesort algorithm (vanilla)
 ******************************/

import java.util.ArrayList;

public class BubbleSort {

    //~~~~~~~~~~~~~~~~~~~ HELPER METHODS ~~~~~~~~~~~~~~~~~~~
    //precond: lo < hi && size > 0
    //postcond: returns an ArrayList of random integers
    //          from lo to hi, inclusive

	public static ArrayList populate( int size, int lo, int hi ) {
		ArrayList<Integer> retAL = new ArrayList<Integer>();  //instantiate new ArrayList 
		while( size > 0 ) {
	    	//     offset + rand int on interval [lo,hi]
	    	retAL.add( lo + ( (int)((hi - lo + 1) * Math.random()) ) ); //add to arraylist random number between range of low and high   
	    	size--;  //decrement size
		}
		return retAL; 
    }

    //randomly rearrange elements of an ArrayList
	public static void shuffle( ArrayList al ) {
		int randomIndex;
		//setup for traversal from right to left
		for( int index = al.size()-1; index > 0; index-- ) {
	    	//pick an index at random
	    	randomIndex = (int)( (index+1) * Math.random() );
	    	//swap the values at position i and randomIndex
	    	al.set( index, al.set( randomIndex, al.get(index) ) );  //sets value at randomIndex to value at index and returns the value of randomIndex, which the value of index is then set to
		}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // VOID version of bubbleSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void bubbleSortV( ArrayList<Comparable> data ) {
		for (int iter = data.size() - 1; iter > 0; iter--) {   //iterates through the list n-1 times which is the amount of passes needed to sort everything
			for (int index = data.size() - 1; index > 0; index--) {  //iterates through the array comparing the last two elements and then the 2nd to last and 3rd to last element etc. until it reaches the 1st and 2nd element 
				if (data.get(index).compareTo(data.get(index - 1)) < 0) {  //if the element before the index we are checking is bigger than it, swap the places of the elements
					data.set(index - 1, data.set(index, data.get(index - 1)));
				}
	    	}
		}     
    }


    // ArrayList-returning bubbleSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Comparable> bubbleSort( ArrayList<Comparable> input ) {
		ArrayList<Comparable> copy = new ArrayList<Comparable>();
		for (int index = 0; index < input.size(); index++) {  //iterates through every element of input
	    	copy.add(input.get(index));  //copies each element of input into a new copy to work with
		}
		bubbleSortV(copy);
		return copy;  //return the newly sorted copy 
    }

    public static void main( String [] args )
    {
	/*===============for VOID methods============= */
	ArrayList glen = new ArrayList<Integer>();
	glen.add(7);
	glen.add(1);
	glen.add(5);
	glen.add(12);
	glen.add(3);
	System.out.println( "ArrayList glen before sorting:\n" + glen );
	bubbleSortV(glen);
	System.out.println( "ArrayList glen after sorting:\n" + glen );
	
	ArrayList coco = populate(10, 1, 1000);
	System.out.println( "ArrayList coco before sorting:\n" + coco );
	bubbleSortV(coco);
	System.out.println( "ArrayList coco after sorting:\n" + coco );

	/*===============for AL RETURNING methods============= */
	ArrayList blen = new ArrayList<Integer>();
	blen.add(7);
	blen.add(1);
	blen.add(5);
	blen.add(12);
	blen.add(3);
	System.out.println( "ArrayList blen before sorting:\n" + blen );
	ArrayList blenSorted = bubbleSort(blen);
	System.out.println( "sorted version of ArrayList blen:\n" + blenSorted );
	System.out.println( "ArrayList blen after sorting:\n" + blen );

	ArrayList bobo = populate(10, 1, 1000);
	System.out.println( "ArrayList bobo before sorting:\n" + bobo );
	ArrayList boboSorted = bubbleSort( bobo );
	System.out.println( "sorted version of ArrayList bobo:\n" + boboSorted );
	System.out.println( "ArrayList bobo after sorting:\n" + bobo );

    }//end main

}//end class BubbleSort
