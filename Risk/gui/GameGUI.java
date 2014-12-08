/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
	private MapScreenPanelTest mapScreen;
	
	/* internal frames to the main map screen below */
	
	// CARD SCREEN
	private CardScreenHandler cardHandler;
	private CardScreenPanel cardScreen;
	
	// ATTACK SCREEN
	private ActionListener attackHandler;
	private JPanel attackScreen;
	
	//FORTIFY SCREEN 
	private MoveTroopsScreenHandler moveTroopsHandler;
	private MoveTroopsScreenPanel moveTroopsScreen;

	/**
	 * // SELECT TERRITORIES SCREEN private ActionListener
	 * selectTerritoriesScreenHandler; private JPanel selectTerritoriesScreen;
	 **/
	

	public GameGUI( RiskGameEngine gameEngine )
	{	
		//this.setPreferredSize( new java.awt.Dimension( 1000, 800 ) );
		this.gameEngine = gameEngine;
		// START SCREEN INIT
		this.startScreenHandler = new StartScreenHandler( gameEngine );
		this.startScreen = new StartScreenPanel( startScreenHandler );

		// CREATE PLAYERS SCREEN INIT
		this.createPlayersScreenHandler = new CreatePlayersScreenHandler(
				this.gameEngine );
		this.createPlayersScreen = new CreatePlayersScreenPanel(
				this.createPlayersScreenHandler );
		
		//MAIN MAP SCREEN 
		mapScreenHandler = new MapScreenHandler( gameEngine );
		mapScreen = new MapScreenPanelTest( mapScreenHandler );
		
		/* all panels below are internal frames to the main map screen */
		//CARD SCREEN 
		cardHandler = new CardScreenHandler( gameEngine );
		cardScreen = new CardScreenPanel( cardHandler );
		mapScreen.setCardScreenPanel( cardScreen );
		
		//ATTACK SCREEN 
		AttackScreenHandler attackScreenHandler = 
				new AttackScreenHandler( gameEngine );
		AttackScreenPanel attackScreen = 
				new AttackScreenPanel( attackScreenHandler);
		mapScreen.setAttackScreenPanel( attackScreen );
		
		//FORTIFY SCREEN 
		moveTroopsHandler = new MoveTroopsScreenHandler( gameEngine );
		moveTroopsScreen = new MoveTroopsScreenPanel( moveTroopsHandler );
		mapScreen.setMoveTroopsScreenPanel( moveTroopsScreen );
		
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
		System.out.print( "\nIn update of GameGUI->" );
		State state = gameEngine.getState();
		System.out.println( "New state of game engine: " + state ); 
		
		if( obj != null )
			System.out.printf(" with object " + obj + " of class " + obj.getClass() );
		/* Update the panel in the JFrame to reflect the current state
		if ( state.equals( "createNewGame" ) )
		{
			this.game = gameEngine.getGame();
			System.out.println( "in if of GameGUI update" );
			this.changeScreen( this.createGameScreen );
		}
		else*/ 
		/* attack phase of turn */
		if( state == State.attack )
		{
			System.out.println( "In notify observers attack state, object = " + obj );
			
			//TODO check it 
			if( obj instanceof String) 
			{
				if ( obj.equals( "captured") )
					mapScreenHandler.updateCapturedState();
				
				else if( obj.equals( "troopsMoved" )) 
					mapScreenHandler.updateMove();
			
				else if( obj.equals( "updateAttackBox") )
					mapScreenHandler.updateAttackBox();
				
				else
				{
					System.out.printf("Calling update map %n");
					mapScreenHandler.updateMap();
				}
					
			}
			else
			{
				System.out.println( "Object not string, call attack");
				mapScreenHandler.attack();
			}

		}
		
		/*begin fortify phase of turn */
		else if ( state == State.fortify )
		{
			mapScreenHandler.fortify();
		}
		
		else if ( state == State.loadStartScreen ) 
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
			mapScreenHandler.initializeMap();
					
		}
		else if( state == State.assignArmies )
		{
			mapScreenHandler.assignArmies();
		}
		else if( state == State.placeArmies )
		{
			mapScreenHandler.placeArmies(); 
		}
		else if( state == State.failedInit)
		{
			JOptionPane.showMessageDialog( null, "Game creation failed. "
					+ "Exiting." );
			System.exit ( state.value );
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
