/* Ricky Lin
   APCS1 pd2
   HW#48 -- Keep Guessing
   2017-12-07R */

/*==================================================
  class GuessNumber -- fun fun fun!

  eg, sample interaction with end user:
  Guess a # fr 1-100: 50
  Too high
  Guess a # fr 1-49: 25
  Too low
  Guess a # fr 26-49: 38
  Correct! It took 3 guesses
  ==================================================*/

import cs1.Keyboard;

public class GuessNumber 
{
    //instance vars
    private int _lo, _hi, _guessCtr, _target;

    /*==================================================
      constructor -- initializes a guess-a-number game
      pre:  
      post: _lo is lower bound, _hi is upper bound,
      _guessCtr is 1, _target is random int on range [_lo,_hi]
      ==================================================*/
    public GuessNumber( int a, int b ) {
		_lo = a;
		_hi = b;
		_guessCtr = 1;
		_target = (int) (Math.random() * (_hi - _lo) + _lo);  //target is a number from [_lo-_hi)   
    }


    /*==================================================
      void playRec() -- Prompts a user to guess until guess is correct.
      Uses recursion.
      pre:  
      post: 
      ==================================================*/
    public void playRec() {
		System.out.println("Guess a number from " + _lo + "-" + _hi); //prints your range of choices first always
		int guess = Keyboard.readInt();  //your input becomes your guess
        if (guess == _target) {
	    	System.out.println("Correct! It only took " + _guessCtr + " tries!");  //if your guess is the target, you win and it tells how many tries you used
		}

		else {  //if you're not right
			if (guess > _hi) {  //if you're too high
				System.out.println("WAY TOO HIGH");
			}

			else if (guess < _lo && guess != -2147483648) {  //if you're too low
				System.out.println("WAY TOO LOW");
			}

			else if (guess == -2147483648) {  //if you don't put in an int
				System.out.println("NOT AN INT");
			}

			else if (guess < _target) {  //if your guess is reasonably less than the target, tells you it's too low and changes the low range to 1 more of your guess
				_lo = guess + 1;
				System.out.println("Too Low, Try Again...");
			}

			else {  //if it's reasonably more, it tells you that and changes the high range to 1 less of your guess
				_hi = guess - 1;
				System.out.println("Too High, Try Again...");
			}

			_guessCtr += 1;  //add a guess count
			playRec();   //run the function again with different lo/hi or with the same values if your input was invalid
		}
    }		       


    /*==================================================
      void playIter() -- Prompts a user to guess until guess is correct.
      Uses iteration.
      pre:  
      post: 
      ==================================================*/
    public void playIter() {
		int guess = 0;  //create an int to hold your input
		
		while (guess != _target) {  //while your guess is off
			System.out.println("Guess a number from " + _lo + "-" + _hi);  //present you with your possible choices
			guess = Keyboard.readInt();  //guess becomes the choice you picked
			
			if (guess == _target) {  //if your guess is right, tell you how many tries you took and breaks out of the loop
				System.out.println("Correct! It only took " + _guessCtr + " tries!");
				break;
			}

			else if (guess > _hi) {  //tells you when it's unreasonably high
				System.out.println("WAY TOO HIGH");
			}

			else if (guess < _lo && guess != -2147483648) {  //tells you when it's unreasonably low
				System.out.println("WAY TOO LOW");
			}

			else if (guess == -2147483648) {  //tells you if your input is not an int
				System.out.println("NOT AN INT");
			}

			else if (guess < _target) { //if your guess is reasonably less than the target, tells you it's too low and changes the low range to 1 more of your guess
				_lo = guess + 1;
				System.out.println("Too Low, Try Again...");
			}

			else {   //if it's reasonably more, it tells you that and changes the high range to 1 less of your guess
				_hi = guess - 1;
				System.out.println("Too High, Try Again...");
			}

			_guessCtr += 1;  //increment the guess count
		}   
    }


    //wrapper for playRec/playIter to simplify calling
    public void play() { 
		//use one or the other below:
		//playRec();
		playIter();
    }

    //main method to run it all
    public static void main( String[] args ) {
		//instantiate a new game
		GuessNumber g = new GuessNumber(1,100);

		//start the game
		g.play();
    }//end main

}//end class
