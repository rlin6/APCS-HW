/* Team AITesters [Ricky Lin, Soojin Choi, Shayan Chowdhury]
   APCS1 pd2
   HW#46 -- AI<B> Sorted
   2017-12-05T */

import java.util.ArrayList;   //import ArrayList

public class ALTester {

    public static boolean sorted (ArrayList<Comparable> array) {  //takes an arraylist<comparable> as parameters
		for (int i = 1; i < 23; i++) {  //starting from the 2nd element
	    	if ((array.get(i).compareTo(array.get(i-i))) < 0) {  //compare the 2nd element to the one before, if the 2nd one is smaller, return false, it is not sorted ascendingly
		    	return false;
	    	}
		}
	return true; //otherwise return true
    }
		
    public static void main (String[] args) {
	
		ArrayList<Comparable> foo = new ArrayList<Comparable>(); //create 4 new arraylist holding 23 Integers
		ArrayList<Comparable> doo = new ArrayList<Comparable>();
		ArrayList<Comparable> too = new ArrayList<Comparable>();
		ArrayList<Comparable> goo = new ArrayList<Comparable>();
	
		for (int x = 0; x < 23; x++) {  //populates the arrays
			foo.add(x); // [0,1,2,3...22]
			doo.add(22 - x);  //[22,21,20...0]
			too.add((int)(x / 2)); //[0,0,1,1,2,2....10,10,11]
			goo.add(x * 2);  //[0,2,4,6...42,44] 
		}

		System.out.println(foo);  //expect the above comments
		System.out.println(doo);
		System.out.println(too);
		System.out.println(goo);

		System.out.println(sorted(foo));  //true is sorted normally
		System.out.println(sorted(doo));  //false descending order
		System.out.println(sorted(too));  //true repeats but in ascending order
		System.out.println(sorted(goo));  //true large differences but still in ascending order 
	}	
}

    
