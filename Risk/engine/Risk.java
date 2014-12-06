/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import gui.GameGUI;

import javax.swing.JFrame;

public class Risk
{
	public final static Dimension GAME_SIZE = new Dimension( 512, 347 );

	public static void main( String[] args )
	{
		RiskGameEngine gameEngine = new RiskGameEngine();
		GameGUI gui = new GameGUI( gameEngine );

		gameEngine.addObserver( gui );

		gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		gui.setSize( GAME_SIZE );
		gui.setResizable( false );
		gui.setLocation( RiskUtils.getStartScreenPosition() );
		
		// gui.pack();
		gui.setVisible( true );
	}

}
