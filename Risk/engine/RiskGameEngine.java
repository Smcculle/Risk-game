/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package engine;

import java.util.Observable;

/**
 * RiskGameEngine is responsible for the creation of the RiskGame object, either
 * by creating a new game and initializing players or by loading a saved game.
 */
public class RiskGameEngine extends Observable
{
	private RiskGame game;
	private String state;

	public RiskGameEngine()
	{
		this.state = "startScreen";
		this.game = new RiskGame( "Dummy game" );
	}

	public void createNewGame()
	{
		System.out.println( "In createNewGame of RiskGameEngine" );
		this.state = "createNewGame";
		this.setChanged();
		this.notifyObservers();
	}

	public void makeGame( String gameName )
	{
		this.game = new RiskGame( gameName );
	}

	public void loadSavedGame()
	{
		this.state = "loadSavedGame";
		this.setChanged();
		this.notifyObservers();
	}
	
	public void loadStartScreen()
	{
		this.state = "loadStartScreen";
		this.setChanged();
		this.notifyObservers();
	}
	
	public void createPlayers()
	{
		this.state = "createPlayers";
		this.setChanged();
		this.notifyObservers();
		
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

	public String getState()
	{
		return this.state;
	}

}