/**
 * CSCI 2120 Fall 2014
 * Risk Game Class Hand
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package classes;

import java.util.ArrayList;

/**
 * @interface HandInterface specifying a hand of cards held by a specific player
 * Player objects will contain Hand objects
 **/
public class Hand
{
	private ArrayList<Card> cards;
	
	public Hand () 
	{
		cards = new ArrayList<Card>();
	}

	/**
	 * Used to receive a Card into a Player's hand
	 * @param newCard the new Card being given to the player's Hand
	 **/
	public void acceptCard( Card newCard ) 
	{ 
		cards.add( newCard );
	}
	
	/**
	 * Used to get a list of the cards currently in the player's hand
	 * @return an ArrayList of the Cards in the player's hand
	 **/
	public ArrayList<Card> getCards() 
	{ 
		return this.cards;	
	}
	
	/**
	 * Used to remove a set of cards from the player's hand
	 * @param set an array of integers indicating the indices of the cards to turn-in
	 * @return an ArrayList of the Cards being turned-in
	 **/
	public ArrayList<Card> turnInSet( int[] set ) 
	{ 
		ArrayList<Card> result = new ArrayList<Card>( set.length );
		
		/* add cards toReturn, then remove from hand */
		for( int index: set )
		{
			/* array bounds checking */
			if( index < this.cards.size() )
				result.add( this.cards.get( index ) );
		}
		this.cards.removeAll( result );
		
		return result; 
	}

}
// end Hand class