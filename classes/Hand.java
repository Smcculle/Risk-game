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
 * @interface HandInterface specifying a hand of cards held by a specific player
 * Player objects will contain Hand objects
 **/
public class Hand
{

	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	void acceptCard( Card newCard ) { }
	
	/**
	 * Used to get a list of the cards currently in the player's hand
	 * @return an ArrayList of the Cards in the player's hand
	 **/
	ArrayList<Card> getCards() { return null; }
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	ArrayList<Card> turnInSet( int[] set ) { return null; }

}
// end Hand class