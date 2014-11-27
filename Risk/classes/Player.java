/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Player
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;
import java.util.HashMap;

/**
 * @interface PlayerInterface specifying players who are playing an active game
 * Player objects will be contained in Game objects
 **/
public class Player
{
	// Instance variables 
	private String name;
	private int numArmies; 
	private HashMap<String, Territory> territories;
	private HashMap<String, Continent> continents;
	private Hand cardHand;
	
	
	//TODO Constructors 
	public Player() { }
	
	public Player(String name) 
	{ 
		this.name = name; 
	}
	
	/**
	 * @return the Player's name
	 **/
	public String getName() 
	{
		return name; 
	}
	
	/**
	 * @return integer representing the number of armies the player has that can be placed
	 **/
	public int getUnplacedArmies() 
	{ 
		return numArmies; 
	}
	
	/**
	 * @return integer representing the total number of armies a player has on the board
	 **/
	public int getTotalArmies() { return 0; }
	
	/**
	 * @return an ArrayList of the territories the player controls
	 **/
	public HashMap<String, Territory> getTerritoriesList() { return null; }
	
	/**
	 * @return a possibly empty ArrayList of continents the player controls
	 **/
	public HashMap<String, Continent> getContinentsList() { return null; }
	
	/**
	 * Used to add territories to the player's list of controlled territories
	 * @param newTerritory a reference to the Territory object the player now controls
	 **/
	public void addTerritory( Territory newTerritory ) { }
	
	/**
	 * Used to add a continent to the player's continent list, once all territories on
	 * that continent are controlled by the player
	 * @param newContinent a reference to the Continent object the player now controls
	 **/
	public void addContinent( Continent newContinent ) { }
	
	/**
	 * Used to increase the number of armies the player has that need to be placed
	 * @param newArmies integer representing the number of armies the player received
	 *  at the beginning of the turn
	 **/
	public void addArmies( int newArmies ) { }
	
	/**
	 * Used to give a player a new Card
	 * @param newCard a reference to a Card object the player is being given
	 **/
	public void addCard( Card newCard ) { }
	
	/**
	 * Used by the player to initiate an attack on a territory
	 * @param attacker a reference to a Territory object the player is attacking from
	 * @param defender a reference to a Territory object that is being attacked
	 * @param numAttackingArmies an integer representing the number of armies used in
	 *  the attack
	 **/
	public void attack(Territory attacker, Territory defender, int numAttackingArmies ) { }
	
	/**
	 * Removes a territory from the player when he loses one.  
	 * @param oldTerritory The territory lost 
	 */
	public void removeTerritory( Territory oldTerritory ) {}

}
// end Player class