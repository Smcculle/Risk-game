/**
 * CSCI 2120 Fall 2014
 * Risk class MapScreenPanelHandler
 * @author Shane McCulley
 * @date Dec 6, 2014
 **/
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractQueue;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import classes.Card;
import classes.Continent;
import classes.Deck;
import classes.GameBoard;
import classes.Player;
import classes.RiskGame;
import classes.Territory;
import engine.RiskGameEngine;
import engine.RiskUtils;
import engine.RiskGameEngine.State;


public class MapScreenHandler implements ActionListener
{

	private RiskGameEngine model; 
	private MapScreenPanelTest view; 
	
	
	public MapScreenHandler( RiskGameEngine model )
	{
		this.model = model; 
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public void actionPerformed( ActionEvent event )
	{
		Component source = (Component)event.getSource();
		
		System.out.print( "Action event.  " 
				+ event.getSource().getClass());
		
		if( source instanceof JComboBox )
		{
			if( source.getName().equals( "actionFromBox" ))
				handleActionFrom( (JComboBox<String>)source, event );
			else if ( source.getName().equals( "actionToBox"))
				handleActionTo( (JComboBox<String>)source, event );
		}
		else if( event.getActionCommand().equals( "endTurn" ))
		{
			System.out.printf( "Ending turn ");
		}
	}
	
	/**
	 * Handles the logic dealing with comboBox events coming from actionFromBox.  
	 * These actions involve player selecting his own territory to either
	 * reinforce troops or select for attack.  
  	 * 
	 * @param comboBox the comboBox the event originated from  
	 * @param event the event generated from selection
	 */
	private void handleActionFrom( JComboBox<String> comboBox, ActionEvent event)
	{
		System.out.printf(" name: %s; value %s; %d items left %n", 
				comboBox.getName(), comboBox.getSelectedItem(), comboBox.getItemCount() );
		
		String territory = ( (String)comboBox.getSelectedItem() );
		
		/* assign a territory, get next player */
		if( model.getState() == State.assignTerritories)
		{	
			model.addTerritory( territory );
			comboBox.removeItem( comboBox.getSelectedItem() );
			view.addCircle( territory );
			
			/* continue while there are territories to assign */
			if( comboBox.getItemCount() > 0 )
				view.setNextPlayer( model.getNextPlayer() );
			
			/* all territories have been taken, advance to second stage */
			else 
				model.assignArmies();
		}
		
		/* assign 1 army to 1 territory, get next player */
		else if( model.getState() == State.assignArmies )
		{
			/* increase troop in territory by 1, decrease currentPlayer by 1*/
			model.incrementTroops( territory );
			view.repaint();
			
			Player nextPlayer = model.getNextPlayer();
			
			if( nextPlayer.getUnplacedArmies() > 0 )
				view.assignArmies( nextPlayer );
			else
			{
				model.placeArmies();
			}
		}
		/* place all armies remaining and then begin turn */ 
		else if( model.getState() == State.placeArmies )
		{
			model.incrementTroops( territory );
			view.setLabelPlaceArmies( model.getCurrentPlayer() );
			
			/* all armies placed, change to attack phase  */
			if( model.getCurrentPlayer().getUnplacedArmies() == 0 )
				model.attack();
		}
		
		/* selecting territory to attack from, load adjacencies */
		else if( model.getState() == State.attack )
		{
			view.initActionToBox( territory );
		}
		
	} 
	
	/**
	 * This box should only fire an event when the player is attacking 
	 * another player or fortifying one of his provinces.   
	 * 
	 * @param comboBox the comboBox the event originated from  
	 * @param event the event generated from selection
	 */
	private void handleActionTo( JComboBox<String> comboBox, ActionEvent event)
	{
		/* open attack screen between the 2 territories */
		if( model.getState() == State.attack )
		{
			view.attack();
		}
	}
	/**
	 * Begins the game by initializing map to allow players to select 
	 * territories.  
	 */
	public void initializeMap()
	{	
		view.setMap( model.getGame().getTerritoriesList() );
		view.setNextPlayer( model.getNextPlayer() );
	}
	
	/**
	 * Continues with the assigning armies portion of initialization.  
	 */
	public void assignArmies()
	{
		view.assignArmies( model.getNextPlayer() );
	}
	
	/**
	 * Begins a player turn by placing armies 
	 */
	public void placeArmies()
	{
		if( model.getCardStatus() == true )
			view.showCards( model.getGame().getCurrentPlayer(), true );
		
		view.placeArmies( model.getCurrentPlayer() );
	}
	
	/**
	 * Update map data with new troop values, close troop panel, update
	 * comboBox with new territory if necessary.  
	 */
	public void updateMove()
	{
		Player currentPlayer = model.getCurrentPlayer();
		view.updateMove( currentPlayer );
	}
	
	/**
	 * Calls revalidate and repaint on the map.  
	 */
	public void updateMap()
	{
		view.updateMap();
	}
	/**
	 * Updates actionFromBox with a list of territories player can attack from. 
	 * Must have at least 2 armies to attack from a territory.  
	 */
	public void attack()
	{
		Player currentPlayer = model.getCurrentPlayer();
		view.showAttackBox( currentPlayer );
	}
	
	/**
	 * Add new territory to player's comboBox, show move troops screen. 
	 */
	public void updateCapturedState()
	{
		Player currentPlayer = model.getCurrentPlayer();
		view.showMoveTroopsScreen();
		view.updateAttackBox( currentPlayer );
	}
	
	/**
	 * Only updates possible attack locations from currentPlayer
	 */
	public void updateAttackBox()
	{
		Player currentPlayer = model.getCurrentPlayer();
		view.updateAttackBox( currentPlayer );
	}
	
	/**
	 * Adds the mapScreenPanel so that we can call its methods easily.  
	 *  
	 * @param view mapScreenPanel to add to the member variables.  
	 */
	public void addView( MapScreenPanelTest view )
	{
		this.view = view; 
	}

	

}
