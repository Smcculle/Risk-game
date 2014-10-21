/**
 * CSCI 2120 Fall 2014
 * Risk Game Class DeckTest
 * @author Shane McCulley
 * @date October 21, 2014
 **/

package tests;

import java.util.ArrayList;

import classes.Card;
import classes.Deck;
import junit.framework.TestCase;

public class DeckTest extends TestCase
{
	private Deck test1;
	private ArrayList<Card> someCards = new ArrayList<Card>();
	private Card c1;
	private Card c2;
				
	
	protected void setUp() 
	{
		test1 = new Deck();
		Card c1 = new Card( "type1", "");					
		Card c2 = new Card( "", "territory2");
		someCards.add( c1 );
		someCards.add( c2 ); 
	}
	
	
	//Testing Deck's deal() method
	public void testDeal()
	{
		int numCards = test1.getSize();
		
		/* test1 should be full of cards */
		Card c = test1.deal();
		assertTrue( c != null );
		assertTrue( test1.getSize() == numCards - 1 );
		
		/* deal all cards */
		for( int i = 0; i < test1.getSize(); i++)
		{
			c = test1.deal();
			assertTrue( c != null );
		}
		
		assertTrue( test1.getSize() == 0 );
		assertTrue( test1.hasCards() == false );
		
		c = test1.deal(); //no cards left to deal 
		
		assertNull( c );
		
		
	}
	
	//Testing Deck's acceptCards(ArrayList<Card>) method
	public void testAcceptCards()
	{
		/*Set up an array list of cards to test with */
		
		assertTrue( test1.hasCards() == true );
		
		/* deal all cards */
		for( int i = 0; i < test1.getSize(); i++)
		{
			test1.deal();
		}
		assertTrue( test1.hasCards() == false );
		assertTrue( test1.getSize() == 0 );
		
		/* adding 2 cards, size should move to 2 */
		test1.acceptCards( someCards ); 
		assertTrue( test1.hasCards() == true );
		assertTrue( test1.getSize() == 2 );
		
		/*testing duplicates in deck.  We should expect to have 2 cards, not 4 (2 repeated) */
		test1.acceptCards( someCards );
		assertTrue( test1.getSize() == 2 );
		
		test1.deal(); 
		test1.deal(); 
		
		assertTrue( test1.hasCards() == false );
		assertTrue( test1.getSize() == 0 ); 
		
	}
	
	//Testing Deck's shuffle() method
	public void testShuffle()
	{
		
		/*Shuffle implemented using Collections.shuffle(), so there is not much to test 
		 * Since the hashCode will change on a successful shuffle, we test that */
		
		int preShuffle = test1.hashCode();
		test1.shuffle();
		assertTrue( preShuffle != test1.hashCode() );
		test1.shuffle();
		assertTrue( preShuffle != test1.hashCode() );
	
	}
	
	//Testing Deck's hasCards() method
	public void testHasCards()
	{	
		assertTrue( test1.hasCards() == true );
		
		/* deal all cards */
		for( int i = 0; i < test1.getSize(); i++)
		{
			test1.deal();
		}
		
		assertTrue( test1.hasCards() == false );
		
		/*after dealing from an empty deck, we should still be empty */
		test1.deal(); 
		assertTrue( test1.hasCards() == false );
		
		/* after adding a single card, it should evaluate  to true */
		ArrayList<Card> aCard = new ArrayList<Card>();
		aCard.add( new Card( "aT", "bT") );
		test1.acceptCards( aCard );
		assertTrue( test1.hasCards() == true );
		
		/* and empty again */
		test1.deal();
		assertTrue( test1.hasCards() == false );
		
	}
	
	//Testing getSize method
	public void testGetSize() 
	{
		int initSize = test1.getSize();
		assertTrue( initSize >= 0 );
		
		/* dealing single card reduces size by 1 */
		test1.deal(); 
		assertTrue( (initSize - 1) == test1.getSize() );
		
		initSize = test1.getSize(); 
		
		/* deal all cards and check size */
		for( int i = 1; i < test1.getSize() + 1; i++)
		{
			test1.deal();
			assertTrue( (initSize - i) == test1.getSize() );
		}
		
		assertTrue( test1.getSize() == 0 );
		
		/* since test1 is empty, guaranteed to add 2 with acceptCards */
		test1.acceptCards( someCards );
		assertTrue( test1.getSize() == 2 );
		test1.deal(); 
		assertTrue( test1.getSize() == 1 );
		
		
	}
}
