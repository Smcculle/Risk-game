/**
 * Homework 4 Binary Tree & Recursion
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date October 07, 2014
 * @version 0.1
 **/

/**
*Class implementing a binary tree
*with in order traversal
**/

public class BinaryTree
{
	// Instance variables
	private int data;
	private BinaryTree leftSubTree;
	private BinaryTree rightSubTree;

	/** Constructor:
	* Parameters: int value contains the data being stored
	* BinaryTree instance variables do not contain sub-trees
	* initially, so they are set to null.
	**/
	public BinaryTree( int value )
	{
		this.data = value;
		this.leftSubTree = null;
		this.rightSubTree = null;
	}
	
	/**
	* Returns the value stored in this node
	**/
	public int getData()
	{
		return this.data;
	}
	
	/**
	* Method insert, adds a value to the tree
	* Parameters: int value contains the value to be added
	**/
	public void insert( int value )
	{
		
		//Insert into left subtree if value is less than or equal to data
		if( value <= this.data ) 
		{
			//insert if left subtree is null
			if( this.leftSubTree == null )
			{
				//Create a new tree with the provided value
				this.leftSubTree = new BinaryTree ( value );
			}
			
			//if left subtree is not null, call insert recursively on subtree
			else
			{
				this.leftSubTree.insert( value );
			}
			
		}
		
		//Repeat same steps for right side if value is greater than data
		if( value > this.data ) 
		{
			//insert if right subtree is null
			if( this.rightSubTree == null )
			{
				//Create a new tree with the provided value
				this.rightSubTree = new BinaryTree ( value );
			}
			
			//if right subtree is not null, call insert recursively on subtree
			else
			{
				this.rightSubTree.insert( value );
			}
			
		}		

	}

	/**
	* Method inOrder traverses the tree following the in order
	* traversal algorithm. When each node is ‘visited’ by the
	* algorithm print the value at that node to the screen
	**/
	public void inOrder()
	{
		//Traverse left subtree until we reach null
		if( this.leftSubTree != null )
			this.leftSubTree.inOrder();
		
		//Executes after reaching null as a left subtree, 'visiting' that node.
		System.out.printf( "%d ", this.data ); 
		
		/* Begin right transversal, which will then start over traversing
		 * every leftSubTree of that right branch until null
		 */
		if( this.rightSubTree != null )
			this.rightSubTree.inOrder();
		
	}
	
		
}