package tests; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import classes.Territory;


public class RiskTest {

	private HashMap<String, Territory> myT;
	
	
	public RiskTest() 
	{
		myT = new HashMap<String, Territory>();
	}
	
	public static void main( String [] args )
	{
		RiskTest myR = new RiskTest();
		myR.open( "config.txt" );
		
		//System.out.println( myR.myT.keySet() );
		//System.out.println( myR.myT.values() );
		for( Territory t : myR.myT.values() )
		{
			
			System.out.print( t.getName() + ": ");
			for( Territory t2 : t.getNeighbors().values() )
			{
				if( t2 == null )
					System.out.println( "null" );
				
				if( myR.myT.containsKey( "" ) )
					System.out.println( "DOES CONTAIN KEY: " + "");
				boolean tn = true; 
				//System.out.println( s + " = " + p.getName() );
				if( tn == false )
					System.out.println( "Big error ");
				
				
			}
			//HashMap<String, Territory> myN = t.getNeighbors();
			//System.out.print("Adjacencies: " + myN + "\n" );
			/*for( Territory t2 : myN.values() )
			{
				System.out.print( t2.getName() + " ");
				System.out.println( t.isAdjacent(t2) && t2.isAdjacent(t) );
			}*/
			System.out.println();
		}

		
	}

	public void open( String filename )
	{
		
		/* No way for the user to call save with filename = null, so we don't need to check NPE */
		File inFile = new File( filename );
		
		/* if the file does not exist, is not accessible, is not regular, print error message and return */
		if( !( inFile.exists() && inFile.canRead() && inFile.isFile() ) )
		{
			System.out.printf( "Provided filename %s is not valid. ", filename );
			return;
		}
		
		/* if the file is valid, use bufferedreader in a try statement to open */
		else
		{
			/* try to open file, catch FileNotFoundException */
			try
			{
				FileReader fin = new FileReader( inFile );
				BufferedReader bin = new BufferedReader( fin );
				String sCurrentLine; 
				
				/* try to read file, catch IOException */
				try
				{
					/* try to read line, returns null if EOF */
					while( (sCurrentLine = bin.readLine() ) != null )
					{
						/* get rid of quotations and split by comma into array 
						 * split[0] contains ID, split[1] contains name, split[2] contains species*/
						String[] split = sCurrentLine.split(",");
						HashMap<String, Territory> tAdj = new HashMap<String, Territory>();
						for( int i = 1; i < split.length; i++ )
						{
							tAdj.put( new String(split[i]), myT.get(split[i]) );
						}
						myT.put( new String(split[0]), new Territory( split[0], tAdj ) );
						
					}
					
					/*done reading file, perform cleanup by closing it */
					bin.close();
					
				}
				catch( IOException e )
				{
					System.err.println( "Couldn't read file: " + e.getMessage() );
				}
			} 
			/* TOCTOU error occurred */
			catch (FileNotFoundException e) 
			{
				System.err.printf( "File %s is no longer readable. %n", inFile );
				e.printStackTrace();
			}
			
		}
		
	}

}
