/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package engine;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import classes.Player;
import classes.RiskGame;

/**
 * RiskGameEngine is responsible for the creation of the RiskGame object, either
 * by creating a new game and initializing players or by loading a saved game.
 */
public class RiskGameEngine extends Observable
{
	private RiskGame game;
	private State state;
	
	public enum State{ 
		loadStartScreen(1), loadSavedGame(2), createPlayers(3), 
		assignTerritories(4), placeArmies(5), turnInCards(6), attack(7),
		fortify(8);
		
		public final int value; 
		
		State( int value ) 
		{
			this.value = value; 
		}
	}

	public RiskGameEngine()
	{
		this.state = State.loadStartScreen;
		game = new RiskGame();
	}
	
	public void loadSavedGame()
	{
		this.state = State.loadSavedGame;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void loadStartScreen()
	{
		this.state = State.loadStartScreen;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void createNewGame()
	{
		System.out.println( "In createNewGame of RiskGameEngine" );
		game.createNewGame();
		this.state = State.createPlayers;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Creates the player objects received from GUI and advances to 
	 * assign territories state.  
	 * @param names
	 */
	public void createPlayers( Map<String, Color> playerInfo, String gameName )
	{
		if ( game.createPlayers( playerInfo, gameName  ) ) 
		{
			this.state = State.assignTerritories;
			this.setChanged();
			this.notifyObservers();
		}
		else
		{
			System.err.println( "Game creation failed" );
			//System.exit( 2 );
		}
		
	}
	
	public void loadGame( String gameFileName )
	{
		// Read & De-Serialize a game object
		// and set this.game to the de-serialized object
	}

	public RiskGame getGame()
	{
		return this.game;
	}

	public State getState()
	{
		return this.state;
	}

	/**
	 * Call RiskGame method to set up the next player.  
	 */
	public Player getNextPlayer()
	{
		return game.getNextPlayer();
	}

	/** 
	 * Calls RiskGame method to add territory to the current player.  
	 * @param territory String name of territory to add. 
	 */
	public void addTerritory( String territory )
	{
		if( game.getCurrentPlayer() != null )
			game.addTerritory( territory );
		
	}

}
