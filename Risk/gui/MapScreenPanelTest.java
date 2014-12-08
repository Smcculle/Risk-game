/**
 * CSCI 2120 Fall 2014
 * Risk class MapScreenPanel
 * @author Shane McCulley
 * @date Dec 1, 2014
 **/
package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;

import java.awt.FlowLayout;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import classes.Player;
import classes.Territory;
import engine.RiskUtils;

@SuppressWarnings( "serial" )
public class MapScreenPanelTest extends JPanel
{
	/** inner class MapImage */
	public class MapImage extends Component
	{
		private BufferedImage bufferedMap; 
		private BufferedImage mapOverlay;
		private final static String MAP_PATH = "images/RiskBoard.jpg";
		private final static String OVERLAY_PATH = "images/BoardOverlay.png";
		
		private int w, h; 
		
		public MapImage( )
		{
			bufferedMap = readImage( MAP_PATH );
			mapOverlay = readImage( OVERLAY_PATH );
			w = bufferedMap.getWidth();
			h = bufferedMap.getHeight();
			System.out.println( "Width: " + w + " and height: " + h );
			
		}
		
		private BufferedImage readImage( String pathName )
		{
			
			//String pathName = "RiskBoard.jpg";
			//String s = "images/cannon.png";
			//player1Label = new JLabel(new javax.swing.ImageIcon(getClass().getClassLoader().getResource(s)));
			URL url = MapScreenPanelTest.class.getClassLoader().getResource( pathName );
			BufferedImage img = null;
			
			try
			{
				img = ImageIO.read( url );
			}
			catch ( IOException e )
			{
				System.err.println( "Error reading image" );
				e.printStackTrace();
				System.exit( 1 );
			}
			
			return img;
		}
		
		public Dimension getPreferredSize()
		{
			if( (w+h) != 0 )
				return new Dimension( w, h + 40 );
			else
				return super.getPreferredSize();
			
		}
		
		@Override
		public void paint( Graphics g )
		{
			System.out.println( "in the paint" );
			g.drawImage( bufferedMap, 0, 0, null );
		}
		public BufferedImage getImg()
		{
			return bufferedMap;
		}
		
	}

	private ActionListener handler;
	
	/* instance variables */
	private MapImage img;  
	private Map<Point, Territory> circlesToDraw;
	private static final int CIRCLE_DIAMETER = 25;
	private static final int CIRCLE_HEIGHT = 24;
	private static final int CIRCLE_WIDTH = 26;
	private Queue<String> numberQueue;
	
	/* north panel */
	private JPanel menuPanel;
	private JLabel infoLabel;
	private JComboBox<String> actionFromBox; 
	private JComboBox<String> actionToBox;
	private JButton endTurnButton;
	private Component labelSpace; 
	private Component comboBoxSpace;
	private Component endTurnSpace;
	
	
	/* map information */
	private Map<String, Territory> territories;
	
	/* JInternalFrame panels */
	private JInternalFrame cardFrame;
	private CardScreenPanel cardScreenPanel;
	
	private AttackScreenPanel attackScreenPanel;
	private MoveTroopsScreenPanel moveTroopsScreenPanel;
	private JInternalFrame attackFrame; 
	private JPanel currentAttackFramePanel; 
	/**
	 * Create the panel.
	 */
	public MapScreenPanelTest( ActionListener handler )
	{
		this.handler = handler; 
		currentAttackFramePanel = new JPanel();
		
		cardFrame = new JInternalFrame();
		//cardFrame.setLocation(273, 160);
		cardFrame.setLocation( RiskUtils.getRelativeScreenLocation( 0.10, 0.0 ) );
		
		attackFrame = new JInternalFrame();
		attackFrame.setLocation( RiskUtils.getRelativeScreenLocation( 0.15, 0.15 ) );
		
		circlesToDraw = new HashMap<Point, Territory>();
		
		
		final MapImage map = new MapImage();
		this.img = map; 	
		JButton cardButton = new JButton( "Cards" );
		//adding to panel above testButton.setBounds(215, 5, 75, 23);
		//setLayout(null);
		JLabel testLabel = new JLabel( "Test label:" );
		//adding to panel above testLabel.setBounds(160, 9, 50, 14);
		menuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		menuPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EmptyBorder(0, 0, 0, 0)));
		menuPanel.setBounds(0, 0, 1280, 40);
		
		//this.add( testLabel );
		cardButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				for( Map.Entry<Point, Territory> entry : circlesToDraw.entrySet() )
				{
					Point key = entry.getKey();
					Territory value = entry.getValue();
					System.out.printf("Outdated event fired");
					//value = Integer.toString( (Integer.parseInt( value ) + 1 ) );
					//circlesToDraw.put( key, value );
					repaint();
				}
				cardFrame.setVisible( !cardFrame.isVisible() );
			}
		} );
		//adding to panel above this.add( testButton );				
		
		actionFromBox = new JComboBox<String>();
		actionFromBox.setName( "actionFromBox" );
		actionFromBox.setBounds(425, 11, 200, 22);
		actionFromBox.setPreferredSize( new Dimension( 150, 22) );
		//for( String s : getComboString() )
		//	actionFromBox.addItem( s );
		setLayout(null);
		//adding to panel above setLayout(null);
		
		actionFromBox.setOpaque( false );
		//comboBox.setBounds(240, 59, 200, 20);
		//adding to panel above add(comboBox);
		menuPanel.add( cardButton );
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		//horizontalStrut.setMaximumSize(new Dimension(50, 32767));
		//horizontalStrut.setMinimumSize(new Dimension(50, 0));
		menuPanel.add(horizontalStrut);
		
		infoLabel = new JLabel("");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuPanel.add(infoLabel);
		
		labelSpace = Box.createHorizontalStrut(300);
		menuPanel.add(labelSpace);
		menuPanel.add( actionFromBox );
		
		actionToBox = new JComboBox<String>( );
		actionToBox.setName( "actionToBox" );
	//	actionToBox.addItem( "Western United States" );
		
		
		comboBoxSpace = Box.createHorizontalStrut(5);
		menuPanel.add(comboBoxSpace);
		
		menuPanel.add( actionToBox );
		actionToBox.setVisible( false );
		//this.setLayout( new BorderLayout() );
		
		this.add( menuPanel );
		
		endTurnSpace = Box.createHorizontalStrut(20);
		
		menuPanel.add(endTurnSpace);
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setActionCommand( "endTurn" );
		endTurnButton.addActionListener( handler );
		menuPanel.add(endTurnButton);
		/**
		JInternalFrame jif = new JInternalFrame();
		JDesktopPane jdp = new JDesktopPane();
		this.add( jdp, java.awt.BorderLayout.CENTER );
		jif.setSize( 200, 200 );
		jif.setLocation( 1300, 800 );
		jdp.add( jif );
		jif.setVisible( true );
		jif.requestFocusInWindow();
		*/
		
		/*
		jif.getContentPane().add( CardScreenPanel.initTest() );
		jif.pack();
		this.add( jif );
		jif.setVisible( true );
				
		JInternalFrame jif2 = new JInternalFrame();
		jif2.getContentPane().add( new MoveTroopsScreenPanel( 
				new MoveTroopsScreenHandler( null ) ) );
		jif2.setLocation( 350, 250 );
		jif2.pack();
		this.add( jif2 );
		jif2.setVisible( true );
		*/
		( (MapScreenHandler)handler ).addView(this);
		
	}
	public BufferedImage getOverlay()
	{
		return img.mapOverlay;
	}
	
	
	//TODO remove
	/*
	public void addCircle( int x, int y ) 
	{
		circlesToDraw.put( new Point( x, y ), Integer.toString( troop ));
	}*/
	
	/**
	 * Adds a territory's circle to list of circles to draw.  
	 */
	public void addCircle( String newTerritory )
	{
		Territory territory = territories.get( newTerritory );
		circlesToDraw.put( territory.getCircleCenter(), territory);
		
		System.out.printf( "New circle of %s added successfully", territory);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension( img.getPreferredSize() );
	}
	public void initGUI()
	{
		final MapImage map = new MapImage();
		add( "Center", map );
		Dimension awk = map.getPreferredSize();
		this.setSize( awk.width, awk.height );
	}
	
	@Override
	protected void paintComponent( Graphics g )
	{
		
		g.drawImage( img.getImg(), 0, 40, null );
		
		for( Map.Entry<Point, Territory> entry : circlesToDraw.entrySet() )
		{
			Point p = entry.getKey();
			Territory t = entry.getValue();
			g.setColor( t.getColor() );
			
			/*
			int x = p.x - (CIRCLE_DIAMETER / 2 );
			int y = p.y - (CIRCLE_DIAMETER / 2 );
			g.fillOval( x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER );
			*/
			int x = p.x - (CIRCLE_WIDTH / 2 );
			int y = p.y - (CIRCLE_HEIGHT / 2 );
			g.fillOval( x, y, CIRCLE_WIDTH, CIRCLE_HEIGHT );
			
			g.setColor( java.awt.Color.BLACK );
			//g.drawString( s, p.x + 5, p.y + 5 );
			drawCenteredText( g, p.x, p.y, 13.0f, 
					Integer.toString(t.getNumArmies() ));
		}
		
	}
	public java.awt.Color getColor()
	{
		java.util.Random random = new java.util.Random();
		final float hue = random.nextFloat();
		// Saturation between 0.1 and 0.3
		final float saturation = (random.nextInt(2000) + 1000) / 10000f;
		final float luminance = 0.9f;
		final java.awt.Color color = java.awt.Color.getHSBColor(hue, saturation, luminance);
		
		return color;
	}
	
	/**
	 * Revalidate and repaint the main Map.  
	 */
	public void updateMap()
	{
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Update map data with new troop values, erase actionFromBox items.  
	 */
	public void updateMove( Player currentPlayer )
	{
		attackFrame.setVisible( false );
		updateAttackBox( currentPlayer );
		actionFromBox.removeAllItems();
	}
	
	/**
	 * Initializes the attack panel from territory selected in actionFromBox
	 * to territory in actionToBox.  
	 */
	public void attack()
	{
		/* get territories from each box */
		Territory attacker = 
				territories.get( (String)actionFromBox.getSelectedItem() );
		Territory defender = 
				territories.get( (String)actionToBox.getSelectedItem() );
		
		/* initialize panel with data */
		attackScreenPanel.attack( attacker, defender );
		
		this.changeAttackFrameContent( attackScreenPanel );
		attackFrame.setVisible( true );
		
	}
	
	/**
	 * Fortify phase: Initialize the move panel with territories selected 
	 * in actionFromBox to territory in actionToBox
	 */
	public void fortify()
	{
		moveTroopsScreenPanel.moveTroops( 
				territories.get( (String)actionFromBox.getSelectedItem() ), 
				territories.get( (String)actionToBox.getSelectedItem() ) );
		
		attackFrame.setVisible( false );
		
		this.changeAttackFrameContent( moveTroopsScreenPanel );
		attackFrame.show();
		
	}
	
	
	/**
	 * Updates content in attackFrame with newContent
	 * 
	 * @param newContent JPanel containing new content.  
	 */
	private void changeAttackFrameContent( JPanel newContent )
	{
		attackFrame.getContentPane().remove( currentAttackFramePanel );
		currentAttackFramePanel = newContent; 
		attackFrame.getContentPane().add( currentAttackFramePanel );
		attackFrame.pack();
		attackFrame.revalidate();
		attackFrame.repaint();
	}
	
	/**
	 * Initialization method at start of game to construct game board.  
	 * 
	 * @param territories list of territories from gameboard.  
	 */
	public void setMap( Map<String, Territory> territories )
	{
		this.territories = territories; 
		initActionFromBox( territories );
	}
	
	/**
	 * Fills the actionFromBox with territories for choosing by the 
	 * current player.  
	 * 
	 * @param territories A list of territories to put in the box.  
	 */
	public void initActionFromBox( Map<String, Territory> territories )
	{
		actionFromBox.removeActionListener( handler );
		actionFromBox.removeAllItems(); 
		
		for( String s : territories.keySet() )
		{
			actionFromBox.addItem( s );
		}
		
		actionFromBox.addActionListener( handler );
		this.updateMap();
	}
	
	
	/**
	 * Updates label with appropriate text for assigning territories and 
	 * sets the current player.  
	 */
	public void assignTerritories( Player currentPlayer )
	{
		setNextPlayer( currentPlayer );
	}
	/**
	 * Sets the panel color of the current player to give visual feedback 
	 * of whose turn it is.
	 *   
	 * @param currentPlayer new Player that is the currentPlayer.  
	 */
	public void setNextPlayer( Player currentPlayer )
	{
		menuPanel.setBackground( currentPlayer.getColor() );
		
	}
	
	/**
	 * Sets the values of the actionFromBox with the territories of the player.
	 *  
	 * @param currentPlayer the current Player.   
	 */
	public void assignArmies( Player currentPlayer )
	{
		setNextPlayer( currentPlayer );
		initActionFromBox( currentPlayer.getTerritoriesList() );
		setLabelPlaceArmies( currentPlayer );
		
	}

	//TODO Distinction not needed 
	public void placeArmies( Player currentPlayer )
	{
		assignArmies( currentPlayer );
	}
	
	private void setLabelAssignTerritories( Player currentPlayer )
	{
		infoLabel.setText( currentPlayer.getName() + ", choose a territory "
				+ "from the box." );
		repaint();
	}
	
	public void setLabelPlaceArmies( Player currentPlayer )
	{
		infoLabel.setText( currentPlayer.getName() + ", place your armies.  " 
				+ currentPlayer.getUnplacedArmies() + " left."  );

		repaint();
	}
	
	private void setLabelAttack( Player currentPlayer )
	{
		infoLabel.setText( currentPlayer.getName() + ", begin attack phase" );
		repaint();
		
	}
	
	private void setLabelFortify( Player currentPlayer ) 
	{
		infoLabel.setText( currentPlayer.getName() + ", reinforcement phase");
		repaint();
	}
	/**
	 * @param currentPlayer
	 */
	public void initFortifyFromChoices( Player currentPlayer )
	{
		setLabelFortify( currentPlayer );
		updateAttackBox( currentPlayer );
	}
	
	public void showMoveTroopsScreen()
	{
		/* player must move a minimum of troops based on # of dice rolled*/
		moveTroopsScreenPanel.moveTroops( 
				attackScreenPanel.getAttacker(), 
				attackScreenPanel.getDefender(), 
				attackScreenPanel.getNumAttacking() );
		
		attackFrame.setVisible( false );
		
		this.changeAttackFrameContent( moveTroopsScreenPanel );
		attackFrame.show();
		
	}
	/**
	 * Brings up the card screen for the current player.  If the player
	 * must trade cards, the exit will be disabled from the window.  
	 */
	public void showCards( Player currentPlayer, boolean mustTradeCards )
	{
		cardScreenPanel.setPlayer( currentPlayer, mustTradeCards );
		
	}
	/**
	 * Sets up the actionToBox for attack state.  
	 */
	public void initActionToBox( String attackingTerritory )
	{
		Territory attackingFrom = territories.get( attackingTerritory );
		Player attackingPlayer = attackingFrom.getOccupant();
		Map<String, Territory> choices = attackingFrom.getNeighbors();
		
		actionToBox.removeActionListener( handler );
		actionToBox.removeAllItems(); 
		
		/* check ownership before adding to box */
		for( Territory t : choices.values() )
		{
			if( t.getOccupant() != attackingPlayer )
			actionToBox.addItem( t.getName() );
		}
		
		actionToBox.addActionListener( handler );	
	}
	
	/**
	 * Populates actionToBox with a list of neighbors of movingTerritory for
	 * fortify state. 
	 * 
	 * TODO merge with initActionToBox with branching point. 
	 * @param movingTerritory String name of territory moving from.  
	 */
	public void initFortifyToChoices( String movingTerritory )
	{
		Territory movingFrom = territories.get( movingTerritory );
		Player movingPlayer = movingFrom.getOccupant();
		Map<String, Territory> choices = movingFrom.getNeighbors();
		
		actionToBox.removeActionListener( handler );
		actionToBox.removeAllItems(); 
		
		/* check ownership before adding to box */
		for( Territory t : choices.values() )
		{
			if( t.getOccupant() == movingPlayer )
			actionToBox.addItem( t.getName() );
		}
		
		actionToBox.addActionListener( handler );	
	}
	
	/**
	 * Shows the actionToBox and populates actionFromBox with list of 
	 * territories that a player can attack from. 
	 * 
	 * @param currentPlayer
	 */
	public void showAttackBox( Player currentPlayer )
	{
		actionToBox.setVisible( true );
		actionToBox.setPreferredSize( actionFromBox.getPreferredSize() );
		setLabelAttack( currentPlayer );
		updateAttackBox ( currentPlayer );
	}
	
	/**
	 * Populates actionFromBox with the current player's territories that
	 * have more than 1 army present.  
	 * 
	 * @param currentPlayer
	 */
	public void updateAttackBox( Player currentPlayer )
	{
		actionFromBox.removeActionListener( handler );
		actionFromBox.removeAllItems(); 
		for( Territory t : currentPlayer.getTerritoriesList().values() )
		{
			/* need at least 2 armies to fortify or attack */
			if( t.getNumArmies() > 1 )
				actionFromBox.addItem( t.getName() );
		}
		
		actionFromBox.addActionListener( handler );
		this.updateMap();
	}
	
	/**
	 * Sets the attack screen panel that was initialized in the GUI.
	 * @param panel the attack screen panel initialized in GUI.  
	 */
	public void setAttackScreenPanel( AttackScreenPanel panel )
	{
		this.attackScreenPanel = panel; 
		currentAttackFramePanel = panel;
		//TODO :check
		attackFrame.getContentPane().add( panel );
		attackFrame.pack();
		this.add( attackFrame );
		//jif.setVisible( true );

	}
	
	/**
	 * Sets the card screen panel that was initialized in the GUI.
	 * @param panel the card screen panel initialized in GUI.  
	 */
	public void setCardScreenPanel( CardScreenPanel panel )
	{
		this.cardScreenPanel = panel;
		
		cardFrame.getContentPane().add( panel );
		cardFrame.pack();
		this.add( cardFrame );
		//jif.setVisible( true );
	}
	
	/**
	 * Sets the move troops screen panel that was initialized in the GUI.
	 * @param panel the move troops screen panel initialized in GUI.  
	 */
	public void setMoveTroopsScreenPanel( MoveTroopsScreenPanel panel )
	{
		this.moveTroopsScreenPanel = panel;
	}
	
	public static void createGUI()
	{
		System.out.println( "Launched" );
		JFrame f = new JFrame( "Main window" );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		MapScreenPanelTest mps = new MapScreenPanelTest( new MapScreenHandler( null ));
		mps.addMouseListener( mps.new MapMouseListener() );
		f.getContentPane().add( mps );
		f.pack();
		f.setVisible( true );
	}
	
	public static void main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			
			@Override
			public void run()
			{
				createGUI();
			}
		} );
		
	}
	public String[] getComboString()
	{
		String[] comboString = { "Middle East",
				"India", 
				"Siam",
				"Afghanistan",
				"China",
				"Ural",
				"Siberia",
				"Mongolia",
				"Japan",
				"Irkutsk",
				"Yakutsk",
				"Kamchatka",
				"Alaska",
				"Northwest Territory",
				"Alberta",
				"Ontario",
				"Quebec",
				"Greenland",
				"Western United States",
				"Eastern United States",
				"Central America",
				"Venezuela",
				"Argentina"};

		return comboString;
	}
	
	public static void drawCenteredText(Graphics g, int x, int y, float size, String text) 
	{
		// Create a new font with the desired size
		Font newFont = g.getFont().deriveFont(size);
		g.setFont(newFont);
		// Find the size of string s in font f in the current Graphics context g.
		FontMetrics fm = g.getFontMetrics();
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(text, g);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());
		// Find the top left and right corner
		int cornerX = x - (textWidth / 2);
		int cornerY = y - (textHeight / 2) + fm.getAscent();

		g.drawString(text, cornerX, cornerY);  // Draw the string.
		
	}
	
	
	public class MapMouseListener implements MouseListener
	{
		
		@Override
		public void mouseClicked( MouseEvent e )
		{
			int x = e.getX();
			int y = e.getY();
			System.out.println( "Mouse click at x,y = " + e.getX() + "," + e.getY() );	
			MapScreenPanelTest mps = (MapScreenPanelTest)e.getSource();
			System.out.println( "RBG in overlay is " + mps.getOverlay().getRGB( e.getX(), e.getY() - 40 )) ;
			//mps.addCircle( e.getX(), e.getY() );
			
			java.awt.Color c = new java.awt.Color( img.mapOverlay.getRGB( x, y - 40) );
			System.out.println( " Blue is : " + c.getBlue() ) ;
			
			//circlesToDraw.add(  new Point( e.getX(), e.getY() ));
			repaint();
		}

		@Override
		public void mouseEntered( MouseEvent e ){}

		@Override
		public void mouseExited( MouseEvent e ){}

		@Override
		public void mousePressed( MouseEvent e ){}

		@Override
		public void mouseReleased( MouseEvent e ){}
		
	}
	
}
