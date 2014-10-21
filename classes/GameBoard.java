/**
 * CSCI 2120 Fall 2014
 * Risk Game Class GameBoard
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;
import java.util.HashMap;


/**
 * GameBoard class specifying the board used in the game
 * Game objects will contain a GameBoard object
 **/
public class GameBoard
{

	private HashMap<String, Territory> territories;
	private HashMap<String, Continent> continents;
	
	
	public GameBoard() {}
	/**
	 * Returns a list of all the territories on the board
	 * @return ArrayList of Territory references
	 **/
	public HashMap<String, Territory> getTerritoriesList() { return null; }
	
	/**
	 * Returns a list of all the continents on the board
	 * @return ArrayList of Continent references
	 **/
	public HashMap<String, Continent> getContinentsList() { return null; }
	
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