/**
 * CSCI 2120 Fall 2014
 * Risk class AttackScreenHandler
 * @author Shane McCulley
 * @date Dec 4, 2014
 **/
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import classes.Dice;
import engine.RiskGameEngine;

public class AttackScreenHandler implements ActionListener
{
	
	private RiskGameEngine model;
	private AttackScreenPanel view; 
	
	public AttackScreenHandler( RiskGameEngine model )
	{
		this.model = model;
	}

	public void addView( AttackScreenPanel view )
	{
		this.view = view;
	}

	//TODO remove 
	public int[] testResults( int numAttacking, int numDefending )
	{
		Dice dice = new Dice();
		int[] results = dice.roll( numAttacking, numDefending );
		System.out.print( "Results: ");
		for( int i : results )
			System.out.print( i + " ");
		System.out.println();
		return results; 
	}
	
	public void actionPerformed( ActionEvent event )
	{
		String command = event.getActionCommand();
		
		if ( command.equals("Attack") )
		{
			System.out.println( "Rolling the dice. "
					+ " Attackers: " + view.getNumAttacking() 
					+ " Defenders: " + view.getNumDefending() );
			//TODO send results to panel, have panel update itself
			view.updateResults( testResults( 
					view.getNumAttacking(), view.getNumDefending() ) );
			model.notifyObservers( "repaint" );
		}
		
		/* notify GUI to update the list of player's territories */
		else if( command.equals("captured") ) 
		{
			model.notifyObservers( command );
		}
		
		else if ( command.equals("Quit") )
		{
			/* closes the internal frame that contains this screen */
			JInternalFrame jif =
					( (JInternalFrame)SwingUtilities.getAncestorOfClass(
							JInternalFrame.class, (Component)event.getSource() ) );

			if ( jif != null )
				jif.dispose();

			else
				SwingUtilities.getWindowAncestor(
						(Component)event.getSource() ).dispose();
		}
		
		/* else it is a toggle button */
		else
		{
			if( ( (Component)event.getSource() ).getName().equals( "attack" ))
			{
				view.setAttackDiceGroup( 
						Integer.parseInt( event.getActionCommand() ));
			}
			else
			{
				view.setDefendDiceGroup(
						Integer.parseInt(  event.getActionCommand() ));
			}
		}

	}

}
