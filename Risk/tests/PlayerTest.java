/**
 * CSCI 2120 Fall 2014
 * Risk Game Class PlayerTest
 * @author Shane McCulley
 * @date October 21, 2014
 **/
 
package tests;

import java.util.HashMap;

import classes.Continent;
import classes.Player;
import classes.Territory;
import junit.framework.TestCase;
 
public class PlayerTest extends TestCase {
     
     //instance variables 
	Player test1; 
	Player test2; 
     
    protected void setUp() 
    {
    	test1 = new Player("Test1");
    	test2 = new Player();
    
    }
    
     // Testing Player's String getName() method
    public void testGetName() 
    {
    	assertTrue( test1.getName() == "Test1");
    	assertTrue( (new Player("").getName() == "" ) );
         
    }
     
     // Testing Player's int getUnplacedArmies() method
    public void testGetUnplacedArmies() 
    {
    	assertTrue( test1.getUnplacedArmies() == 0 );
    	assertTrue( test2.getUnplacedArmies() == 0 );
    	
    	test1.addArmies(5);
    	assertTrue( test1.getUnplacedArmies() == 5 );
    	
    	/* armies haven't been placed yet, so they should be cumulative */
    	test1.addArmies(10);
    	assertTrue( test1.getUnplacedArmies() == 15 );
        
    }
     
    // Testing Player's int getTotalArmies() method
    public void testGetTotalArmies() 
    {
    	Territory t1 = new Territory();
    	t1.setNumArmies(1);
    	Territory t2 = new Territory();
    	t2.setNumArmies(2);
    	Territory t3 = new Territory();
    	t3.setNumArmies(3);
    	
    	/* total armies are 0, they should increase with each territory added */
    	assertTrue( test1.getTotalArmies() == 0 );
    	
    	test1.addTerritory( t1 );
    	assertTrue( test1.getTotalArmies() == 1 );
    	
    	test1.addTerritory( t2 );
    	assertTrue( test1.getTotalArmies() == 3 );
    	
    	test1.addTerritory( t3 );
    	assertTrue( test1.getTotalArmies() == 5 );
    	
    	test1.removeTerritory( t1 );  // should remove 1 army
    	assertTrue( test1.getTotalArmies() == 4 );
    	
    } 
    
    // Testing Player's ArrayList<Territory> getTerritoriesList() method
    public void testGetTerritoriesList() 
    {
    	assertTrue( test1.getTerritoriesList() == null );
    	test1.addTerritory( new Territory() );
    	
    	assertTrue( test1.getTerritoriesList().size() == 1 );
    	
    	HashMap<String, Territory> adj = new HashMap<String, Territory>();
		adj.put( "1",  new Territory( "T1", null ) );
		adj.put( "2",  new Territory( "T2", null ) );
		
		Territory t3 = new Territory( "T3", adj );
		
		test1.addTerritory( t3 ); 
		
		/*test the territories that come back */
		
		HashMap<String, Territory> gtl = test1.getTerritoriesList();
		
		assertTrue( !gtl.isEmpty() );
		assertTrue( gtl.containsKey( "T3" ) );
		assertTrue( gtl.containsValue( t3 ) );
        
    }
     
    // Testing Player's ArrayList<Continent> getContinentsList() method
    public void testGetContinentsList() 
    {
    	Continent con1 = new Continent( "C1", 5 );
    	Continent con2 = new Continent( "C2", 8 );
    	
    	assertTrue( test1.getContinentsList() == null );
    	test1.addContinent( con1 );
    	test1.addContinent( con2 );
    	
    	HashMap<String, Continent> gcl = test1.getContinentsList();
    	assertTrue( !gcl.isEmpty() );
    	
    	/* should contain keys C1, C2 */
    	assertTrue( gcl.containsKey("C1" ) );
    	assertTrue( gcl.containsKey("C2" ) );
    	
    	/* should contain continents Con1 and Con2 as values */
    	assertTrue( gcl.containsValue( con1 ) );
    	assertTrue( gcl.containsValue( con2 ) );
    	
    }
    

    // Testing Player's void addTerritory(Territory) method
    public void testAddTerritory() 
    {
    	assertTrue( test1.getTerritoriesList() == null );
    	test1.addTerritory( new Territory() );
    	
    	assertTrue( test1.getTerritoriesList().size() == 1 );
    	
    	HashMap<String, Territory> adj = new HashMap<String, Territory>();
		adj.put( "1",  new Territory( "T1", null ) );
		adj.put( "2",  new Territory( "T2", null ) );
		
		Territory t3 = new Territory( "T3", adj );
		
		test1.addTerritory( t3 ); 
		
		/*test the territories that come back */
		
		HashMap<String, Territory> gtl = test1.getTerritoriesList();
		
		assertTrue( !gtl.isEmpty() );
		assertTrue( gtl.containsKey( "T3" ) );
		assertTrue( gtl.containsValue( t3 ) );
        
    }
    
    // Testing Player's void addContinent(Continent) method
    public void testAddContinent() 
    {
    	Continent con1 = new Continent( "C1", 5 );
    	Continent con2 = new Continent( "C2", 8 );
    	
    	assertTrue( test1.getContinentsList() == null );
    	
    	test1.addContinent( con1 );
    	assertTrue( test1.getContinentsList().size() == 1 );
    	
    	test1.addContinent( con2 );
    	assertTrue( test1.getContinentsList().size() == 2 );
    	
    	HashMap<String, Continent> gcl = test1.getContinentsList();
    	
    	/* test for con1 and con2 */
    	assertTrue( gcl.containsKey( "C1" ));
    	assertTrue( gcl.containsValue( con1 ));
    	
    	assertTrue( gcl.containsKey( "C2" ));
    	assertTrue( gcl.containsValue( con2 ));
    	
        
    }
    
    // Testing Player's void addArmies(int) method
    public void testAddArmies() 
    {
    	assertTrue( test1.getUnplacedArmies() == 0 );
    	assertTrue( test2.getUnplacedArmies() == 0 );
    	
    	test1.addArmies(5);
    	assertTrue( test1.getUnplacedArmies() == 5 );
    	
    	/* armies haven't been placed yet, so they should be cumulative */
    	test1.addArmies(10);
    	assertTrue( test1.getUnplacedArmies() == 15 );
    	
    	/* testing test2 with several 0s */
    	test2.addArmies(0);
    	assertTrue( test2.getUnplacedArmies() == 0 );
    	
    	test2.addArmies(1);
    	assertTrue( test2.getUnplacedArmies() == 1 );
    	
    	test2.addArmies(0);
    	assertTrue( test2.getUnplacedArmies() == 1 );
    	
    	test2.addArmies(5);
    	assertTrue( test2.getUnplacedArmies() == 6 );
        
    }
    
    
    // Testing Player's void addTerritory() method
    public void addTerritory() 
    {
    	assertTrue( test1.getTerritoriesList() == null );
    	test1.addTerritory( new Territory() );
    	
    	assertTrue( test1.getTerritoriesList().size() == 1 );
    	
    	HashMap<String, Territory> adj = new HashMap<String, Territory>();
		adj.put( "1",  new Territory( "T1", null ) );
		adj.put( "2",  new Territory( "T2", null ) );
		
		Territory t3 = new Territory( "T3", adj );
		
		test1.addTerritory( t3 ); 
		
		/*test the territories that come back */
		
		HashMap<String, Territory> gtl = test1.getTerritoriesList();
		
		assertTrue( !gtl.isEmpty() );
		assertTrue( gtl.containsKey( "T3" ) );
		assertTrue( gtl.containsValue( t3 ) );
        
    }
     
    /*Testing Player's void attack method
     *void attack(Territory attacker, Territory defender, int numAttackingArmies)
    */
    public void testAttack() 
    {
        Territory t1 = new Territory();
        Territory t2 = new Territory();
        t1.setNumArmies(100);
        t2.setNumArmies(5);
        
        test1.addTerritory( t1 );
        test2.addTerritory( t2 );
        
        assertTrue( t1.getOccupant() == test1 );
        assertTrue( t2.getOccupant() == test2 );
        /* after attacking, either t1 or t2 has less armies than before, or both */
        test1.attack( t1, t2, 3);
        
        /* armies should not grow after attacking */
        assertTrue( t1.getNumArmies() <= 100 && t2.getNumArmies() <= 5 );
        
        /* At least one side must lose armies */
        assertTrue( t1.getNumArmies() < 100 || t2.getNumArmies() < 5 );
        
        /* if defender armies reduced to 0, player should change */
        while( t2.getNumArmies() > 0 )
        {
        	test1.attack( t1, t2, 3);   // constantly attack t2 from t1 with 3 armies until win
        }
        
        /* test1 should now control t2 after reducing armies to 0 */
        assertTrue( t2.getOccupant() == test1 );
        
        /* test1 should have to move at least 1 army into t2 */
        assertTrue( t2.getNumArmies() >= 1 );
        
    } 
 }
 
 
