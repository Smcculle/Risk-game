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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

import java.awt.FlowLayout;

public class MapScreenPanelTest extends JPanel
{
	public class MapImage extends Component
	{
		private BufferedImage bufferedMap; 
		private int w, h; 
		
		public MapImage( )
		{
			bufferedMap = readImage();
			w = bufferedMap.getWidth();
			h = bufferedMap.getHeight();
			System.out.println( "Width: " + w + " and height: " + h );
			
		}
		
		private BufferedImage readImage()
		{
			
			String pathName = "RiskBoard.jpg";
			URL url = MapScreenPanelTest.class.getResource( pathName );
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
				return new Dimension( w, h );
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
	private Queue<String> numberQueue;
	
	/**
	 * Create the panel.
	 */
	public MapScreenPanelTest()
	{
		circlesToDraw = new HashMap<Point, String>();
		
		final MapImage map = new MapImage();
		this.img = map; 	
		JButton testButton = new JButton( "Test me " );
		testButton.setBounds(215, 5, 75, 23);
		//setLayout(null);
		JLabel testLabel = new JLabel( "Test label:" );
		testLabel.setBounds(160, 9, 50, 14);
		
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
				
			}
		} );
		this.add( testButton );				
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(425, 11, 200, 22);
		for( String s : getComboString() )
			comboBox.addItem( s );
		setLayout(null);
		
		comboBox.setOpaque( false );
		//comboBox.setBounds(240, 59, 200, 20);
		add(comboBox);
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
		g.drawImage( img.getImg(), 0, 0, null );
		
		for( Map.Entry<Point, String> entry : circlesToDraw.entrySet() )
		{
			Point p = entry.getKey();
			String s = entry.getValue();
			g.setColor( getColor() );
			int x = p.x - (CIRCLE_DIAMETER / 2 );
			int y = p.y - (CIRCLE_DIAMETER / 2 );
			g.fillOval( x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER );
			g.setColor( java.awt.Color.BLACK );
			//g.drawString( s, p.x + 5, p.y + 5 );
			drawCenteredText( g, p.x, p.y, 14.0f, s );
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
		f.getContentPane().add( "Center", mps );
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
			System.out.println( "Mouse click at x,y = " + e.getX() + "," + e.getY() );	
			MapScreenPanelTest mps = (MapScreenPanelTest)e.getSource();
			
			mps.addCircle( e.getX(), e.getY() );
			
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
