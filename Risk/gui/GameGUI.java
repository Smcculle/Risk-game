/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import javax.swing.JPanel;
import javax.swing.JFrame;

import engine.RiskGame;
import engine.RiskGameEngine;

import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;

@SuppressWarnings( "serial" )
public class GameGUI extends JFrame implements Observer
{

	private JPanel currentPanel;

	// START SCREEN
	private ActionListener startScreenHandler;
	private JPanel startScreen;

	// CREATE GAME SCREEN
	private ActionListener createGameScreenHandler;
	private JPanel createGameScreen;

	// CREATE PLAYERS SCREEN
	private ActionListener createPlayersScreenHandler;
	private JPanel createPlayersScreen;

	/**
	 * // SELECT TERRITORIES SCREEN private ActionListener
	 * selectTerritoriesScreenHandler; private JPanel selectTerritoriesScreen;
	 **/
	private RiskGameEngine gameEngine;
	private RiskGame game;

	public GameGUI( RiskGameEngine gameEngine )
	{	
		//this.setPreferredSize( new java.awt.Dimension( 1000, 800 ) );
		this.gameEngine = gameEngine;
		// START SCREEN INIT
		this.startScreenHandler = new StartScreenHandlerClass( this.gameEngine );
		this.startScreen = new StartScreenPanel( this.startScreenHandler );

		// CREATE GAME SCREEN INIT
		this.createGameScreenHandler = new CreateGameScreenHandler(
				this.gameEngine );
		this.createGameScreen = new CreateGameScreenPanel(
				this.createGameScreenHandler );

		// CREATE PLAYERS SCREEN INIT
		this.createPlayersScreenHandler = new CreatePlayersScreenHandler(
				this.gameEngine );
		this.createPlayersScreen = new CreatePlayersScreenPanel(
				this.createPlayersScreenHandler );
		/**
		 * // SELECT TERRITORIES SCREEN INIT this.selectTerritoriesScreenHandler
		 * = new SelectTerritoriesScreenHandlerClass( this.model );
		 * this.selectTerritoriesScreen = new SelectTerritoriesScreenPanel(
		 * this.selectTerritoriesScreenHandler );
		 **/
		this.currentPanel = this.startScreen;
		this.getContentPane().add( this.currentPanel );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	/**
	 * Method called by update to change panels. Removes currentPanel from
	 * contentPane, adds the argument to contentPane, and repaints.
	 * 
	 * @param newPanel the new panel to be added to contentPane
	 */
	private void changeScreen( JPanel newCurrentPanel )
	{
		this.getContentPane().remove( this.currentPanel );
		this.currentPanel = newCurrentPanel;
		this.getContentPane().add( this.currentPanel );
		//this.pack();
		this.revalidate();
		this.repaint();
	}

	public void update( Observable obs, Object obj )
	{
		System.out.println( "In update of GameGUI" );
		String state = gameEngine.getState();
		System.out.println( "New state of game engine: " + state );

		// Update the panel in the JFrame to reflect the current state
		if ( state.equals( "createNewGame" ) )
		{
			this.game = gameEngine.getGame();
			System.out.println( "in if of GameGUI update" );
			/*
			 * this.getContentPane().remove( this.currentPanel );
			 * this.currentPanel = this.createGameScreen;
			 * this.getContentPane().add( this.currentPanel );
			 * this.revalidate(); this.repaint();
			 */
			this.changeScreen( this.createGameScreen );
		}
		else if ( state.equals( "loadStartScreen" ) )
		{
			System.out.println( "Moving to startScreen in GUI" );
			this.changeScreen( this.startScreen );
		}
		else if ( state.equals( "loadSavedGame" ) )
		{
			System.out.println( "Loading saved game in GameGUI" );
			System.out.println( currentPanel.getName() );
			if ( currentPanel.getName().equals( "Start Screen" ) )
				( (StartScreenPanel)currentPanel ).chooseOpenFile();

		}
		else if ( state.equals( "createPlayers" ) )
		{
			System.out.println( "In createPlayers GameGUI " );
			this.changeScreen( createPlayersScreen );
		}

		/**
		 * else if( state.equals( "selectTerritories" ) ) {
		 * this.getContentPane().remove( this.currentPanel ); this.currentPanel
		 * = this.selectTerritoriesScreen; this.getContentPane().add(
		 * this.currentPanel );
		 * 
		 * }
		 **/
		else
		{
			System.out.println( "Shit whent wrong" );
		}
	}

}
