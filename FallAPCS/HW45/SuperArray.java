// Ricky Lin
// APCS1 pd2
// HW45 -- In America, the Driver Sits on the Left
// 2017-12-04

/***************************
 * class SuperArray version 3.0
 * ( SKELETON )
 * Wrapper class for array. Facilitates resizing,
 * resizing
 * expansion
 * read/write capability on elements
 * adding an element to end of array
 * adding an element at specified index
 * removing an element at specified index
 *
 * ...and now SuperArray complies with the specifications of the
 * List interface. (List.java must be in same dir as this file)
 ***************************/

import java.util.Collection;

public class SuperArray implements List
{
  private Object[] _data;  //underlying container
  private int _size;    //number of elements in this SuperArray

  //default constructor – initializes 10-item array
  public SuperArray()
  {
    _data = new Object[10];
    _size = 0;
  }


  //output SuperArray in [a,b,c] format
  public String toString()
  {
    String foo = "[";
    for( int i = 0; i < _size; i++ )
    {
      foo += _data[i] + ",";
    }
    if ( foo.length() > 1 )
    //shave off trailing comma
      foo = foo.substring( 0, foo.length() - 1 );
    foo += "]";
    return foo;
  }


  //double capacity of SuperArray
  private void expand()
  {
    Object[] temp = new Object[ _data.length * 2 ];
    for( int i = 0; i < _data.length; i++ )
      temp[i] = _data[i];
    _data = temp;
  }


  //accessor -- return value at specified index
  public Object get( int index )
  {
    return _data[index];
  }


  //mutator -- set value at index to newVal,
  //           return old value at index
  public int set( int index, int newVal )
  {
    int temp = _data[index];
    _data[index] = newVal;
    return temp;
  }


  //adds an item after the last item
  public boolean add( int num )
  {
    add( _size, num );
    return true;
  }


  //inserts an item at index
  public void add( int index, int newVal )
  { //O(1) adding at end, O(n) adding at beginning
    //first expand if necessary
    if ( _size >= _data.length )
      expand();
    for( int i = _size; i > index; i-- )
    {
      _data[i] = _data[i-1]; //each slot gets value of left neighbor
    }
    _data[index] = newVal;
    _size++;
  }


  //removes the item at index
  //shifts elements left to fill in newly-empted slot
  public int remove( int index )
  { //O(1) for element at end, O(n) for element at beginning
    int oldVal = _data[index];
    for( int i = index; i < _size - 1; i++ )
    {
      _data[i] = _data[i+1]; //shifts everything after index left
    }
    _size--;
    return oldVal;
  }


  //return number of meaningful items in _data
  public int size()
  {
    return _size;
  }



  //main method for testing
  public static void main( String[] args )
  {

    ListInt mayfield = new SuperArray();
    System.out.println("Printing empty SuperArray mayfield...");
    System.out.println(mayfield);

    mayfield.add(5);
    mayfield.add(4);
    mayfield.add(3);
    mayfield.add(2);
    mayfield.add(1);

    System.out.println("Printing populated SuperArray mayfield...");
    System.out.println(mayfield);

    mayfield.remove(3);
    System.out.println("Printing SuperArray mayfield post-remove...");
    System.out.println(mayfield);
    mayfield.remove(3);
    System.out.println("Printing SuperArray mayfield post-remove...");
    System.out.println(mayfield);

    mayfield.add(3,99);
    System.out.println("Printing SuperArray mayfield post-insert...");
    System.out.println(mayfield);
    mayfield.add(2,88);
    System.out.println("Printing SuperArray mayfield post-insert...");
    System.out.println(mayfield);
    mayfield.add(1,77);
    System.out.println("Printing SuperArray mayfield post-insert...");
    System.out.println(mayfield);

  }//end main()


}//end class


/***
,,########################################,,
.*##############################################*
,*####*:::*########***::::::::**######:::*###########,
.*####:    *#####*.                 :*###,.#######*,####*.
*####:    *#####*                      .###########*  ,####*
.*####:    ,#######,                        ##########*    :####*
*####.    :#########*,                       ,,,,,,,,.      ,####:
####*  ,##############****************:,,               .####*
:####*#####################################**,        *####.
*############################################*,   :####:
.#############################################*,####*
:#####:*****#####################################.
*####:                  .,,,:*****###########,
.*####,                            *######*
.####* :*#######*               ,#####*
*###############*,,,,,,,,::**######,
*##############################:
*####*****##########**#####*
.####*.            :####*
:####*         .#####,
*####:      *####:
.*####,  *####*
:####*####*
*######
*##

-Miranda Chaiken '16

***/
