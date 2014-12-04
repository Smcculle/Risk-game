/**
 * CSCI 2120 Fall 2014
 * Risk class MapScreenPanel
 * @author Shane McCulley
 * @date Dec 1, 2014
 **/
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

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
	
	/* instance variables */
	private MapImage img;  
	private Map<Point, String> circlesToDraw;
	private static final int CIRCLE_DIAMETER = 25;
	private static final int CIRCLE_HEIGHT = 24;
	private static final int CIRCLE_WIDTH = 26;
	private Queue<String> numberQueue;
	private JInternalFrame jif;
	private JPanel menuPanel;
	
	/**
	 * Create the panel.
	 */
	public MapScreenPanelTest()
	{
		jif = new JInternalFrame();
		//jif.setSize(423, 340);
		jif.setLocation(273, 160);
		
		
		circlesToDraw = new HashMap<Point, String>();
		
		
		final MapImage map = new MapImage();
		this.img = map; 	
		JButton testButton = new JButton( "Test me " );
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
		testButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				for( Map.Entry<Point, String> entry : circlesToDraw.entrySet() )
				{
					Point key = entry.getKey();
					String value = entry.getValue();
					value = Integer.toString( (Integer.parseInt( value ) + 1 ) );
					circlesToDraw.put( key, value );
					repaint();
				}
				jif.setVisible( !jif.isVisible() );
			}
		} );
		//adding to panel above this.add( testButton );				
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(425, 11, 200, 22);
		for( String s : getComboString() )
			comboBox.addItem( s );
		setLayout(null);
		//adding to panel above setLayout(null);
		
		comboBox.setOpaque( false );
		//comboBox.setBounds(240, 59, 200, 20);
		//adding to panel above add(comboBox);
		menuPanel.add( testButton );
		
		Component horizontalStrut = Box.createHorizontalStrut(350);
		horizontalStrut.setMaximumSize(new Dimension(50, 32767));
		horizontalStrut.setMinimumSize(new Dimension(50, 0));
		menuPanel.add(horizontalStrut);
		menuPanel.add( comboBox );
		
		JComboBox<String> defendBox = new JComboBox<String>( );
		defendBox.addItem( "Western United States" );
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		menuPanel.add(horizontalStrut_1);
		
		menuPanel.add( defendBox );
		//this.setLayout( new BorderLayout() );
		
		this.add( menuPanel );
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
		
		jif.getContentPane().add( CardScreenPanel.initTest() );
		jif.pack();
		this.add( jif );
		jif.setVisible( true );
		
		JInternalFrame jif2 = new JInternalFrame();
		jif2.getContentPane().add( new AttackScreenPanel() );
		jif2.setLocation( 350, 250 );
		jif2.pack();
		this.add( jif2 );
		jif2.setVisible( true );
		
	}
	public BufferedImage getOverlay()
	{
		return img.mapOverlay;
	}
	
	public void addCircle( int x, int y ) 
	{
		Random random = new Random();
		int troop = random.nextInt( 50 );
		circlesToDraw.put( new Point( x, y ), Integer.toString( troop ));
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
		
		System.out.println ( "Big daddy paint now " );
		g.drawImage( img.getImg(), 0, 40, null );
		
		for( Map.Entry<Point, String> entry : circlesToDraw.entrySet() )
		{
			Point p = entry.getKey();
			String s = entry.getValue();
			g.setColor( getColor() );
			
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
			drawCenteredText( g, p.x, p.y, 13.0f, s );
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
	public static void createGUI()
	{
		System.out.println( "Launched" );
		JFrame f = new JFrame( "Main window" );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		MapScreenPanelTest mps = new MapScreenPanelTest();
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
			mps.addCircle( e.getX(), e.getY() );
			
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
