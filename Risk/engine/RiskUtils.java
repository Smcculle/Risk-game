/**
 * CSCI 2120 Fall 2014
 * Risk class RiskUtils
 * @author Shane McCulley
 * @date Dec 5, 2014
 **/
package engine;



import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Utility function class 
 */
public final class RiskUtils
{
	private static final String PATH_DIR = "images/";
	private static final double START_POSITION = 0.25; 
	
	/* RiskUtils should not be instantiated */
	private RiskUtils() {}
	
	public static <K, V> void printM( java.util.Map<K, V> map)
	{
		System.out.printf("Print map-> ");
		for ( Entry<K, V> entry : map.entrySet() )
		{
			K key = entry.getKey();
			V value = entry.getValue();
			System.out.printf("key=%s, value=%s; ", 
					key, value );
		}
	}
	/**
	 * Returns an image icon from the images folder.  
	 * 
	 * @param iconName image file name supplied with extension.  
	 * @return a JLabel containing the image.   
	 */
	public static JLabel getIcon( String iconName )
	{
		BufferedImage img = getImage( iconName );
		return new JLabel( new ImageIcon( img ) );	
	}
	
	/**
	 * Returns a scaled image icon from the images folder.  
	 * @param iconName image file name supplied with extension.  
	 * @return a JLabel containing the image.   
	 */
	public static JLabel getScaledIcon( String iconName, int x, int y )
	{
		BufferedImage img = getImage( iconName );
		
		return new JLabel( new ImageIcon( 
				img.getScaledInstance( x, y, Image.SCALE_SMOOTH )));
	}
	
	/**
	 * Gets an image with the specified name from the images folder.  
	 * 
	 * @param imageName  An image name with the extension.  
	 * @return BufferedImage found at the location provided. 
	 */
	public static BufferedImage getImage( String imageName )
	{
		BufferedImage img = null;
		
		try
		{
			img = ImageIO.read( getResource( imageName ) );
		}
		catch ( IOException e )
		{
			System.err.println( "Error reading image" );
			e.printStackTrace();
			System.exit( 1 );
		}
		
		return img;
	}
	
	/**
	 * Gets a location on the user's screen relative to their resolution.  
	 * Calling with x = y = 0.5 gets the middle of the screen.   
	 * 
	 * @param x double from 0 to 1 representing the weighted x coordinate. 
	 * @param y double from 0 to 1 representing the weighted y coordinate. 
	 * @return Point relative to the screen with the specified weights
	 */
	public static Point getRelativeScreenLocation( double x, double y )
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		return new Point( (int)(d.width * x ), (int)(d.height *y ) );
	}
	
	/**
	 * Default starting location for the risk game.  
	 * @return a Point 1/4 of the way across and down the screen from (0,0).
	 */
	public static Point getStartScreenPosition()
	{
		return getRelativeScreenLocation( START_POSITION, START_POSITION );
	}
	private static URL getResource( String fileName )
	{
		String pathName = PATH_DIR + fileName;
		URL url = RiskUtils.class.getClassLoader().getResource( pathName );
		
		return url;
	}

}