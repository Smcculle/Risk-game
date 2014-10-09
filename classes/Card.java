/**
 * Risk Game Interfaces
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date September 15, 2014
 * @version 0.1
 **/


package interfaces;


/**
 * @interface CardInterface specifying the Risk game cards
 **/
public interface CardInterface
{

	/**
	 * @return the type (infantry, cavalry, artillery) of the card as a String
	 **/
	String getType();
	
	/**
	 * @return a reference to the territory this card is matched to
	 **/
	Territory getTerritory();

}
// end Card interface

