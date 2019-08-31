import java.util.LinkedList;

class LLStack<T> implements Stack<T> {

  protected LinkedList<T> _link;

  LLStack() {
    _link = new LinkedList<T>();
  }

  //Return true if this stack is empty, otherwise false.
  boolean isEmpty() {
    return _link.size()==0;
  }

  //Return top element of stack without popping it.
  T peek() {
    T retVal = null;
    if (isEmpty()) {
      return null;
    }
    retVal = _link.get( _link.size() - 1 );
    return retVal;
  }

  //Pop and return top element of stack.
  T pop() {
    T retVal = null;
    if (isEmpty()) {
      return null;
    }
    retVal = _link.remove( _link.size() - 1 );
    return retVal;
  }

  //Push an element onto top of this stack.
  void push( T x ) {
    _link.add(x);
  }
}
