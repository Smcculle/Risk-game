/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Continent
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;
import java.util.ArrayList;


/**
 * Continent class specifying continents on the board
 * GameBoard objects will contain Continent objects 
 **/
public class Continent
{
	/* Instance variables */
	private String name;
	private Player occupant;
	private int bonusArmies;
	
	public Continent() { } 
	
	public Continent(String name, int bonusArmies) { }

	/**
	 * @return the name of the continent as a String
	 **/
	public String getName() { return null; }
	
	/**
	 * If no player owns all territories on a continent then this method should 
	 * return null
	 * @return a reference to the Player object that currently owns this continent
	 **/
	public Player getOccupant() { return null; }
	
	/**
	 * @return the number of additional armies a player gets for owning this continent
	 **/
	public int getNumBonusArmies() { return 0; }
	
	/**
	 * Sets which player owns a continent, if any
	 * @param occupant a reference to the Player object that now owns this continent
	 **/
	public void setOccupant( Player occupant ) { }
	
	// May also want
	
	/**
	 * @return true of there is a player that owns all the territories on this continent
	 **/
	public boolean isOccupied() { return false; }
	
	/**
	 * @return an ArrayList of all the territories on the continent
	 **/
	public ArrayList<Territory> getTerritories() { return null; }

}
// end Continent class
