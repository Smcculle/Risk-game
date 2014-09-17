/**
 * Risk Game Test Harness
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date September 17, 2014
 * @version 0.1
 **/

import junit.framework.TestCase;

public class ContinentTest extends TestCase {
    
    Continent testContinent; 
    Continent testContinent2; 
    Continent testContinentEmpty;
    
    //set this continent as occupied as there is not a constructor
    //for continent to set occupant at instantiation.  
    Continent testOccupied;
    
    //Player objects for testing setOccupant, getOccupant
    Player testPlayer; 
    Player testPlayerEmpty;
    Player testOccupiedPlayer;
    
    protected void setUp() {
        
        //Possible constructor takes string name, int bonusArmies    
        testContinent = new Continent( "Europe", 2 );
        testContinent2 = new Continent( "", 1 )
        testContinentEmpty = new Continent();
        
        testOccupied = new Continent();
        testOccupiedPlayer = new Player( "testOccupied Player"); 
        testOccupied.setOccupant( testOccupiedPlayer);
        
        //Initializing player object with name "Test player"
        testPlayer = new Player( "Test player" ); 
        //empty player object
        testPlayerEmpty = new Player();     
        
    }
    
    // Testing Continent's String getName() method
    public void testGetName() { 
        String continentName = testContinent.getName();
        String continentName2 = testContinent2.getName();
        String empty = testContinentEmpty.getName();
        
        assertTrue( continentName.equals("Europe") );
        assertTrue( continentName2.equals("") );
        assertTrue( empty.getName() = null );
        
    }
    
    //Testing Continent's int getNumBonusArmies() method
    public void testGetNumBonusArmies() {
        
        assertTrue( testContinent.getNumBonusArmies() == 2);
        assertTrue( testContinent2.getNumBonusArmies() == 1);
        assertTrue( testContinentEmpty.getNumBonusArmies() == null);
        
    }
    
    //Testing Continent's boolean isOccupied() method
    public void testIsOccupied() {
        assertTrue(testContinent.isOccupied() == false);
        
        
    }
    
    //Testing Continent's Player getOccupant() method
    public void testGetOccupant() {
        
        //getOccupant() should return null as there is no occupant
        assertTrue( testPlayerEmpty != null );
        testPlayerEmpty = testContinentEmpty.getOccupant(); 
        assertTrue( testPlayer == null ); 
        
        //testPlayerEmpty is set to null from the lines above 
        testContinent2.setOccupant( testPlayerEmpty );
        assertTrue( testContinent2.getOccupant() == null );
        
        //getOccupant() should return testOccupied Player
        assertTrue( testContinent.getOccupant().equals( testOccupiedPlayer ));
        

    }
    
    //Testing Continent's void setOccupant(Player occupant) method
    public void testSetOccupant() {
        
        //testContinent has no player set, should be null 
        assertTrue( testContinent.getOccupant() == null);
        
        //After setting player, it should no longer be null
        testContinent.setOccupant( testPlayer );
        assertTrue( testContinent.getOccupant().equals( testPlayer ));
        
        //Testing empty case 
        testContinent2.setOccupant( testPlayerEmpty );
        assertTrue( testContinent2.getOccupant().equals( testPlayerEmpty ));
        
        /*Testing setting occupant to null, if player loses continent 
         *From the line above, testContinent2's occupant is testPlayerEmpty 
         */
         testContinent2.setOccupant( null );
         assertTrue( testContinent2.getOccupant() == null );
        
    }
    

}


 