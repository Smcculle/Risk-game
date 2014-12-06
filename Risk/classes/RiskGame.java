/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package classes;

import java.util.PriorityQueue;

/**
 * RiskGame holds the core classes of Risk and enforces gameplay rules.    
 */
public class RiskGame
{
	private PriorityQueue<Player> players; 
	private Player currentPlayer; 
	private GameBoard gameBoard; 
	private Deck deck; 
	private Dice dice; 
	private RiskGameLoader gameLoader; 
	
	

	public RiskGame ()
	{
		dice = new Dice();
	
	}
	
	/**
	 * Initializes a new game state with board and deck prepared, awaiting
	 * for # of players and names to be selected.  
	 * 
	 **/
	public void createNewGame()
	{
		if( gameBoard == null )
		{
			gameLoader = new RiskGameLoader();
			gameBoard = gameLoader.getGameBoard();
			deck = gameLoader.getDeck();
		}
		
		/* game loader is no longer needed */
		gameLoader = null; 
		System.out.println( "Game created" );
	}

	/**
	 * Loads a saved game from serialized objects stored in a file
	 * 
	 * @param gameName the name of the file containing the saved game
	 **/
	public void loadSavedGame( String gameName )
	{
	}

	/**
	 * Starts the game loop that will begin actual game play
	 **/
	public void playGame()
	{
	}

	private void takeTurn()
	{
	}
	
}
