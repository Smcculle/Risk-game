/**
 * CSCI 2120 Fall 2014
 * Risk Game class RiskGame
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package engine;

/**
 * RiskGame is responsible for the creation of the RiskGameEngine object, either
 * by creating a new game and initializing players or by loading a saved game.
 * RiskGame calls the appropriate newgame or loadgame methods of RiskGameEngine. 
 * 
 * Risk game also initializes the default card, territory values.  
 */
public class RiskGame
{

	private String name;

	public RiskGame( String name )
	{
		this.name = name;
	}

}
