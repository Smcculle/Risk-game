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
 * GameBoard class specifying the board used in the game
 * Game objects will contain a GameBoard object
 **/
public class GameBoard
{

	/**
	 * Returns a list of all the territories on the board
	 * @return ArrayList of Territory references
	 **/
	public ArrayList<Territory> getTerritoriesList() { return null; }
	
	/**
	 * Returns a list of all the continents on the board
	 * @return ArrayList of Continent references
	 **/
	public ArrayList<Continent> getContinentsList() { return null; }
	
	/**
	 * Retrieves a reference to a territory by name
	 * @param the String containing the name of the territory to get
	 * @return a reference to the specified territory
	 **/
	public Territory getTerritoryByName( String territoryName ) { return null; }
	
	/**
	 * Retrieves a reference to a continent by name
	 * @param the String containing the name of the continent to get
	 * @return a reference to the specified continent
	 **/
	public Continent getContinentByName( String continentName ) { return null; }

} 
// end GameBoard class