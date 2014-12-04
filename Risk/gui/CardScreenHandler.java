/**
 * CSCI 2120 Fall 2014
 * Risk class CardScreenHandler
 * @author Shane McCulley
 * @date Dec 3, 2014
 **/
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import engine.RiskGameEngine;

public class CardScreenHandler implements ActionListener, MouseListener
{
	private RiskGameEngine model; 
	private CardScreenPanel view;
	
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
		
		if( command == "Back" )
		{
			System.out.println( "Going back!" );
			if( model != null ) model.createNewGame();
		}
		if( command == "Next" )
		{
			System.out.println( SwingUtilities.getWindowAncestor( (java.awt.Component)event.getSource() ).getSize() );
		}

	}

	/**
	 * Used to select one of our cards (JPanel) 
	 */
	@Override
	public void mouseReleased( MouseEvent event ) 
	{
		System.out.println( "Mouseevent in CSH, source: " + event.getSource() );
		Object obj = event.getSource();
		
		if( event.getSource() instanceof CardComponent )
		{
			( (CardComponent)obj ).toggleSelected();
		}
			
	}
	
	/* MouseListener methods that are not used */
	@Override
	public void mouseClicked( MouseEvent event ){}
	
	@Override
	public void mouseEntered( MouseEvent event ) {}
		
	@Override
	public void mouseExited( MouseEvent event ){}
	
	@Override
	public void mousePressed( MouseEvent event ){}
}
