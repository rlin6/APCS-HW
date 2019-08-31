interface Stack<T> 
{
    //Return true if this stack is empty, otherwise false.
    boolean isEmpty();

    //Return top element of stack without popping it.
    T peek();

    //Pop and return top element of stack.
    T pop();

    //Push an element onto top of this stack.
    void push( T x );

}//end interface
