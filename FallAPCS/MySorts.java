/* Team 12AM Projects [Ricky Lin, Mohtasim Howlader]
   APCS1 pd02
   HW#55 -- Never Fear, Big Oh is Here!
   2017-12-15F */

import java.util.ArrayList;

public class MySorts {

    /*

       add = O(n)
       Reason: Add is just a linear search, meaning it looks through every single one of the elements, resulting in n passes
       
       addBin = O(log n) 
       Reason: addBin is a binary search, meaning that it looks at the middle of the array, splits it into 2 halfs, and looks in the middle of the relevant half.
       Because it continously halves the halves of the array, it takes log2 n passes to go through the whole array. 

    */
 
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

    
    // VOID version of InsertionSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
	
	
    public static void insertionSort( ArrayList<Comparable> data )
    {
		/* The worse and best cases for Insertion Sort is the same as bubble sort: descending and ascending order respectively. When the elements are in descending 
	   order, the sorted partition will hold the largest numbers. The elements in the unsorted region will have to compare themselves to the entirety of the 
	   sorted partition before reaching their final resting place in the beginning of the array (especially for the last element, which has to walk through
	   the entire sorted partition, meaning every element before it, to be placed in its final resting index of 0). The best case would be that the array is
	   already sorted. You would still make the same number of passes but there would be no swapping, taking up less memory. The only thing that would occur 
	   is that the sorted partition would increase until it includes the entire array in it. 
	   
	   Best Case Order: O(n)
	   Reason: When it's already sorted in ascending, the code simply iterates through the entire list once, which is n passes
	   
	   Worst Case Order: O(n^2)
	   Reason: When it's sorted in descending order, the code has to iterate through the sorted region with each number, meaning that you would need n * n number
	   of passes, resulting in n^2 passes.
	   */
		
	int pass = 0;  //counter to keep track of the passes and swaps
	int swap = 0;
	for( int partition = 1; partition < data.size(); partition++ ) {
	    //partition marks first item in unsorted region
	    pass += 1;  //increment every start of pass
	    //diag:
	    System.out.println( "\npartition: " + partition + "\tdataset:");
	    System.out.println( data ); 
	    System.out.println ("Pass #" + pass);
	    //traverse sorted region from right to left
	    for( int i = partition; i > 0; i-- ) {
		// "walk" the current item to where it belongs
		// by swapping adjacent items
		if ( data.get(i).compareTo( data.get(i-1) ) < 0 ) {
		    //diag:
		    System.out.println( "swap indices "+(i-1)+" & "+i+"..." );
		    data.set( i, data.set( i-1, data.get(i) ) );
		    swap += 1;  //increment and print every swap
		    System.out.println(swap);
		}
		else 
		    break; 
	    }
	}
    }//end insertionSortV

    
    // VOID version of SelectionSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void selectionSort( ArrayList<Comparable> data ) 
    {
	   /* There is no worse or best case for Selection Sort using the library code. This is because SelectionSort does not look at its adjacent neighbors unlike
	   Bubble and Insertion sort. Thus, no matter how the values are arranged, it will only swap the current element with the most extreme unsorted element.
	   Due to how the code is written, even if the given array is already sorted, the algo will still swap two elements, except it will be the same element 
	   onto itself and move on to do the same thing to the next index and so on. No matter how the array is unsorted, the algo will track down the most extreme
	   element and perform the swap and move onto the next index. Any given array will only require size-1 passes and size-1 swaps because of this. 
	   
	   Best Case Order: O(n^2)
	   Reason: There are 2 loops in the code that iterate through the list (in which one iteration is n passes), so two loops of n passes is n^2 passes.
	   
	   Worst Case Order: O(n^2)
	   Reason: As explained above, the best and worst case is the same. The two iterations through the entire list will always be performed.
	   */
	//note: this version places greatest value at rightmost end,

	//maxPos will point to position of SELECTION (greatest value)
	int maxPos;
	int swap = 0;  //keeps track of swaps
	for( int pass = data.size()-1; pass > 0; pass-- ) {
	    System.out.println( "\nbegin pass " + (data.size()-pass) );//diag
	    maxPos = 0;
	    for( int i = 1; i <= pass; i++ ) {
		System.out.println( "maxPos: " + maxPos );//diag
		System.out.println( data );//diag
		if ( data.get(i).compareTo( data.get(maxPos) ) > 0 )
		    maxPos = i;
	    }
	    data.set( maxPos, ( data.set( pass, data.get(maxPos) ) ) );
	    swap += 1;   //increment and later print the swap
	    System.out.println( "after swap: " +  data );//diag
	    System.out.println( "# of swaps: " + swap );
	}
    }//end selectionSortV




    // VOID version of bubbleSort
    // Rearranges elements of input ArrayList
    // postcondition: data's elements sorted in ascending order
    public static void bubbleSort( ArrayList<Comparable> data )
    {
	   /* The worst case for Bubble Sort is when the elements are in descending order. If the smallest element is at the end, the first element would have to compare
	   itself to every single element in the array before reaching it. Then the second index would have to traverse the length of the unsorted array to reach the 
	   2nd to smallest element. Since the algo has to constantly swap the large elements up the array, it takes much more memory. The best case is when the array
	   is already sorted. While it still takes the same number of passes, no elements will be switched because they are already in place. Considering that 
	   swapping takes much more memory to do, not having to swap any elements saves the most memory
	   
	   Best Case Order: O(n)
	   Reason: In the best case, the list is already sorted, which means that there will only be one pass for each item in list, which is just n, the size of the list.
	   
	   Worst Case Order: O(n^2)
	   Reason: In the worst case, the list is in reverse order, which means that in order to add the next item to the sorted list, it needs to traverse the whole list.
			   This means that it has to traverse the list (n passes) n times, which is n times n, which is n^2.
   
	   
	   */
	   
	   
	int swap = 0;  //swap counter
	//make n-1 passes across collection
	for( int passCtr = 1; passCtr < data.size(); passCtr++ ) {
	    System.out.println( "commencing pass #" + passCtr + "..." );

	    //iterate from first to next-to-last element
	    for( int i = 0; i < data.size()-1; i++ ) {
		//if element at i > element at i+1, swap
		if ( data.get(i).compareTo(data.get(i+1) ) > 0 ) { 
		    data.set( i, data.set(i+1,data.get(i)) );
		    swap += 1;  //when swapping, increment swap and print it
		    System.out.println("Swap #:" + swap);
		}
		//System.out.println(data); //diag: show current state of list
	    }
	}
    }
}
