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
 * Deck class specifying the deck of undistributed Risk game cards
 * Game objects will contain Deck objects
 **/
public class Deck
{

	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	Card deal() { return null; }
	
	/**
	 * Used to add cards turned-in by players back to the deck
	 * @param set an ArrayList of Cards turned-in by a player
	 **/
	void acceptCards( ArrayList<Card> set ) { }
	
	/**
	 * Shuffles the deck
	 **/
	void shuffle() { }
	
	// may also want
	
	/**
	 *
	 **/
	boolean hasCards() { return false; }

}
// end Deck class


