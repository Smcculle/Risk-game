/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import classes.RiskGame;
import engine.RiskGameEngine;
import engine.RiskGameEngine.State;
import engine.RiskUtils;

import java.util.Observer;
import java.util.Observable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

@SuppressWarnings( "serial" )
public class GameGUI extends JFrame implements Observer
{

	private RiskGame game;
	private RiskGameEngine gameEngine;
	
	private JPanel currentPanel;

	// START SCREEN
	private ActionListener startScreenHandler;
	private JPanel startScreen;

	// CREATE PLAYERS SCREEN
	private ActionListener createPlayersScreenHandler;
	private JPanel createPlayersScreen;
	
	// MAIN MAP SCREEN 
	private MapScreenHandler mapScreenHandler; 
	private JPanel mapScreen;

	/**
	 * // SELECT TERRITORIES SCREEN private ActionListener
	 * selectTerritoriesScreenHandler; private JPanel selectTerritoriesScreen;
	 **/
	

	public GameGUI( RiskGameEngine gameEngine )
	{	
		//this.setPreferredSize( new java.awt.Dimension( 1000, 800 ) );
		this.gameEngine = gameEngine;
		// START SCREEN INIT
		this.startScreenHandler = new StartScreenHandler( this.gameEngine );
		this.startScreen = new StartScreenPanel( this.startScreenHandler );

		// CREATE PLAYERS SCREEN INIT
		this.createPlayersScreenHandler = new CreatePlayersScreenHandler(
				this.gameEngine );
		this.createPlayersScreen = new CreatePlayersScreenPanel(
				this.createPlayersScreenHandler );
		
		//MAIN MAP SCREEN 
		mapScreenHandler = new MapScreenHandler( this.gameEngine );
		mapScreen = new MapScreenPanelTest( mapScreenHandler );
		
		/**
		 * // SELECT TERRITORIES SCREEN INIT this.selectTerritoriesScreenHandler
		 * = new SelectTerritoriesScreenHandlerClass( this.model );
		 * this.selectTerritoriesScreen = new SelectTerritoriesScreenPanel(
		 * this.selectTerritoriesScreenHandler );
		 **/
		this.currentPanel = this.startScreen;	
		this.getContentPane().add( currentPanel );
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
		State state = gameEngine.getState();
		System.out.println( "New state of game engine: " + state );

		/* Update the panel in the JFrame to reflect the current state
		if ( state.equals( "createNewGame" ) )
		{
			this.game = gameEngine.getGame();
			System.out.println( "in if of GameGUI update" );
			this.changeScreen( this.createGameScreen );
		}
		else*/ 
		if ( state == State.loadStartScreen ) 
		{
			System.out.println( "Moving to startScreen in GUI" );
			this.changeScreen( this.startScreen );
		}
		else if ( state == State.loadSavedGame ) 
		{
			System.out.println( "Loading saved game in GameGUI" );
			System.out.println( currentPanel.getName() );
			if ( currentPanel.getName().equals( "Start Screen" ) )
				( (StartScreenPanel)currentPanel ).chooseOpenFile();

		}
		else if ( state == State.createPlayers ) 
		{
			System.out.println( "In createPlayers GameGUI " );
			this.changeScreen( createPlayersScreen );
		}
		else if( state == State.assignTerritories )
		{
			this.game = gameEngine.getGame();
			System.out.println( "Assigning territories" );
			this.changeScreen( mapScreen );
			this.pack();
			this.setLocation( RiskUtils.getRelativeScreenLocation( 0.10, 0.0) );
			mapScreenHandler.initializeMap( );
			
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
