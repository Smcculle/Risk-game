/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import engine.RiskGameEngine;

public class CreatePlayersScreenHandler implements ActionListener
{

	private RiskGameEngine model;
	private CreatePlayersScreenPanel view; 

	public CreatePlayersScreenHandler( RiskGameEngine model )
	{
		this.model = model;
	}

	@Override
	public void actionPerformed( ActionEvent event )
	{
		String command = event.getActionCommand();
	
		System.out.println( "Action performed in CPSH, command = "
				+ event.getActionCommand() );
		
		if( command.equals( "Back") )
		{
			System.out.println( "Going back!" );
			model.loadStartScreen();
		}
		else if( command.equals( "colorChosen") )
		{
			JButton button = (JButton)event.getSource();
			
			/* pass value to panel to change appropriate fields */
			( (JOptionPane)SwingUtilities.getAncestorOfClass( 
					JOptionPane.class, button) ).setValue( button );
		}
		else if( command.equals( "chooseColor") )
		{
			JButton source = (JButton)event.getSource();
		   
			/* sets the color for player number in getName() */
			view.setColor( Integer.parseInt( source.getName() ) );
		}
		
		else if( command.equals( "Next" ) )
		{
			model.createPlayers( view.getNames() );
		}
		else
		{
			//TODO: Remove
			throw new RuntimeException();
		}

	}
	
	public void setView( CreatePlayersScreenPanel view )
	{
		this.view = view; 
	}
	
}
