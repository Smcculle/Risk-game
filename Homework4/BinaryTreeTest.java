/**
 * Homework 4 Binary Tree & Recursion
 * CSCI 2120 Fall 2014
 * @author Shane McCulley
 * @date October 08, 2014
 * @version 0.1
 **/

import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BinaryTreeTest extends TestCase
{
    //repeated test fixtures
    BinaryTree testTree;
    BinaryTree testTreeNeg;
    
    protected void setUp()
    {
        testTree = new BinaryTree( 7 );
        testTreeNeg = new BinaryTree( -7 );
        
    }
    
    //Testing constructor
    public void testConstructor()
    {
        //checking basic constructor assignment
        assertTrue( testTree.getData() == 7 );
        assertTrue( testTreeNeg.getData() == -7 );
		assertTrue( new BinaryTree( 0 ).getData() == 0 );
        
        //testTree and testTreeNeg should not be equal
        assertTrue( !testTree.equals( testTreeNeg ) );
        
        //testing equality of new statements
        assertTrue( !testTree.equals( new BinaryTree( 7 ) ) );
        assertTrue( !testTreeNeg.equals( new BinaryTree( -7 ) ) );
    } 
    
	 /* Testing inOrder() method.  Since it prints the array to 
	 * system.out, we will test using a ByteArrayOutputStream set to system.out. 
     * The output should be in order separated by single spaces, i.e.,
     * 1 2 4 5 7 10 14
     */
    public void testInOrder()
    {
		
		/* Redirect inOrder() output to a byte stream to check expected results
		 * save System.out into a PrintStream so we can restore it when finished
		 */
        PrintStream oldStdOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut( new PrintStream( outContent ) );
		
		/*Testing with single element.  setUp initializes testTree with 7.
		 * inOrder() now generates a bytearrayoutputstream that we will convert
         * to string to compare with expected values.  reset() clears the stream
         */
		String singleExpected = "7";
        testTree.inOrder(); // generates our bytearray of values
        assertEquals( singleExpected, outContent.toString().trim() );         
        outContent.reset();
		
		//single test with -7 initialized
		String singleExpectedNeg = "-7";
		testTreeNeg.inOrder();
		assertEquals( singleExpectedNeg, outContent.toString().trim() );         
        outContent.reset();
		
		//Single test with 0
		String singleExpectedZero = "0";
		new BinaryTree(0).inOrder();
		assertEquals( singleExpectedZero, outContent.toString().trim() );         
        outContent.reset();
		
		//Testing insert with reverse sorted entries and duplicates
		String testInsertTreeExpected = "-30 -26 -5 2 6 24 24 26 30 30";
	
		BinaryTree testInsertTree = new BinaryTree( 30 );
		testInsertTree.insert( 30 );
		testInsertTree.insert( 26 );
		testInsertTree.insert( 24 );
		testInsertTree.insert( 24 );
		testInsertTree.insert( 6 );
		testInsertTree.insert( 2 );
		testInsertTree.insert( -5 );
		testInsertTree.insert( -26 );
		testInsertTree.insert( -30 );
		
		testInsertTree.inOrder();
		assertEquals( testInsertTreeExpected, outContent.toString().trim() );
		outContent.reset();
		
		//Testing insert with sorted entries and duplicates
		String testInsertTree2Expected = "-5 -5 0 2 2 4 7 10 12 12";
		
		BinaryTree testInsertTree2 = new BinaryTree( -5 );
		testInsertTree2.insert( -5 );
		testInsertTree2.insert( 0 );
		testInsertTree2.insert( 2 );
		testInsertTree2.insert( 2 );
		testInsertTree2.insert( 4 );
		testInsertTree2.insert( 7 );
		testInsertTree2.insert( 10 );
		testInsertTree2.insert( 12 );
		testInsertTree2.insert( 12 );
		
		testInsertTree2.inOrder();
		assertEquals( testInsertTree2Expected, outContent.toString().trim() );
	
		
		/* Cleanup.  Closing bytearrayoutputstream has 
        *  no effect, so we ignore that.  
        */ 
		System.out.flush();
        System.setOut( oldStdOut );
		 
    }
    
    /* Testing insert() method using toOrder(). Since it prints the array to 
	 * system.out, we will test using a ByteArrayOutputStream set to system.out. 
     * The output should be in order separated by single spaces, i.e.,
     * 1 2 4 5 7 10 14
     */
    public void testInsert()
    {
		/* Redirect inOrder() output to a byte stream to check expected results
		 * save System.out into a PrintStream so we can restore it when finished
		 */
        PrintStream oldStdOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut( new PrintStream( outContent ) );
		
		BinaryTree insertRight = new BinaryTree( 0 );
		BinaryTree insertLeft = new BinaryTree( 0 );
        
        /* Insert some values for printing into testTree 
		 * and testTreeNeg that may cause insert problems 
		 */
		 
		// Note: testTree initialized with +7 in setUp()
		String testTreeExpected = "0 1 1 4 7 7 7 7 10 14 20";
        
        testTree.insert( 7 );
        testTree.insert( 1 );
        testTree.insert( 7 );
        testTree.insert( 14 );
        testTree.insert( 7 );
        testTree.insert( 10 );
        testTree.insert( 4 );
		testTree.insert( 1 );
		testTree.insert( 20 );
		testTree.insert( 0 );
		
		// Note: testTreeNeg initialized with -7 in setUp() 
		String testTreeNegExpected = "-14 -10 -10 -7 -5 -4 -2 -1";
			
		testTreeNeg.insert( -10 );
        testTreeNeg.insert( -5 );
        testTreeNeg.insert( -1 );
        testTreeNeg.insert( -14 );
        testTreeNeg.insert( -2 );
        testTreeNeg.insert( -10 );
        testTreeNeg.insert( -4 );
        
		/* insertLeft will add increasingly lower values to make a left 
		 * unbalanced tree with all right subtrees equal to null
		 */
		String insertLeftExpected = "-50 -40 -30 -20 -10 -5 0";
	
		insertLeft.insert( -5 );
		insertLeft.insert( -10 );
		insertLeft.insert( -20 );
		insertLeft.insert( -30 );
		insertLeft.insert( -40 );
		insertLeft.insert( -50 );
		
		/* insertRight will add increasingly higher values to make a right 
		 * unbalanced tree with all left subtrees equal to null
		 */
		String insertRightExpected = "0 5 10 20 30 40 50";
	
		insertRight.insert( 5 );
		insertRight.insert( 10 );
		insertRight.insert( 20 );
		insertRight.insert( 30 );
		insertRight.insert( 40 );
		insertRight.insert( 50 );
		 
        /* inOrder() now generates a bytearrayoutputstream that we will convert
         * to string to compare with expected values.  reset() clears the stream
         */
        testTree.inOrder(); // generates our bytearray of values
        assertEquals( testTreeExpected, outContent.toString().trim() );         
        outContent.reset();
        
        // repeat test with testTreeNeg in the same way
        testTreeNeg.inOrder(); 
        assertEquals( testTreeNegExpected, outContent.toString().trim() ); 
		outContent.reset();
		
		// repeat test with insertLeft in the same way
        insertLeft.inOrder(); 
        assertEquals( insertLeftExpected, outContent.toString().trim() ); 
		outContent.reset();
		
		// repeat test with testTreeNeg in the same way
        insertRight.inOrder(); 
        assertEquals( insertRightExpected, outContent.toString().trim() ); 
		outContent.reset();
        
        /* Cleanup.  Closing bytearrayoutputstream has 
        *  no effect, so we ignore that.  
        */ 
		System.out.flush();
        System.setOut( oldStdOut );
    }
    
    
}