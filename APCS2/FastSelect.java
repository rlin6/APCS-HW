  /*
  Ricky Lin
  APCS2 pd02
  HW#17: So So Fast
  2018-03-12 M
  */

  public class FastSelect {
/*
The algorithm works by using y-1 as a pivot point and continously reorganizing the array until partition returns the final position of the pivot as y-1.
This works because we know that the value returned partition is in its final location, meaning that the yth smallest value would be at the y-1 position.

The Big(O) of the fastSelect alone would be O(n) because you are only iterating through the array once to compare the value returned by partition to y-1
*/
    public static int fastSelect(int[] arr, int y) {
      for (int i = 0; i < arr.length; i++) {
        System.out.println(toString(arr));
        if (partition(arr,0,arr.length-1,y-1) == y-1) {
          return arr[y-1];
        }
      }
      return 0;
    }

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

    public static int[] swap(int[] arr,int index1,int index2){
      int temp = arr[index1];
      arr[index1] = arr[index2];
      arr[index2] = temp;
      System.out.println(toString(arr));
      return arr;
    }

    public static String toString(int[] arr){
  		String str = "";
  		for(int x:arr){
  			str += x + ", ";
  		}
  		return str;
  	}

    public static void main(String[] args) {
      int[] array1 = {7,1,5,12,3}; //case given in class
      System.out.println(fastSelect(array1,3)); //best case: constant time because pivot value would be the same as the yth value you are looking for
      System.out.println(fastSelect(array1,5));//worst case: linear time because you would have to iterate through the array more than once in order to find the yth value
      System.out.println(fastSelect(array1,1));
      System.out.println(fastSelect(array1,2));
      System.out.println(fastSelect(array1,4));
    }
  }
