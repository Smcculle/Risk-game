/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package classes;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import engine.RiskUtils;

/**
 * RiskGame holds the core classes of Risk and enforces gameplay rules.    
 */
public class RiskGame
{
	/* Risk default provide 35 armies for 3 players to 20 armies for 6*/
	private final int MAX_ARMIES = 50; 
	private final int LESS_EACH_PLAYER = 5;
	
	/* instance variables */
	private ConcreteQueue<Player> players; 
	private Player currentPlayer; 
	private GameBoard gameBoard; 
	private Deck deck; 
	private Dice dice; 
	private RiskGameLoader gameLoader; 
	private String name; 
	public int t = 0;
	
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
		t++;
	}

	public boolean createPlayers( Map<String, Color> playerInfo, 
								  String gameName )
	{
		players = new ConcreteQueue<Player>();
		this.name = gameName;
		System.out.println( "Creating game.  Game name = " + name 
				+ ", " + playerInfo.size() + " players. ");
	
		int startingArmies = calcStartingArmies( playerInfo.size() );
		/* random iteration through map shuffles player order */
		for( Map.Entry<String, Color> entry : playerInfo.entrySet() )
		{
			Player newPlayer = new Player( entry.getKey(), entry.getValue() );
			newPlayer.addArmies( startingArmies );
			players.add( newPlayer );
		}
		t++;
		return (!players.isEmpty()); 
	}
	
	/**
	 * Calculates the number of armies each player starts with.  Calculation
	 * based on number of players.  
	 * 
	 * @return int number of armies each player starts with. 
	 */
	private int calcStartingArmies( int numPlayers )
	{
		return MAX_ARMIES - ( LESS_EACH_PLAYER * numPlayers ); 
	}
	
	/**
	 * Sets a new player as the current player.  Inserts currentPlayer into 
	 * the queue and dequeues the next player.  
	 */
	public Player getNextPlayer()
	{
		if( currentPlayer != null )
			players.add( currentPlayer );
			
		currentPlayer = players.poll();
		System.out.printf("Current player is now: %s %n", currentPlayer );
		
		return currentPlayer;
	}
	
	public void addTerritory( String territory )
	{
		Territory newTerritory = gameBoard.getTerritoryByName( territory );
		currentPlayer.addTerritory( newTerritory );
		newTerritory.setOccupant( currentPlayer );
		
		System.out.printf("Player %s adding territory %s. Armies from %d to "
				, currentPlayer.getName(), territory, currentPlayer.getUnplacedArmies());
		currentPlayer.addArmies( -1 );
		newTerritory.setNumArmies( 1 );
		System.out.println( currentPlayer.getUnplacedArmies() );
		System.out.printf("Now %s owns: ", currentPlayer);
		RiskUtils.printM( currentPlayer.getTerritoriesList() );
		
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
	
	public Deck getDeck()
	{
		return deck; 
	}
	
	public ConcreteQueue<Player> getPlayers()
	{
		return players;
	}
	
	public GameBoard getGameBoard()
	{
		return gameBoard;
	}
	
	public Map<String, Territory> getTerritoriesList()
	{
		return gameBoard.getTerritoriesList();
	}
	
	public Map<String, Continent> getContinentsList()
	{
		return gameBoard.getContinentsList();
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	private void takeTurn()
	{
	}
	
}
