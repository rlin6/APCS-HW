/*======================================
  Ricky Lin
  APCS2 pd02
  HW#05-Step 1: Split, Step 2: ?, Step 3: Sorted!
  2018-02-06
  
  class MergeSort
  Implements mergesort on array of ints.

  Summary of Algorithm:

  For merge, I ran a while loop that keeps track of the two indices that we are comparing in a and b. The loop runs while either indices is less than their respective
  length because not all arrays are the same length as each other or finishes adding to the new array at the same time. If a or b finishes before the other, the loop
  will still run, only this time they will only add elements from the non-completed array. If the int at a1(the index we're on) is larger than b1, the int at index b1  gets added to the new array and its index incremented, and vice versa. In the end, you will get a sorted array in ascending order.

  For sort, I used recursion. After finding the midpoint m, I create 2 new arrays with the size m and then use a for loop to fill one with the 1st half of the array a  nd the other with the 2nd half of the array. Then I called merge on the sort of two arrays. This will run the sort again, halving each array again until the base 
  case is reached, when the array is only size 1. Once the sorted array of 1 is returned for both parameters, the merge will merge the two sorted arrays, which will
  then be merged with the other newly merged array waiting on the other half. This merging will continue until the original two halves merge together into a new 
  sorted array. 
  ======================================*/

public class MergeSort
{
  /******************************************************
   * int[] merge(int[],int[])
   * Merges two input arrays
   * Precond:  Input arrays are sorted in ascending order
   * Postcond: Input arrays unchanged, and
   * output array sorted in ascending order.
   ******************************************************/
  private static int[] merge( int[] a, int[] b )
  {
      int[] ans = new int[(a.length + b.length)]; //create new array with the combined lengths
      int a1 = 0;  //counter for a's position
      int b1 = 0;  //counter for b's position
      while (a1 < a.length || b1 < b.length) {  //iterates while either counter is not at the end of each array
	  if (a1 == a.length) {   //if you finished with all of a add the remaining b elements
	      ans[a1 + b1] = b[b1];  
	      b1 += 1;
	  }
	  else if (b1 == b.length) {  //if you finished with all of b add the remaining a elements
	      ans[a1 + b1] = a[a1];
	      a1 += 1;
	  }
	  else if (a[a1] > b[b1]) {  //if the current element at position a1 is larger than the one at b1, add the lower b1 to the new array and move to next b position
	      ans[a1 + b1] = b[b1];
	      b1 += 1;
	  }
	  else {
	      ans[a1 + b1] = a[a1]; //otherwise add the element at a1 and move to next a position 
	      a1 += 1;
	  }
      }
      return ans;  //return the sorted merged array	      	      
  }//end merge()


  /******************************************************
   * int[] sort(int[])
   * Sorts input array using mergesort algorithm
   * Returns sorted version of input array (ascending)
   ******************************************************/
  public static int[] sort( int[] arr )
  {
      int m = arr.length / 2;  //find the half way point 
      if (arr.length == 1) {  //base case: when the array has one number
	  return arr; }
      else 
	  int[] firstH = new int[m];  //array holding the first half
	  int[] secondH = new int[m]; //array holding the second half
	  for (int i = 0; i < firstH.length; i++) {  //fills both arrays up with the respective halves
	      firstH[i] = arr[i];
	      secondH[i] = arr[arr.length - 1 - i];
	  }
	  return merge(sort(firstH),sort(secondH));  //recursive that calls sort again on both halves, halfing them further until they reach the base case and merge will kick in, merging them together until you reach the top again and merge the original 2 halves  
      } 
  }//end sort()



  //-------------------HELPERS-------------------------
  //tester function for exploring how arrays are passed
  //usage: print array, mess(array), print array. Whaddayasee?
  public static void mess( int[] a ) {
    for( int i = 0 ; i<a.length; i++ )
      a[i] = 0;
  }

  //helper method for displaying an array
  public static void printArray( int[] a ) {
    System.out.print("[");
    for( int i : a )
      System.out.print( i + ",");
    System.out.println("]");
  }
  //---------------------------------------------------


  //main method for testing
  public static void main( String [] args ) {

   
      int[] arr0 = {0};
      int[] arr1 = {1};
      int[] arr2 = {1,2};
      int[] arr3 = {3,4};
      int[] arr4 = {1,2,3,4};
      int[] arr5 = {4,3,2,1};
      int[] arr6 = {9,42,17,63,0,512,23};
      int[] arr7 = {9,42,17,63,0,9,512,23,9};

      System.out.println("\nTesting mess-with-array method...");
      printArray( arr3 );
      mess(arr3);
      printArray( arr3 );

      System.out.println("\nMerging arr1 and arr0: ");
      printArray( merge(arr1,arr0) );

      System.out.println("\nMerging arr4 and arr6: ");
      printArray( merge(arr4,arr6) );

      
      System.out.println("\nSorting arr4-7...");
      printArray( sort( arr4 ) );
      printArray( sort( arr5 ) );
      printArray( sort( arr6 ) );
      printArray( sort( arr7 ) );
  }//end main()

}//end class MergeSort
