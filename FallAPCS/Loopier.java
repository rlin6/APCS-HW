// Ricky Lin
// APCS1 pd2
// HW25 -- All Hallow's Eve
// 2017-11-01

public class Loopier {

	public static int[] populate (int[] arr) {
		for (int index = 0; index < a.length; index++) { //initializes variable, runs while variable is less than array, and increments it afterwards
   			arr[index] = (int) (Math.random() * 100); //each element in array is set to the int of random times 100
		}
		return arr; // return array
  	}

	public static String arrayToString (int[] arr) {
		String ans = "" + arr[0] ;  //initialize string consisting of 1st element
		for (int index = 1; index < arr.length; index++) { //for loops that runs while variable is less than length of array
			ans +=  ", " + arr[index];  // adds , + element to the string
		}
		return ans; // return string
    }

	public static int linSearch (int[] arr, int target) {
		for (int index = 0; index < arr.length; index++) { //for loop that runs while vari is less than array length
    		if (arr[index] == target) {  //if element is the target
				return index;  //return the element
			}
		}
		return -1;  //if no target, return -1
    }

    public static int[] trun (int[] arr) {
		int[] newArr = new int[arr.length - 1];  //creates a new array 1 shorter than arr
		for (int index = 0; index < newArr.length; index++) {  //for loops that runs while vari is less than the new array length
	    	newArr[index] = arr[index + 1];  // adds the 1st index element onwards from old array into the new array
		}
		return arr;  // return the new array
    }
    
    public static int linSearchR (int[] arr, int target) {
		if (arr.length == 0) {  //when entire array is empty b/c target is not in it and everything's been truncated by trun, return -1 b/c target is not there
	    	return -1;
		}
		
		else if (arr[0] == target) { //if the 1st element is the target, return 0 to keep that index
	    	return 0;
		}

		else if (linSearchR(trun(arr), target) == -1){ //if the recursive returns -1, return -1 to stop it
	    	return -1;
		}
	
		else {  // adds 1 to the index of the new truncated array going through recursion
	    	return 1 + linSearchR(trun(arr), target);
		}
    }
		
    public static int freq (int[] arr, int target) {
		int count = 0 ; //initialize count
		for (int index = 0; index < arr.length; index++) { //if the element is equal to target, add one to the count
			if (arr[index] == target) {
				count += 1;
			} 
		}
		return count; //return total count
    }

    public static int freqRec (int[] arr, int target) {
		if (arr.length == 0) { //if array is empty from trun-ing through all of it without finding target, return 0
    		return 0;
		}
		
		else if (arr[0] == target) { //if the first element is equal to target, 1 is added to the total count and you run the recursion with a truncated array
	    	return 1 + freqRec(trun(arr), target);
		}
		
		else {  //otherwise, check the truncated array without adding one to frequency 
	    	return freqRec(trun(arr), target);
		}
    }
    
    public static void main (String [] args) {
		//testing methods
		int[] arr = new int[5];
		System.out.println(arrayToString(populate(arr))); //5 random numbers [1,100}
		
		int[] test = {2,4,6,7,8,1};
		System.out.println(arrayToString(trun(test)));//4,6,7,8,1
		System.out.println(linSearch(test,6)); //2
		System.out.println(linSearch(test,3));//-1
		System.out.println(linSearchR(test,6));//2
		System.out.println(linSearchR(test,3));//-1
		
		int[] test2 = {2,2,2,2,2,1};
		System.out.println(freq(test2,2));//5
		System.out.println(freq(test2,6));//0
       	System.out.println(freqRec(test2,2));//5
		System.out.println(freqRec(test2,6));//0
    }
}
