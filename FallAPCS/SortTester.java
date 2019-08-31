import java.util.ArrayList;

public class SortTester {
    public static void sop(Object s) {
	System.out.println(s);
    }
    public static void main (String[] args) {
	//Tests for Bubble Sort
	
	ArrayList<Comparable> xlist=new ArrayList<Comparable>();
	System.out.println(MySorts.populate(10,1,100));
        for (int i=5;i>=0;i--) {
	    xlist.add(i);
	}
	sop(xlist);

	MySorts.bubbleSortV(xlist);
	sop(xlist);

	
    }
}
