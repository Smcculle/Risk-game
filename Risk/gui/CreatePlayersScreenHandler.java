/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import engine.RiskGameEngine;

public class CreatePlayersScreenHandler implements ActionListener
{

	private RiskGameEngine model;
	private CreatePlayersScreenPanel view; 
	private Action textFieldAction;

	public CreatePlayersScreenHandler( RiskGameEngine model )
	{
		this.model = model;
		createTextFieldAction();
		
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
		    System.out.println( "Oops choosey " + source.getName() );
			/* sets the color for player number in getName() */
			view.setColor( Integer.parseInt( source.getName() ) );
		}
		
		else if( command.equals( "Next" ) )
		{
			model.createPlayers( view.getInformation(), view.getGameName() );
		}
		else
		{
			System.out.println("Unregistered command: " + command );
		}

	}
	
	public void setView( CreatePlayersScreenPanel view )
	{
		this.view = view; 
	}
	
	public Action getTextFieldAction()
	{
		return this.textFieldAction;
	}
	
	//TODO remove 
	@SuppressWarnings( "serial" )
	private void createTextFieldAction()
	{
		this.textFieldAction = new AbstractAction()
		{
	            public void actionPerformed( ActionEvent event ) 
	            {
	            	System.out.println( "Textfieldaction-> passing to");
	            	/*
	            	JTextField textField = (JTextField)event.getSource();
	            	
	            	System.out.println( "User pressed enter or tab" );
	            	System.out.println( "in addition, command is " + event.getActionCommand() );
	            	
	            	/* enable next button if all visible fields have data
	            	view.validateFields();*/
	            	this.actionPerformed( event );
	            	
	            }
	    };
	}
	
}
