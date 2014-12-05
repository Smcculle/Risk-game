/**
 * CSCI 2120 Fall 2014
 * Risk class DiceButton
 * @author Shane McCulley
 * @date Dec 4, 2014
 **/
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JToggleButton;

/**
 * Draws the background color using paintComponent when necessary.  
 * The background color of JToggleButton is not respected when selected.  
 */
@SuppressWarnings( "serial" )
public class DiceButton extends JToggleButton
{
	
	public DiceButton()
	{
		super();
	}
	
	@Override
	protected void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		
		/* manually draw background */
		if( this.isSelected() && 
				this.getBackground() == Color.RED ||
				this.getBackground() == Color.GREEN )
		{
			 int w = getWidth();
			 int h = getHeight();
			 String s = getText();
			 g.setColor(getBackground());
			 g.fillRect(0, 0, w, h);
			 
			 // correct foreground color 
			 g.setColor(SystemColor.controlText);
			 g.drawString(s,
					 (w - g.getFontMetrics().stringWidth(s)) / 2 + 1,
					 (h + g.getFontMetrics().getAscent()) / 2 - 1);
		}
	}

}
