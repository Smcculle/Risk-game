/**
 * CSCI 2120 Fall 2014
 * Risk class CardScreenHandler
 * @author Shane McCulley
 * @date Dec 3, 2014
 **/
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import classes.Card;
import engine.RiskGameEngine;

public class CardScreenHandler implements ActionListener, MouseListener
{
	private RiskGameEngine model;
	private CardScreenPanel view;
	private int[] selectedIndex; 
	
	public CardScreenHandler( RiskGameEngine model )
	{
		this.model = model;
	}

	public void addView( CardScreenPanel view )
	{
		this.view = view;
	}

	public void actionPerformed( ActionEvent event )
	{
		String command = event.getActionCommand();

		if ( command == "Accept" )
		{
			view.removeSet( selectedIndex );
		}

		if ( command == "Exit" )
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

	}

	private boolean isValidSet()
	{
		boolean result = false;
		selectedIndex = view.getSelectedIndex();

		System.out.print( "Index is: " );
		for ( int i : selectedIndex )
			System.out.print( i + " " );

		System.out.println();
		Card[] cards = view.getCards();

		String c0 = cards[0].getType();
		String c1 = cards[1].getType();
		String c2 = cards[2].getType();

		/* 3 of a kind */
		if ( c0.equals( c1 ) && c0.equals( c2 ) && c1.equals( c2 ) )
			result = true;
		else
			result = isDistinct( c0, c1, c2 );

		return result;
	}

	private boolean isDistinct( String a, String b, String c )
	{
		return ( !a.equals( b ) && !( a.equals( c ) || b.equals( c ) ) );
	}

	/**
	 * Used to select one of our cards (JPanel)
	 */
	@Override
	public void mousePressed( MouseEvent event )
	{
		System.out.println( "Mouseevent in CSH, source: " + event.getSource() );

		if ( event.getSource() instanceof CardComponent )
		{
			CardComponent card = (CardComponent)event.getSource();
			toggleSelectedCard( card );

		}

	}

	/**
	 * Handles selection of cards in CardScreenPanel. Ensures a maximum of 3
	 * cards can be selected. Calls isValidSet() if a 3rd card is selected to
	 * enable the accept button if a valid set is selected.
	 * 
	 * @param card a CardComponent that will be toggled.
	 */
	private void toggleSelectedCard( CardComponent card )
	{
		int numSelected = view.getNumSelected();
	
		/* if selected, set false and check accept button state */
		if( card.isSelected() )
		{
			card.setSelected( false );
			if( numSelected == 3 )
				view.setAccept( false );
		}
		
		/* set true if less than 2, validate button if 2 */
		else
		{
			if( numSelected < 3 )
				card.setSelected( true );
			if( numSelected == 2 && isValidSet() )
				view.setAccept( true );
		}
		
	}

	/* MouseListener methods that are not used */
	@Override
	public void mouseReleased( MouseEvent event ){}

	@Override
	public void mouseClicked( MouseEvent event ){}
	
	@Override
	public void mouseEntered( MouseEvent event ){}

	@Override
	public void mouseExited( MouseEvent event ){}
	

}
