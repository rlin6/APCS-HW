//Ricky Lin
//APCS2 pd2
//HW#34 -- The English Do Not Wait In Line
//2018-04-16

public class NodeQueue<Quasar> implements Queue<Quasar> {

    LLNode<Quasar> _head; //1st pointer
    LLNode<Quasar> _tail; //last pointer

    public NodeQueue () {
	_head = null; //both null
	_tail = null;
    }

    public Quasar dequeue () {
	if ( !isEmpty() ) { //if there's something in the queue
	    Quasar retVal = _head.getValue(); //keep value of node being removed
	    if (_head == _tail) { //if _head and _tail are the same
		_tail = null; //change tail to null
	    }
	    _head = _head.getNext(); //change head to the next node
	    return retVal; //return retVal
	}
	return null; //return nothing as you can't dequeue nothing
    }

    public void enqueue (Quasar x) {
	LLNode<Quasar> nNode = new LLNode<Quasar>(x, null); //initialization of new node
	if (_head == null && _tail == null) { //if adding first node
	    _head = nNode; //set both to the new node
	    _tail = nNode;
	}
	else {
	    _tail.setNext(nNode); //otherwise, add the node after tail
	    _tail = _tail.getNext();
	}
    }

    public boolean isEmpty() {
	return (_head == null && _tail == null); //return if both _head and _tail empty
    }

    public Quasar peekFront () {
	return _head.getValue(); //look at value of _head
    }

}
