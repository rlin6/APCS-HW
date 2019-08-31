/*
Ricky Lin
APCS2 pd02
HW #44: Prune Your Tree
2018-05-01 W
*/

/*****************************************************
 * class BST - v1:partial
 * Implementation of the BINARY SEARCH TREE abstract data type (ADT)
 *
 * A BST maintains the invariant that, for any node N with value V,
 * L<V && V<R, where L and R are node values in N's left and right
 * subtrees, respectively.
 * (Any value in a node's left subtree must be less than its value,
 *  and any value in its right subtree must be greater.)
 * This BST only holds ints (its nodes have int cargo)
 *****************************************************/

public class BST
{

  //instance variables / attributes of a BST:
  TreeNode _root;

  /*****************************************************
   * default constructor
   *****************************************************/
  BST( )
  {
    _root = null;
  }


  /*****************************************************
   * void insert( int )
   * Adds a new data element to tree.
   *****************************************************/
  public void insert( int newVal )
  {
    TreeNode newNode = new TreeNode( newVal );

    if ( _root == null ) {
      _root = newNode;
      return;
    }
    insert( _root, newNode );
  }
  //recursive helper for insert(int)
  public void insert( TreeNode stRoot, TreeNode newNode )
  {
    if ( newNode.getValue() < stRoot.getValue() ) {
      //if no left child, make newNode the left child
      if ( stRoot.getLeft() == null )
        stRoot.setLeft( newNode );
      else //recurse down left subtree
        insert( stRoot.getLeft(), newNode );
      return;
    }
    else { // new val >= curr, so look down right subtree
      //if no right child, make newNode the right child
      if ( stRoot.getRight() == null )
        stRoot.setRight( newNode );
      else //recurse down right subtree
        insert( stRoot.getRight(), newNode );
      return;
    }
  }//end insert()




  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //~~~~~~~~~~~~~v~~TRAVERSALS~~v~~~~~~~~~~~~~~~~~~~~~

  // each traversal should simply print to standard out
  // the nodes visited, in order

  //process root, recurse left, recurse right
  public void preOrderTrav()
  {
    preOrderTrav( _root );
  }
  public void preOrderTrav( TreeNode currNode )
  {
    if ( currNode == null ) //stepped beyond leaf
      return;
    System.out.print( currNode.getValue() + " " );
    preOrderTrav( currNode.getLeft() );
    preOrderTrav( currNode.getRight() );
  }

  //recurse left, process root, recurse right
  public void inOrderTrav()
  {
    inOrderTrav( _root );
  }
  public void inOrderTrav( TreeNode currNode )
  {
    if ( currNode == null ) //stepped beyond leaf
      return;
    inOrderTrav( currNode.getLeft() );
    System.out.print( currNode.getValue() + " " );
    inOrderTrav( currNode.getRight() );
  }

  //recurse left, recurse right, process root
  public void postOrderTrav()
  {
    postOrderTrav( _root );
  }
  public void postOrderTrav( TreeNode currNode )
  {
    if ( currNode == null ) //stepped beyond leaf
      return;
    postOrderTrav( currNode.getLeft() );
    postOrderTrav( currNode.getRight() );
    System.out.print( currNode.getValue() + " "  );
  }

  //~~~~~~~~~~~~~^~~TRAVERSALS~~^~~~~~~~~~~~~~~~~~~~~~
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /*****************************************************
     * TreeNode search(int)
     * returns pointer to node containing target,
     * or null if target not found
     *****************************************************/
    TreeNode search( int target )
    {
      return search( target, _root );  //run helper method
    }

    TreeNode search( int target, TreeNode currNode ) {
      if ( currNode == null ) {  //if target not found
        return null;
      }
      else if ( currNode.getValue() == target ) { //if target found
        return currNode;
      }
      else if ( currNode.getValue() > target ) {  //if target is less than currNode
        return search( target, currNode.getLeft() );  //recursive run to left subtree
      }
      else {  //if greater than
        return search( target, currNode.getRight() ); //recursive run to right subtree
      }
    }


    /*****************************************************
     * int height()
     * returns height of this tree (length of longest leaf-to-root path)
     * eg: a 1-node tree has height 0
     *****************************************************/
    public int height()
    {
      return height( _root );  //run the recursive function on root
    }

    public int height( TreeNode currNode ) {
      if (currNode == null) { //if you go pass the tree
	       return -1; //minus one
	    }
	    int left = height(currNode.getLeft()); //height of left subtree
      int right = height(currNode.getRight()); //height of right subtree
      int runs; //keep track of # of times you run the recursions
      if (left > right) { //check for greater height
	       runs = left; //set runs to that height
      }
      else {
	       runs = right;
      }
	    return 1 + runs; //return recursive call
    }

    /*****************************************************
     * int numLeaves()
     * returns number of leaves in tree
     *****************************************************/
    public int numLeaves()
    {
      return numLeaves(_root);  //use helper
    }

    public int numLeaves( TreeNode currNode ) {
      if (currNode == null) {
        return 0;  //if current node is null, return 0
      }
      else if ( currNode.getLeft() == null && currNode.getRight() == null ) {
        return 1; //if node doesn't connect to anymore nodes, it's a leaf
      }
      return numLeaves(currNode.getLeft()) + numLeaves(currNode.getRight()); //recursive call on two subtrees of the tree
    }

    public TreeNode remove( int target ) {
      return remove( target, _root );
    }

    public TreeNode remove ( int target, TreeNode currNode ) {
      TreeNode leader = currNode;
      TreeNode follower = currNode;
      if ( leader.getValue() == target ) {
        if ( leader.getLeft() == null && leader.getRight() == null ) {
          follower.setLeft(null);
        }
      }
    }

  //main method for testing
  public static void main( String[] args )
  {
    BST arbol = new BST();

    //PROTIP: sketch state of tree after each insertion
    //        ...BEFORE executing these.
    arbol.insert( 4 );
    arbol.insert( 2 );
    arbol.insert( 5 );
    arbol.insert( 6 );
    arbol.insert( 1 );
    arbol.insert( 3 );
    arbol.insert( 0 ); //testing when longest edge is unfilled
    arbol.insert( -1 );

    System.out.println( "\n-----------------------------");
    System.out.println( "pre-order traversal:" );
    arbol.preOrderTrav();

    System.out.println( "\n-----------------------------");
    System.out.println( "in-order traversal:" );
    arbol.inOrderTrav();

    System.out.println( "\n-----------------------------");
    System.out.println( "post-order traversal:" );
    arbol.postOrderTrav();

    System.out.println( "\n-----------------------------");

    System.out.println( arbol.height() );  //expect 4
    System.out.println( arbol.numLeaves() );  //expect 3
    System.out.println( arbol.search( -1 ) ); //expect node
    System.out.println( arbol.search( 8 ) ); //expect null
    /*~~~~~~~~~~~~move~me~down~~~~~~~~~~~~~~~~~~~~~~
      <<< YOUR NEW TEST CALLS HERE >>>
      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  }

}//end class
