/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Deck
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;
import java.util.ArrayList;


/**
 * Deck class specifying the deck of undistributed Risk game cards
 * Game objects will contain Deck objects
 **/
public class Deck
{
	
	private ArrayList<Card> cards;
	
	public Deck() {} 
	/**
	 * Issues one card to be given to a player
	 * @return Card from the front of the deck
	 **/
	public Card deal() { return null; }
	
	/**
	 * Used to add cards turned-in by players back to the deck
	 * @param set an ArrayList of Cards turned-in by a player
	 **/
	public void acceptCards( ArrayList<Card> set ) { }
	
	/**
	 * Shuffles the deck
	 **/
	public void shuffle() { }
	
	// may also want
	
	/**
	 *
	 **/
	public boolean hasCards() { return false; }
	
	public int getSize() {return 0;}

}
// end Deck class


