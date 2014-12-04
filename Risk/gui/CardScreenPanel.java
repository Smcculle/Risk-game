/**
 * CSCI 2120 Fall 2014
 * Risk class CardScreenPanel
 * @author Shane McCulley
 * @date Dec 2, 2014
 **/
package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.ScrollPaneConstants;

import classes.Card;

@SuppressWarnings( "serial" )
public class CardScreenPanel extends JPanel
{
	private static final int PREFERRED_WIDTH = 843;
	private static final int PREFERRED_HEIGHT = 227;
	private JPanel menuButtonPanel;
	private JButton exitButton;
	private JButton acceptButton;
	private JLabel chooseSet;
	private JPanel cardPanel;
	private JScrollPane scrollPane; 
	private CardScreenHandler handler; 
	
	public CardScreenPanel( CardScreenHandler handler )
	{
		this.handler = handler; 
		setLayout( new BorderLayout( 10, 10 ) );

		chooseSet = new JLabel( "Choose a set of 3 cards below.  "
				+ "Match 3 of a kind or 1 of each infantry, cavalry, artillery" );
		this.add( chooseSet, BorderLayout.NORTH );
		
		/* holds the bottom buttons */
		menuButtonPanel = new JPanel();
		menuButtonPanel.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 5 ) );
		
		/* accept button */
		acceptButton = new JButton( "Accept" );
		acceptButton.setEnabled( false );
		menuButtonPanel.add( acceptButton );
		// acceptButton.addActionListener( handler );
		
		/* exit button */
		exitButton = new JButton( "Exit" );
		exitButton.setPreferredSize( acceptButton.getPreferredSize() );
		// exitButton.addActionListener( handler );
		menuButtonPanel.add( exitButton );
		
		//menuButtonPanel.setPreferredSize( menuButtonPanel.getPreferredSize() );
		
		/* panel for cards */ 
		cardPanel = new JPanel();
		scrollPane = new JScrollPane(); 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.getHorizontalScrollBar().setUnitIncrement( 20 );
		scrollPane.getHorizontalScrollBar().setBlockIncrement( 150 );
		
		
		this.add( scrollPane, BorderLayout.CENTER );
		scrollPane.setVisible( true );
		scrollPane.setViewportView( cardPanel );
		
		/* To prevent a very large screen if player has 6+ cards */
		scrollPane.setPreferredSize( new Dimension( PREFERRED_WIDTH, PREFERRED_HEIGHT));
		this.add( menuButtonPanel, BorderLayout.SOUTH );
		this.setSize( 640, 380 );
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		

	}
	
	private JPanel createCardPanel()
	{	
		JScrollBar scrollBar = new JScrollBar();
		JPanel result = new JPanel();
		result.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
		result.add( scrollBar );
		
		return result;
		
	}
	
	public void addCard( Card c )
	{
		CardComponent newCard = new CardComponent( c );
		newCard.addMouseListener( handler );
		cardPanel.add( newCard );
	}
	
	public void getaSize()
	{
		System.out.println( "JSP size:" + scrollPane.getSize() );
		System.out.println( "cardPanel size:" + cardPanel.getSize() );
		System.out.println( "this size:" + this.getSize() );
	}
	
	/** Testing below */
	public static void main( String[] args )
	{
		
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				createAndShowGUI();
			}
		} );
	}

	protected static CardScreenPanel initTest()
	{
		CardScreenHandler csh = new CardScreenHandler( null );
		CardScreenPanel csp = new CardScreenPanel( csh );
		Card testA = new Card( "cannon", "Northeast Territory" );
		Card testB = new Card( "horse", "Eastern United States" );
		Card testC = new Card( "troop", "Kamchatka" );
		csp.addCard( testA );
		csp.addCard( testB );
		csp.addCard( testC );
		csp.addCard( testA );
		csp.addCard( testA );
		csp.addCard( testA );
		csp.addCard( testB );
		csp.addCard( testC );
		csp.addCard( testA );
		csp.addCard( testA );
		
		return csp; 
	}
	protected static void createAndShowGUI()
	{
		JFrame testFrame = new JFrame();
		testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		CardScreenPanel csp = initTest();
		
		//testFrame.getContentPane().add( new CardComponent( testC ) );
		testFrame.getContentPane().add( csp );
		testFrame.pack();
		testFrame.setVisible( true );
		csp.getaSize();
	}
}
