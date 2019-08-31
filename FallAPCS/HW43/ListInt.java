// Team Something -- Ricky Lin, Nadine Jackson
// APCS1 pd2
// HW43 -- adhering to a published standard (implementing an interface)
// 2017-11-30

/***************************
 * interface ListInt
 * Declares methods that will be implemented by any
 *  class wishing to adhere to this specification.
 * This interface specifies behaviors of a list of ints.
 ***************************/

public interface ListInt
{ //list interface with add, add at index, get, set, size, and remove methods

  //add at end and return true
  boolean add( int x);

  //add at index
  void add( int index, int num);

  //retrieve int at index
  int get( int index);

  //replace element at index with new value
  int set( int index, int num);

  //return # of elements
  int size();

  //remove int at index, shifts everything past it left, and return removed num
  int remove (int index);
}
