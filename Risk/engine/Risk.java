/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package engine;

import gui.GameGUI;
import javax.swing.JFrame;

public class Risk
{

	public static void main( String[] args )
	{
		RiskGameEngine gameEngine = new RiskGameEngine();
		GameGUI gui = new GameGUI( gameEngine );

		gameEngine.addObserver( gui );

		gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		gui.setSize( RiskUtils.GAME_SIZE );
		gui.setResizable( false );
		gui.setLocation( RiskUtils.getStartScreenPosition() );
		gui.setVisible( true );
	}

}
