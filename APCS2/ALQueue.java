/*Team Git: Ricky Lin, Bo Hui Lu
  APCS2 pd2
  HW #35: ...Nor Do Aussies
  2018-04-16 T
*/

import java.util.ArrayList;

public class ALQueue<Quasar> implements Queue<Quasar> {

  private ArrayList<Quasar> _queue;  //encapsulate ArrayList with this instance variable
  private int frontIndex;  //used in dequeue to refer to front of _queue

  public ALQueue() {
    _queue = new ArrayList<Quasar>();  //initializate _queue to new ArrayList
    frontIndex = 0;  //init the front index to 0
  }


//This way of removing doesn't actually "remove" the item from the AL.
//Instead, it sets index that refers to the "front" to the current one +1,
//which essentially removes reference to the old front.
  public Quasar dequeue() {
	  Quasar old = _queue.get(frontIndex);
	  frontIndex++;
	  return old;
  }

  public void enqueue( Quasar x ) {
    _queue.add(x);  //simply add the object at the end of the _queue
  }

  public boolean isEmpty() {
    return _queue.size() == 0;  //return if size is 0
  }

  public Quasar peekFront() {
    return _queue.get(frontIndex);  //return the first in the _queue
  }

  public static void main(String[] args) {
    ALQueue bob = new ALQueue();
    System.out.println( bob.isEmpty() ); //expect true
    bob.enqueue(4);
    bob.enqueue(5);
    bob.enqueue(34);
    bob.enqueue(32);
    System.out.println( bob.peekFront() ); //expect 4
    System.out.println( bob.isEmpty() );  //expect false
    System.out.println( bob.dequeue() );  //expect 4
    System.out.println( bob.peekFront() ); //expect 5
    System.out.println( bob.dequeue() );  //expect 5
    System.out.println( bob.peekFront() ); //expect 34
    System.out.println( bob.dequeue() );  //expect 34
    System.out.println( bob.peekFront() ); //expect 32
    System.out.println( bob.dequeue() );  //expect 32
  }
}
