public class Equal {
	public static void main (String[] args) {
		BigSib bs1 = new BigSib("foo");
		BigSib bs2 = new BigSib("foo");
		BigSib bs3 = new BigSib("foo");
   
		System.out.println(bs1);  //testing equality using == and .equals between each BigSib object
		System.out.println(bs2);
		System.out.println(bs3);
		System.out.println(bs1 == bs2);
		System.out.println(bs1 == bs3);
		System.out.println(bs2 == bs3);
		System.out.println(bs1.equals(bs2));
		System.out.println(bs1.equals(bs3));
    }
}
