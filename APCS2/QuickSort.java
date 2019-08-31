//Ricky Lin
//APCS2 pd02
//HW18 -- QuickSort
//2018-03-13t

/*****************************************************
 * class QuickSort
 * Implements quicksort algo to sort an array of ints in place
 *
 * 1. Summary of QuickSort algorithm:
 * QSort(arr):
 Given an array, we set the pivot to be the rightmost number in the set range using partition.
 Then we recursively call parition on the two halves of the array divided by the pivot,
 The smaller left range being from [0,pvtPos-1]
 The larger right range being from [pvtPos+1, right]
 We will continue calling the method of the shrinking range of values until we reach a range of 1 element, meaning it is sorted
 *
 * 2a. Worst pivot choice and associated runtime:
 The worst pivot choice would be picking the largest number in the left array and the smallest number in the right array as the pivot of each range.
 This is because picking those numbers would only eliminate that number from the range of the next recursive of each half.
 This would be a O(n^2)
 *
 * 2b. Best pivot choice and associated runtime:
 The best choice is picking pivot that would end up in the middle index of the array. This will effectively cut the array into halves, allowing you
 to run the recursion on a fewer number of elements.
 The run time would be O(nlogn)
 *
 * 3. Approach to handling duplicate values in array:
 The code seems to be working perfectly fine with duplicates in the array. Not sure what we have to change because it seems fine as it is.
 *
 *****************************************************/

public class QuickSort
{
  //--------------v  HELPER METHODS  v--------------
  //swap values at indices x, y in array o
  public static void swap( int x, int y, int[] o ) {
    int tmp = o[x];
    o[x] = o[y];
    o[y] = tmp;
  }

  //print input array
  public static void printArr( int[] a ) {
    for ( int o : a )
	    System.out.print( o + " " );
    System.out.println();
  }

  //shuffle elements of input array
  public static void shuffle( int[] d ) {
    int tmp;
    int swapPos;
    for( int i = 0; i < d.length; i++ ) {
	    tmp = d[i];
	    swapPos = i + (int)( (d.length - i) * Math.random() );
	    swap( i, swapPos, d );
    }
  }

  //return int array of size s, with each element fr range [0,maxVal)
  public static int[] buildArray( int s, int maxVal ) {
    int[] retArr = new int[s];
    for( int i = 0; i < retArr.length; i++ )
	    retArr[i] = (int)( maxVal * Math.random() );
    return retArr;
  }
  //--------------^  HELPER METHODS  ^--------------

  public static int partition(int[] arr,int first,int last,int pivot){
    int v = arr[pivot];
    swap(arr,pivot,last);
    int current = first;
    for(int i=0;i<last;i++){
      if(arr[i] < v){
        swap(arr,current,i);
        current++;
      }
    }
  swap(arr,last,current);
  return current;
  }

  /*****************************************************
   * void qsort(int[])
   * @param d -- array of ints to be sorted in place
   *****************************************************/
  public static void qsort( int[] d )
  {
    qHelp(d,0,d.length-1);  //calls the recursive method
  }

  //you may need a helper method...

  public static int[] qHelp (int[] arr, int left, int right) {
	   if (left < right) {
      int pvtPos = partition(arr,left,right,right);  //pivot becomes rightmost element
	    qHelp(arr, left, pvtPos-1); //recursive call to the smaller left half in interval [left, pvtPos-1]
	    qHelp(arr, pvtPos+1, right); //recursive call to the larger right half in interval [pvtPos+1, right]
     }


  //main method for testing
  public static void main( String[] args )
  {
    //get-it-up-and-running, static test case:
    int [] arr1 = {7,1,5,12,3};
    System.out.println("\narr1 init'd to: " );
    printArr(arr1);

    qsort( arr1 );
    System.out.println("arr1 after qsort: " );
    printArr(arr1);

    // randomly-generated arrays of n distinct vals
    int[] arrN = new int[10];
    for( int i = 0; i < arrN.length; i++ )
    arrN[i] = i;

    System.out.println("\narrN init'd to: " );
    printArr(arrN);

    shuffle(arrN);
    System.out.println("arrN post-shuffle: " );
    printArr(arrN);

    qsort( arrN );
    System.out.println("arrN after sort: " );
    printArr(arrN);

    //get-it-up-and-running, static test case w/ dupes:
    int [] arr2 = {7,1,5,12,3,7};
    System.out.println("\narr2 init'd to: " );
    printArr(arr2);

    qsort( arr2 );
    System.out.println("arr2 after qsort: " );
    printArr(arr2);


    // arrays of randomly generated ints
    int[] arrMatey = new int[20];
    for( int i = 0; i < arrMatey.length; i++ )
    arrMatey[i] = (int)( 48 * Math.random() );

    System.out.println("\narrMatey init'd to: " );
    printArr(arrMatey);

    shuffle(arrMatey);
    System.out.println("arrMatey post-shuffle: " );
    printArr(arrMatey);

    qsort( arrMatey );
    System.out.println("arrMatey after sort: " );
    printArr(arrMatey);

  }//end main

}//end class QuickSort
