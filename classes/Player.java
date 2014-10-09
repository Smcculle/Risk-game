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
 * @interface PlayerInterface specifying players who are playing an active game
 * Player objects will be contained in Game objects
 **/
public class Player
{
	//TODO Instance variables 
	
	
	//TODO Constructors 
	public Player() { }
	
	public Player(String name) { }
	
	/**
	 * @return the Player's name
	 **/
	public String getName() { return null; }
	
	/**
	 * @return integer representing the number of armies the player has that can be placed
	 **/
	public int getUnplacedArmies() { return 0; }
	
	/**
	 * @return integer representing the total number of armies a player has on the board
	 **/
	public int getTotalArmies() { return 0; }
	
	/**
	 * @return an ArrayList of the territories the player controls
	 **/
	public ArrayList<Territory> getTerritoriesList() { return null; }
	
	/**
	 * @return a possibly empty ArrayList of continents the player controls
	 **/
	public ArrayList<Continent> getContinentsList() { return null; }
	
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
	void attack(Territory attacker, Territory defender, int numAttackingArmies ) { }

}
// end Player class