/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import engine.Risk;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("serial")
public class StartScreenPanel extends JPanel
{


	private JLabel createNewGameLabel;
	private JButton createNewGameButton;

	private JLabel loadSavedGameLabel;
	private JButton loadSavedGameButton;

	private JFileChooser fileChooser;

	private ActionListener screenHandler;

	public StartScreenPanel( ActionListener handler )
	{
		this.screenHandler = handler;
		this.setLayout( new GridLayout( 4, 1 ) );
		this.setName( "Start Screen" );

		/* new game label and button */
		createNewGameLabel = new JLabel( "Creates a new Risk Game: " );
		this.add( createNewGameLabel );

		createNewGameButton = new JButton( "New Game" );
		createNewGameButton.setActionCommand( "createNewGame" );
		createNewGameButton.addActionListener( this.screenHandler );
		this.add( createNewGameButton );

		/* load game label and button */
		loadSavedGameLabel = new JLabel( "Loads a Risk Game saved to file: " );
		this.add( loadSavedGameLabel );

		loadSavedGameButton = new JButton( "Load Game" );
		loadSavedGameButton.setActionCommand( "loadSavedGame" );
		loadSavedGameButton.addActionListener( this.screenHandler );
		this.add( loadSavedGameButton );
		this.setPreferredSize( Risk.GAME_SIZE );

	}

	protected void chooseOpenFile()
	{

		this.fileChooser = new JFileChooser( new File(
				System.getProperty( "user.dir" ) ) );

		int status = fileChooser.showOpenDialog( this );

		if ( status == JFileChooser.APPROVE_OPTION )
			System.out.println( "A file was chosen" );
	
		else
			System.out.println( "Cancelled by user" );
		

	}

}
