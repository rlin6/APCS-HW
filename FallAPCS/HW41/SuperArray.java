// Ricky Lin
// APCS1 pd2
// HW41 -- Array of Steel
// 2017-11-28

/***************************
 * class SuperArray version 1.0
 * ( SKELETON )
 * Wrapper class for array. Facilitates resizing,
 * getting and setting element values.
 ***************************/

public class SuperArray
{
  private int[] _data;  //underlying container
  private int _size;    //number of elements in this SuperArray

  //default constructor – initializes 10-item array
  public SuperArray()
  {
    _data = new int[10];
    _size = 10;
  }


  //output SuperArray in [a,b,c] format
  public String toString()
  {
    String foo = "[";
    for(int i = 0; i < _size; i++)
    {
      foo += _data[i] + ",";
    }
    if ( foo.length() > 1 )
      //shave off trailing comma
      foo = foo.substring(0, foo.length() - 1);
    foo += "]";
    return foo;
  }


  //double capacity of SuperArray
  private void expand()
  {
    int[] temp = new int[ _data.length * 2 ];
    for( int i = 0; i < _data.length; i++ )
      temp[i] = _data[i];  //copy over elements
    _data = temp;
    _size *= 2;
  }


  //accessor -- return value at specified index
  public int get(int index)
  {
    return _data[index];
  }


  //mutator -- set value at index to newVal,
  //           return old value at index
  public int set(int index, int newVal)
  {
    int temp = _data[index];
    _data[index] = newVal;
    return temp;
  }

  //main method for testing
  public static void main( String[] args )
  {

    SuperArray mayfield = new SuperArray();
    System.out.println("Printing empty SuperArray mayfield...");
    System.out.println(mayfield);

    for( int i = 0; i < curtis._data.length; i++ )
    {
      mayfield.set( i, i * 2 );
    }

    System.out.println("Printing populated SuperArray mayfield...");
    System.out.println(mayfield);

    for( int i = 0; i < 3; i++ )
    {
      mayfield.expand();
      System.out.println("Printing expanded SuperArray mayfield...");
      System.out.println(mayfield);
    }

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
