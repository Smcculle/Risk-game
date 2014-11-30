/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import engine.RiskGameEngine;

public class StartScreenHandlerClass implements ActionListener
{
    private RiskGameEngine model;

    public StartScreenHandlerClass( RiskGameEngine gameEngine )
    {
        this.model = gameEngine;
    }

    public void actionPerformed( ActionEvent event )
    {
        System.out.print( "In action performed for start screen, " );
        String command = event.getActionCommand();
        System.out.print( "command received: " + command + ". ");
        
        if( command.equals( "createNewGame" ) )
        {
            System.out.println( "Now if createNewGame" );
            this.model.createNewGame();
        }
        else if( command.equals( "loadSavedGame" ) )
        {
        	System.out.println( "Now if loadSavedGame");
            this.model.loadSavedGame();
        }
        else
        {
            System.out.println( "Invalid command in Start Screen" );
        }

    }
    
}
