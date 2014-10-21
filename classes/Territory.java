/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Territory
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes; 
import java.util.HashMap;

/**
 * Territory class specifying territories on the board
 * GameBoard objects will contain Territory objects 
 **/
public class Territory
{
	private String name;
	private Player occupant;
	private int armies;
	private HashMap<String, Territory> neighbors;
	
	public Territory() {}
	public Territory( String name, HashMap<String, Territory> neighbors) {}

	/**
	 * @return the name of the territory
	 **/
	public String getName() { return null; }
	
	/**
	 * @return an ArrayList of all territories it shares a border with
	 **/
	public HashMap<String, Territory> getNeighbors() { return null; }
	
	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	public Player getOccupant() { return null; }
	
	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	public int getNumArmies() { return 0; } 
	
	/**
	 * Used to set the new occupying player of a territory
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	public void setOccupant( Player occupant ) { }
	
	/**
	 * Used by the occupying player to add armies to a territory
	 * @param numArmies the number of armies to be placed into the territory
	 **/
	public void setNumArmies( int numArmies ) { }


}
// end Territory class