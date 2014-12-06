/**
 * CSCI 2120 Fall 2014
 * Risk class RiskUtils
 * @author Shane McCulley
 * @date Dec 5, 2014
 **/
package engine;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Utility function class 
 */
public final class RiskUtils
{
	private static final String PATH_DIR = "images/";
	
	/* RiskUtils should not be instantiated */
	private RiskUtils() {}
	
	
	/**
	 * Returns an image icon from the images folder.  
	 * @param iconName image file name supplied with extension.  
	 * @return a JLabel containing the image.   
	 */
	public static JLabel getIcon( String iconName )
	{
		return new JLabel( new ImageIcon( getImage( iconName ) ) );	
	}
	
	public static JLabel getScaledIcon( String pathName, int x, int y )
	{
		BufferedImage img = getImage( pathName );
		
		return new JLabel( new ImageIcon( 
				img.getScaledInstance( x, y, Image.SCALE_SMOOTH )));
	}
	
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
	
	private static URL getResource( String fileName )
	{
		String pathName = PATH_DIR + fileName;
		URL url = RiskUtils.class.getClassLoader().getResource( pathName );
		
		return url;
	}


}
