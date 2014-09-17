/**
 * Risk Game Interfaces
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date September 15, 2014
 * @version 0.1
 **/

package interfaces; 
import java.util.ArrayList;

/**
 * @interface TerritoryInterface interface specifying territories on the board
 * GameBoard objects will contain Territory objects 
 **/
public interface TerritoryInterface
{

	/**
	 * @return the name of the territory
	 **/
	String getName();
	
	/**
	 * @return an ArrayList of all territories it shares a border with
	 **/
	ArrayList<Territory> getNeighbors();
	
	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	Player getOccupant();
	
	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	int getNumArmies();
	
	/**
	 * Used to set the new occupying player of a territory
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	void setOccupant( Player occupant );
	
	/**
	 * Used by the occupying player to add armies to a territory
	 * @param numArmies the number of armies to be placed into the territory
	 **/
	void setNumArmies( int numArmies );


}
// end Territory interface