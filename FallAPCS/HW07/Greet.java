/* Ricky Lin
   APCS1 pd2
   HW07 -- On the Origins of a BigSib
   2017-09-26 */

public class Greet
{
	public static void main(String[] args)
	{

		String greeting;

		BigSib richard = new BigSib("Word Up"); //overloaded constructor creates object with helloMsg set
		BigSib grizz = new BigSib("Hey Ya");
		BigSib dotCom = new BigSib("Sup");
		BigSib trachy = new BigSib("Salutations");
		BigSib mew = new BigSib(); //public constructor with preset helloMsg

		greeting = richard.greet("freshman");
		System.out.println(greeting);

		greeting = grizz.greet("Dr.SpaceMan");
		System.out.println(greeting);

		greeting = dotCom.greet("Kong Foo");
		System.out.println(greeting);


		greeting = trachy.greet("mom");
		System.out.println(greeting);

		greeting = mew.greet("dad");
		System.out.println(greeting);
	}
}
