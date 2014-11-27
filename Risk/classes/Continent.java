/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Continent
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;

import java.util.HashMap;
import java.util.Map;


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
	private Map<String, Territory> territories; 
	
	public Continent() { } 
	
	public Continent(String name, int bonusArmies) 
	{ 
		this( name, bonusArmies, new HashMap<String, Territory>() );
		
	}
	
	public Continent( String name, int bonusArmies, Map<String,Territory> territories )
	{
		this.name = name; 
		this.bonusArmies = bonusArmies;
		occupant = null; 
		this.territories = territories;
	}

	/**
	 * @return the name of the continent as a String
	 **/
	public String getName() 
	{
		return name; 
	}
	
	/**
	 * If no player owns all territories on a continent then this method should 
	 * return null
	 * @return a reference to the Player object that currently owns this continent
	 **/
	public Player getOccupant() 
	{
		
		return occupant; 	
	}
	
	/**
	 * @return the number of additional armies a player gets for owning this continent
	 **/
	public int getNumBonusArmies() 
	{ 
		
		return bonusArmies; 	
	}
	
	/**
	 * Sets which player owns a continent, if any
	 * @param occupant a reference to the Player object that now owns this continent
	 **/
	public void setOccupant( Player occupant ) 
	{
		
		this.occupant = occupant; 		
	}
	
	/**
	 * @return true of there is a player that owns all the territories on this continent
	 **/
	public boolean isOccupied() 
	{
		
		return ( occupant != null ); 
		
	}
	
	/**
	 * @return a Map of all the territories on the continent
	 **/
	public Map<String, Territory> getTerritories() 
	{
		return territories; 
	}

}
// end Continent class
