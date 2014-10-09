/**
 * Homework 4 Binary Tree & Recursion
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date October 07, 2014
 * @version 0.1
 **/
 
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;


/* BinaryTreeExample reads a serialized ArrayList<Integer> in from a file
 * supplied by argument, deserializes it, and stores the integers into a tree 
 * using the BinaryTree class.  It then prints the tree using in-order traversal. 
 */
public class BinaryTreeExample
{
	//This suppresses our unchecked cast when deserializing the ArrayList
	@SuppressWarnings("unchecked")
	public static void main( String[] args)
	{
		// If there is not a correct number of arguments print usage message
		if( args.length != 1 )
		{
			System.out.println( "Usage: BinaryTreeExample filename" );
			System.out.println( "where filename contains a serialized ArrayList<Integer>" );
			
		}
		
		else
		{
			BinaryTree newTree;
			ObjectInputStream objReader = null; 
			ArrayList<Integer> data = null;
					
			//try to read object from a file 
			try
			{
				//the file for reading is stored in args[0] as in usage above
				objReader = new ObjectInputStream( new FileInputStream( args[0] ) );
				
				
				data = (ArrayList<Integer>)objReader.readObject(); 
				objReader.close();
			}
			catch( IOException e)
			{
				System.err.println( "Error in file I/O" );
				System.err.println( e.getMessage() );
			}
			catch( ClassNotFoundException e)
			{
				System.err.println( "Error casting object" ); 
				System.err.println( e.getMessage() );
			}	
			
			//Add the contents of the ArrayList to the binary tree
			newTree = new BinaryTree( data.get( 0 ) );
			for(int i = 1; i < data.size(); i++)
				newTree.insert( data.get(i) );
			
			System.out.println( "\nDeserialized array below:" );
			for( int item : data )
			{
				System.out.printf( "%d ", item );
			}
			System.out.println( "\n\nIn-order traversal: ");
			
			//Calls the in-order traversal and prints to system.out
			newTree.inOrder();
			System.out.println();
		}
		
	}

}