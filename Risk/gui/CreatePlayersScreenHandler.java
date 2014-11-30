/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.RiskGameEngine;

public class CreatePlayersScreenHandler implements ActionListener
{

	private RiskGameEngine model;

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
		
		if( command == "Back" )
		{
			System.out.println( "Going back!" );
			if( model != null ) model.createNewGame();
		}

	}

}
