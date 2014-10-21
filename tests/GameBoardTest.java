/**
 * CSCI 2120 Fall 2014
 * Risk Game Class GameBoardTest
 * @author Shane McCulley
 * @date October 21, 2014
 **/
 
package tests; 

import java.util.HashMap;

import junit.framework.TestCase;
import classes.Territory;
import classes.Continent;
import classes.GameBoard;
 
public class GameBoardTest extends TestCase { 
	
	private GameBoard testGameBoard;
	
	private Territory testTerritory;
	
	private Continent testContinent;
	private Continent testContinent2;
	private Continent testContinent3;
	
	 
	
	
	protected void setUp() {
		
		//new GameBoard() should initialize our arraylist of countries and territories
		testGameBoard = new GameBoard(); 
		testTerritory = new Territory();
		testContinent = new Continent();
		
		testContinent2 = new Continent( "Europe", 2 );
        testContinent3 = new Continent( "", 1 );
       // testContinentEmpty = new Continent();
		
	}
	
	
	//Testing GameBoard's getContinentByName(String): Continent method
    public void testGetContinentByName() {
    	
    	testContinent = testGameBoard.getContinentByName("Europe");
    	
    	// testContinent should be Europe
    	assertTrue( testContinent != null );
    	assertTrue( testContinent.getNumBonusArmies() > 0 );
    	assertTrue( testContinent.equals( testContinent2 ));
    	
    	assertTrue( testGameBoard.getContinentByName( "" ) == null);
    	assertTrue( testGameBoard.getContinentByName( "Europa" ) == null);
    	
    		
    	
    }
    
    //Testing GameBoard's getContinentsList(): HashMap<String, Continent> method
    public void testGetContinentsList() 
    {
    	HashMap<String, Continent> gcl = testGameBoard.getContinentsList();
    	
    	assertTrue( !gcl.isEmpty() );
    	
    	
    	assertTrue( gcl.size() == 6 );
    	
    	assertTrue( gcl.containsKey( "Asia" ) );
    	assertTrue( gcl.containsKey( "North America" ) );
    	assertTrue( gcl.containsKey( "Europe" ) );
    	assertTrue( gcl.containsKey( "Africa" ) );
    	assertTrue( gcl.containsKey( "Australia" ) );
    	assertTrue( gcl.containsKey( "South America" ) );
    	
    	
    }
    
	//Testing GameBoard's getTerritoriesList(): HashMap<String, Territory> method
    public void testGetTerritoriesList() 
    {
    		HashMap<String, Territory> gtl = testGameBoard.getTerritoriesList();
    		/* 42 total territories */
    		assertTrue( gtl.size() == 42 );
    		
    	
    }
    
  //Testing GameBoard's getTerritoryByName(String): Territory method
    public void testGetTerritoryByName() 
    {
    	HashMap<String, Territory> gtl = testGameBoard.getTerritoriesList();
    	for( String s : gtl.keySet() )
    	{
    		assertTrue( gtl.get( s ) == testGameBoard.getTerritoryByName( s ) ) ;
    	}
    	
    }
	
 
 
 
 }