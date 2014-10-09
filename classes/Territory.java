/**
 * Risk Game Interfaces
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date September 15, 2014
 * @version 0.1
 **/

package classes; 
import java.util.ArrayList;

/**
 * Territory class specifying territories on the board
 * GameBoard objects will contain Territory objects 
 **/
public class Territory
{

	/**
	 * @return the name of the territory
	 **/
	String getName() { return null; }
	
	/**
	 * @return an ArrayList of all territories it shares a border with
	 **/
	ArrayList<Territory> getNeighbors() { return null; }
	
	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	Player getOccupant() { return null; }
	
	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	int getNumArmies() { return 0; } 
	
	/**
	 * Used to set the new occupying player of a territory
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	void setOccupant( Player occupant ) { }
	
	/**
	 * Used by the occupying player to add armies to a territory
	 * @param numArmies the number of armies to be placed into the territory
	 **/
	void setNumArmies( int numArmies ) { }


}
// end Territory class