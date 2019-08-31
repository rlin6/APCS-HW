class Queue<T>
{
  protected LLNode<T> _front;
  protected LLNode<T> _back;
  protected int _size;

  Queue()
  {
    _front = null;
    _back = null;
    _size = 0;
  }

  //means of removing an element from collection:
  //Dequeues and returns the first element of the queue.
  T dequeue()
  {
    LLNode<T> temp = _front;
    if (_size == 0)
      return null;

    if (_size == 1)
    {
      _front = null;
      _back = null;
    } else
    {
      _front = _front.getNext();
    }

    _size -= 1;
    return temp.getValue();
  }

  //means of adding an element to collection:
  //Enqueue an element onto the back of this queue.
  public void enqueue( T x )
  {
    LLNode<T> temp = new LLNode<T>(x, null);
    if (_front == null && _back == null)
    {
      _front = temp;
      _back = temp;
    } else
    {
      _back.setNext(temp);
      _back = _back.getNext();
    }
    _size += 1;
  }

  //Returns true if this queue is empty, otherwise returns false.
  public boolean isEmpty()
  {
    return _size == 0;
  }

  //Returns the first element of the queue without dequeuing it.
  public T peekFront()
  {
    return _front.getValue();
  }
  
  public int size()
  {
    return _size;
  }
}
