/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Dice
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;

import java.util.Random;

/**
 * 
 **/
public class Dice
{
	private Random die;
	
	public Dice () 
	{
		die = new Random();
	} 

	/**
	 * Simulates the rolling of a set of dice
	 * @param attacking  an integer representing how many dice to use for the attack(1-3)
	 * @param defending  an integer to represent how many dice to use for the defense(1-2)
	 * @return an integer array with the results of the rolls
	 **/
	public int[] roll( int attacking, int defending ) 
	{
		int totalRolls = attacking + defending; 
		int[] results = new int[ totalRolls ];
		
		for( int i = 0; i < totalRolls; i++)
		{
			/* random number from 1 to 6 inclusive */
			results[i] = die.nextInt( 6 ) + 1;
		}
		
		return results; 
		
	}

}
// end Dice class