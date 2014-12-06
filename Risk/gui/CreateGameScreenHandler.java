/**
 * CSCI 2120 Fall 2014
 * Risk Game class CreateGameScreenHandler
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import engine.RiskGameEngine;

public class CreateGameScreenHandler implements ActionListener
{

    private RiskGameEngine model;

    public CreateGameScreenHandler( RiskGameEngine gameEngine )
    {
        this.model = gameEngine;
    }

    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand();
        System.out.print( "Command received: " + command + "...  ");
        
        if( command.equals( "createGame" ) )
        {
            System.out.println( "Creating players now" );
            //this.model.createPlayers();
        }
        
        else if ( command.equals( "Back" ) )
        {
        	System.out.println( "Moving back to start screen" );
        	this.model.loadStartScreen();
        }
        else
        {
            System.out.println( "Error in create game screen command" );
        }

    }


}
