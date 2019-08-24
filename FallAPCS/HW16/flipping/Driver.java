/* Ricky Lin
   APCS1 pd2
   HW#16 Get It While You can 
   2017-10-12 */

public class Driver
{
    public static void main( String[] args )
    {

	//build Objects from blueprint specified by class Coin

	//test default constructor
	Coin mine = new Coin();

	//test 1st overloaded constructor
	Coin yours = new Coin( "quarter" );

	//test 2nd overloaded constructor
	Coin wayne = new Coin( "dollar", "heads" );

	//test toString() methods of each Coin
	System.out.println("mine: " + mine);
	System.out.println("yours: " + yours);
	System.out.println("wayne: " + wayne);

	//test flip() method
	System.out.println("\nAfter flipping...");
	yours.flip();
	wayne.flip();
	System.out.println("yours: " + yours);
	System.out.println("wayne: " + wayne);

	//test equals() method
	if ( yours.equals(wayne) ) 
	    System.out.println( "Matchee matchee!" );
	else
	    System.out.println( "No match. Firestarter you can not be." );
	
	//reset the counters 
	yours.setheadsCtr(0);
	yours.settailsCtr(0);
	yours.setflipCtr(0);
	
	// while loop that runs while there's less than 7 head flips 
	while (yours.getheadsCtr() < 7) {
	    yours.flip();
	}
	
	//prints all the results of the flips
	System.out.println(yours.getheadsCtr());
	System.out.println(yours.gettailsCtr());
	System.out.println(yours.getflipCtr());
	
	//reset the counters 
	yours.setheadsCtr(0);
	yours.settailsCtr(0);
	yours.setflipCtr(0);
	wayne.setheadsCtr(0);
	wayne.settailsCtr(0);
	wayne.setflipCtr(0);
	
	//variable to keep track of matches
	int match = 0; 
	
	// while loop that runs while there's less than 8 matches  
	while (match < 8) {
	    yours.flip();
	    wayne.flip();
	    if (yours.equals(wayne)) {
			match += 1;
	    }
	}

	//prints all the results of the flips
	System.out.println(yours.getheadsCtr());
	System.out.println(yours.gettailsCtr());
	System.out.println(yours.getflipCtr());
	System.out.println(wayne.getheadsCtr());
	System.out.println(wayne.gettailsCtr());
	System.out.println(wayne.getflipCtr());
	
	//reset the counters 
	yours.setheadsCtr(0);
	yours.settailsCtr(0);
	yours.setflipCtr(0);
	wayne.setheadsCtr(0);
	wayne.settailsCtr(0);
	wayne.setflipCtr(0);
	match = 0;
	
	// while loop that runs while there's less than 13000 matches and not divisible by 2001 
	while (match < 13000 || match % 2001 != 0) {
	    yours.flip();
	    wayne.flip();
	    if (yours.equals(wayne)) {
			match += 1;
	    }
	}
	
	//prints all the results of the flips
	System.out.println(yours.getheadsCtr());
	System.out.println(yours.gettailsCtr());
	System.out.println(yours.getflipCtr());
	System.out.println(wayne.getheadsCtr());
	System.out.println(wayne.gettailsCtr());
	System.out.println(wayne.getflipCtr());

    }//end main()

}//end class
