/*
Ricky Lin
APCS2 pd2
HW #36: Now Let’s Consider You Lot at Fake Terry’s
2018-04-17 W
*/

/*****************************************************
 * class RQueue
 * A linked-list-based, randomized queue
 * (a collection with FIIDKO property)
 *
 *       -------------------------------
 *   end |  --->   Q U E U E   --->    | front
 *       -------------------------------
 *
 *  linkages point opposite direction for O(1) en/dequeuing
 *            N <- N <- ... <- N <- N
 *      _end -^                     ^- _front
 *
 ******************************************************/

public class RQueue<T> implements Queue<T>
{
  //instance variables
  private LLNode<T> _front, _end;
  private int _size;


  // default constructor creates an empty queue
  public RQueue()
	{
    _size = 0;
	}//end default constructor


  public void enqueue( T enQVal )
  {
    LLNode<T> nNode = new LLNode<T>(enQVal, null); //initialization of new node
  	if (_front == null && _end == null) { //if adding first node
  	    _front = nNode; //set both to the new node
  	    _end = nNode;
  	}
  	else {
  	    _end.setNext(nNode); //otherwise, add the node after tail
  	    _end = _end.getNext();
  	}
    _size++;
  }//end enqueue()

  // remove and return thing at front of queue
  // assume _queue ! empty
  public T dequeue()
  {
    if ( !isEmpty() ) { //if there's something in the queue
      this.sample();  //run sample to shuffle
      T retVal = _front.getValue(); //keep value of node being removed
      if (_front == _end) { //if _front and _end are the same
        _end = null; //change _end to null
      }
      _front = _front.getNext(); //change _front to the next node
      _size--; //decrement size
      return retVal; //return retVal
    }
    return null; //return nothing as you can't dequeue nothing
  }//end dequeue()


  public T peekFront()
  {
      return _front.getValue(); //return value of _front
  }


  /******************************************
   * void sample() -- a means of "shuffling" the queue
   * Algo:
   * Given a random number within the range of the queue, iterate through the queue, incrementing ctr until it matches with random value.
   * Then, if the number you reached is not already the head, swap the cargo of the _front and the new node
   ******************************************/
  public void sample ()
  {
      int rand = (int) (Math.random() * _size);  //random number within range of queue size
      int ctr = 0;  //counter
      LLNode<T> temp = _front;  //the node you're currently on
      while (ctr != rand) {  //while counter doesn't match random number
        temp = temp.getNext();  //move pointer to next node
        ctr++;  //increment counter
      }
      if ( temp != _front ) {  //if temp and front are the same, no need to swap
        T tempVal = temp.getValue();  //save old value of temp
        temp.setValue( _front.getValue() );  //set temp cargo to front cargo
        _front.setValue( tempVal );  //set front cargo to temp cargo
      }

  }//end sample()


  public boolean isEmpty()
  {
    return (_front == null && _end == null);  //return true if both front and end are null
  } //O(1)


    // print each node, separated by spaces
  public String toString()
  {
    String ret = (String) _front.getValue();  //make string of first node
    LLNode<T> temp = _front;  //track which node we're currently on
    for ( int i = 1; i < _size; i++ ) {  //iterate through rest of queue
        temp = temp.getNext();  //move tracker to next node
        ret += " " + temp.getValue();  // add space and new value to string
    }
    return ret;  //return string
  }//end toString()

  //main method for testing
  public static void main( String[] args )
  {
      Queue<String> PirateQueue = new RQueue<String>();

      System.out.println("\nnow enqueuing...");
      PirateQueue.enqueue("Dread");
      PirateQueue.enqueue("Pirate");
      PirateQueue.enqueue("Robert");
      PirateQueue.enqueue("Blackbeard");
      PirateQueue.enqueue("Peter");
      PirateQueue.enqueue("Stuyvesant");

      System.out.println("\nnow testing toString()...");
      System.out.println( PirateQueue ); //for testing toString()...

      System.out.println("\nnow dequeuing...");
      System.out.println( PirateQueue.dequeue() );
      System.out.println( PirateQueue.dequeue() );
      System.out.println( PirateQueue.dequeue() );
      System.out.println( PirateQueue.dequeue() );
      System.out.println( PirateQueue.dequeue() );
      System.out.println( PirateQueue.dequeue() );

      System.out.println("\nnow dequeuing fr empty queue...");
      System.out.println( PirateQueue.dequeue() );
      /*v~~~~~~~~~~~~~~MAKE MORE~~~~~~~~~~~~~~v

      ^~~~~~~~~~~~~~~~AWESOME~~~~~~~~~~~~~~~^*/

  }//end main

}//end class RQueue
