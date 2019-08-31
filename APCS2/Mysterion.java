//Ricky Lin
//APCS2 pd02
//HW16 About Face
//2018-03-08 R

public class Mysterion{

	/*Name: halfSort
	  Parameters: arr = array that's given
	  			  first = first index of the array that's going to be organized
	  			  last = last index of the array that's going to be organized
	  			  pivot = Arranges elements of array based upon this.
	  Function: Organizes array with numbers < pivot to the left and > pivot to the right of the final location of pivot
	*/
	public static int mysterion(int[] arr,int first,int last,int pivot){
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

	public static void main(String []args){
		int[] array1 = {7,1,5,12,3};
		mysterion(array1,0,array1.length-1,array1.length/2);
		System.out.println(toString(array1));

		System.out.println("\n");

		int[] array2 = {1,2,3,4,5,6,7};
		mysterion(array2,0,array2.length-1,array2.length/2);
		System.out.println(toString(array2));

		System.out.println("\n");

		int[] array3 = {7,6,5,4,3,2,1};
		mysterion(array3,0,array3.length-1,array3.length/2);
		System.out.println(toString(array3));

		System.out.println("\n");

		int[] array4 = {1,5,4,7,13,2,9};
		mysterion(array4,0,array4.length-1,array4.length/2);
		System.out.println(toString(array4));
	}
}
