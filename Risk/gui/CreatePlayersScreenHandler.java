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

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import engine.RiskGameEngine;

public class CreatePlayersScreenHandler implements ActionListener
{

	private RiskGameEngine model;
	private CreatePlayersScreenPanel view; 
	private ColorFrame frame; 

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
		else if( command.equals( "chooseColor") )
		{
			/*JOptionPane.showMessageDialog( view, frame );*
			JOptionPane.showOptionDialog( 
					view, frame, "Choose a color", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, new Object[]{}, null );
			/*JOptionPane.showOptionDialog(null, "Hello","Empty?", 
			  JOptionPane.DEFAULT_OPTION,
			  JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
			 */
			/*
			JOptionPane opT = new JOptionPane( null, 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.DEFAULT_OPTION, null, frame.getComponents() );
			JDialog dialog = new JDialog();
			dialog.setTitle( "Choose a color" );
			dialog.setModal( true );
			dialog.setContentPane( opT );
			dialog.pack();
			dialog.setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
			dialog.setVisible( true );*/
			
			JOptionPane pane = new JOptionPane( frame, 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.DEFAULT_OPTION, null, new Object[]{});
		     //pane.set.Xxxx(...); // Configure
		     JDialog dialog = pane.createDialog(null, "Choose a color");
		     dialog.setVisible( true );
		     Object selectedValue = pane.getValue();
		     System.out.println( "Got selectedValue object: " + selectedValue );
		     //If there is an array of option buttons:
		     
		    
		}
		
		else if( command.equals( "Next" ) )
		{
			model.createPlayers( view.getNames() );
		}

	}
	
	public void setView( CreatePlayersScreenPanel view )
	{
		this.view = view; 
	}
	
	public void setFrame( ColorFrame frame )
	{
		this.frame = frame; 
	}

}
