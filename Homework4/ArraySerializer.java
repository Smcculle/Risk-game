import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.ClassNotFoundException;

public class ArraySerializer
{

	public static void main( String[] args )
	{
		// If there is not a correct number of arguments print usage message
		if( args.length != 2 )
		{
			System.out.println( "Usage: ArraySerializer size filename" );
			System.out.println( "  or ArraySerializer -r filename to read a serialized file" );
		}
		// if the argument is -r read a serailzied file and output the elements
		else if( args[0].equals( "-r") )
		{
			readSer( args[1] );
		}
		// else generate an ArrayList of random integers and serialize it
		else
		{
			int size = Integer.parseInt( args[0] );
			String filename = args[1] + ".ser";
			ObjectOutputStream writer;
			ArrayList<Integer> data = new ArrayList<Integer>();
			Random generator = new Random();

			for(int index = 0; index < size; index++) data.add( generator.nextInt( size ) );
		
			try
			{
				writer = new ObjectOutputStream( new FileOutputStream( filename ) );
				writer.writeObject( data );
				writer.close();
			}
			catch( IOException ioException )
			{
				System.out.println( "Unable to create or open file " + filename );
				System.out.println( ioException.getMessage() );
			}
		}
		
	}

	/**
	 * Method to read serialized ArrayLists of Integers and print the elements to the screen
	 * @param filename the name of the serialized object file
	 **/
	public static void readSer( String filename )
	{
		// Create a serialized object reader
		ObjectInputStream reader;
		ArrayList<Integer> data;

		// Try to open and deserialize the ArrayList object
		try
		{
			reader = new ObjectInputStream( new FileInputStream( filename ) );
			data = (ArrayList<Integer>) reader.readObject();

			// Print the elements stored in the serialized ArrayList to ensure that it properly recovered the data
			System.out.println( "Deserialized Data: " );

			for( int item : data )
			{
				System.out.printf( "%d ", item );
			}

		}
		catch( IOException ioException )
		{
			System.out.println( "Unable to open or read file " + filename );
			System.out.println( ioException.getMessage() );

		}
		catch( ClassNotFoundException classException )
		{
			System.out.println( "Unable to restore data in serialized file to orignial type." );
			System.out.println( classException.getMessage() );
		}
		
	}
}
