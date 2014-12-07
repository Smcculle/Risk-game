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

import classes.Card;
import classes.Continent;
import classes.Deck;
import classes.GameBoard;
import classes.Player;
import classes.RiskGame;
import classes.Territory;
import engine.RiskGameEngine;
import engine.RiskGameEngine.State;

public class MapScreenHandler implements ActionListener
{

	private RiskGameEngine model; 
	private MapScreenPanelTest view; 
	
	
	public MapScreenHandler( RiskGameEngine model )
	{
		this.model = model; 
	}
	
	
	public void initializeMap( )
	{	
		System.out.println("*********************************");
		System.out.println( model.getGame().t);
		System.out.println("*********************************");
		view.initActionFromBox( model.getGame().getTerritoriesList() );
		view.setNextPlayer( model.getNextPlayer() );
	}
	
	@Override
	public void actionPerformed( ActionEvent event )
	{
		Component source = (Component)event.getSource();
		
		System.out.print( "Action event.  " 
				+ event.getSource().getClass());
		
		if( source instanceof JComboBox )
		{
			comboAction( (JComboBox)source, event );
		}
	}
	
	private void comboAction( JComboBox<String> comboBox, ActionEvent event)
	{
		System.out.printf(" with name: %s and value %s %n", 
				comboBox.getName(), comboBox.getSelectedItem());
		
		String newTerritory = ( (String)comboBox.getSelectedItem() );
		model.addTerritory( newTerritory );
		comboBox.removeItem( comboBox.getSelectedItem() );
		view.addCircle( newTerritory );
		view.setNextPlayer( model.getNextPlayer() );
	}
	
	public void addView( MapScreenPanelTest view )
	{
		this.view = view; 
	}

}
