/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CreateGameScreenPanel extends JPanel
{

	private JButton createGameButton;
	private JButton backButton; 
    private ActionListener handler;

    public CreateGameScreenPanel( ActionListener handler )
    {
        this.handler = handler;
        this.setBackground( Color.BLACK );
        
        this.createGameButton = new JButton( "Create Game" );
        createGameButton.setActionCommand( "createGame" );
        this.createGameButton.addActionListener( handler );
        this.add( this.createGameButton );
        
        this.backButton = new JButton("Back");
        this.backButton.addActionListener( handler );
        this.add ( this.backButton );
              
    }
   
}
