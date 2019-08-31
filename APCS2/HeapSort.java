//Ricky Lin
//APCS2 pd02
//HW#51 -- Heaping Piles of Sordidness
//2018-05-21 M

public class HeapSort {

    private int[] _data; //array of data values

    public HeapSort(int[] d) {
	_data = d; //initializes with given array
    }

    private void heapify(int partition) { //acts like partition
	int index = 0; //keep track of index
	for (int i = 1; i <= partition; i++) { //from first child
	    index = i; //set index
	    while( _data[(index - 1)/2] < _data[index] && index > 0 ) { //meets condition of heap property
		swap(index, (index-1)/2); //swap two elements
		index = (index-1)/2; //update index
	    }
	}
    }

    public void sort() {
	heapify(_data.length - 1); //heapify data
	for (int i = _data.length - 1; i > 0; i--) {
	    swap(0, i); //move element to sorted region
	    heapify(i - 1); //heapify the subtree
	}
    }

    public void swap(int first, int second) {  //swap helper method
	int temp = _data[first];
	_data[first] = _data[second];
	_data[second] = temp;
    }

    public String toString() {
	String ret = "";
	for (int i: data) {
	    ret += i + " ";
	}
	return ret;
    }

    public static void main (String[] args) {
	int[] arr = new int[]{7,1,12,3,5};
	HeapSort testing = new HeapSort(arr);
	System.out.println(testing);
	test.sort();
	System.out.println(testing);
    }
}
