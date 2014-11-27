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
	public Territory( String name, HashMap<String, Territory> neighbors) 
	{ 
		this.name = name;
		this.neighbors = neighbors; 
	}

	/**
	 * @return the name of the territory
	 **/
	public String getName() 
	{ 
		return name; 
	}
	
	/** TODO: Implement
	 * @return an ArrayList of all territories it shares a border with
	 **/
	public HashMap<String, Territory> getNeighbors() 
	{ 
		return neighbors; 
	}
	
	/**
	 * @return a reference to the player that currently owns this territory
	 **/
	public Player getOccupant() 
	{
		return occupant;	
	}
	
	/**
	 * @return the number of armies the occupying player has in the territory
	 **/
	public int getNumArmies() 
	{ 
		return armies; 
	} 
	
	/**
	 * Used to set the new occupying player of a territory
	 * @param occupant reference to the Player object who now occupies the territory
	 **/
	public void setOccupant( Player occupant ) 
	{ 
		this.occupant = occupant; 
	}
	
	/**
	 * Used by the occupying player to add armies to a territory
	 * @param numArmies the number of armies to be placed into the territory
	 **/
	public void setNumArmies( int numArmies ) 
	{ 
		this.armies = numArmies; 
	}
	
	public boolean isAdjacent( Territory t )
	{
		return neighbors.containsValue( t );
	}
	
	public boolean isAdjacent( String s )
	{
		return neighbors.containsKey( s );
	}


}// end Territory class