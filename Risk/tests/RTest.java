package tests; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import classes.Dice;


public class RTest {
	
	public static void main( String [] args )
	{
		Dice d = new Dice();
		
		int[] arr;
		
		printlne( arr );
		
		arr = d.roll(3, 2);
		printlne(arr );
		
		arr = d.roll(1, 1);
		printlne( arr );
		
		arr = d.roll(1, 2);
		printlne( arr );
		
		arr = d.roll(2, 2);
		printlne( arr );
		
		
	}
	public static void printlne( int[] arr )
	{
		for( int i : arr)
		{
			System.out.print( i + " " );
		}
		System.out.println();
	}


}
