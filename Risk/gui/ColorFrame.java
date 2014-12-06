/**
 * CSCI 2120 Fall 2014
 * Risk class ColorFrame
 * @author Shane McCulley
 * @date Dec 6, 2014
 **/
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates a frame to choose the player's color.
 */
@SuppressWarnings( "serial" )
public class ColorFrame extends JPanel
{

	private ActionListener handler; 
	private JPanel colorPanel;
	private JButton redButton, blueButton, greenButton, purpleButton,
			orangeButton, yellowButton, brownButton, pinkButton, grayButton;

	public ColorFrame( ActionListener handler )
	{
		this.setLayout( new BorderLayout() );
		initComponents();
		setColors();
		addToPanel();
		
		this.add( colorPanel, BorderLayout.CENTER );
		((CreatePlayersScreenHandler)handler).setFrame( this );
	}

	private void initComponents()
	{
		colorPanel = new JPanel( new GridLayout(3, 3, 5, 5) );
		redButton = new JButton();
		blueButton = new JButton();
		greenButton = new JButton();
		purpleButton = new JButton();
		orangeButton = new JButton();
		yellowButton = new JButton();
		brownButton = new JButton();
		pinkButton = new JButton();
		grayButton = new JButton();
		//redButton.addActionListener( handler );
		//redButton.setActionCommand( "red" );
		redButton.addChangeListener( new ChangeListener()
		{
			
			@Override
			public void stateChanged( ChangeEvent e )
			{
				System.out.println( "Changed " + e );
				
			}
		} );
		blueButton.setActionCommand( "wat" );
		blueButton.addActionListener( new ActionListener()
		{
			
			@Override
			public void actionPerformed( ActionEvent e )
			{
				System.out.println("Pushed button with value " + e.getActionCommand());
				SwingUtilities.getWindowAncestor( (Component)e.getSource()).setVisible( false ); 
				
			}
		});
		greenButton.addActionListener( handler );
		purpleButton.addActionListener( handler );
		orangeButton.addActionListener( handler );
		yellowButton.addActionListener( handler );
		brownButton.addActionListener( handler );
		pinkButton.addActionListener( handler );
		grayButton.addActionListener( handler );
	}
	
	private void setColors()
	{
		redButton.setBackground( new Color( 228, 26, 28 ) );
		redButton.setText( "Red" );
	
		blueButton.setBackground( new Color( 55, 126, 184 ) );
		blueButton.setText( "Blue" );
		
		greenButton.setBackground( new Color( 77, 175, 74 ) );
		greenButton.setText( "Green" );
		
		purpleButton.setBackground( new Color( 152, 78, 163 ) );
		purpleButton.setText( "Purple" );
		
		orangeButton.setBackground( new Color( 255, 127, 0 ) );
		orangeButton.setText( "Orange" );
		
		yellowButton.setBackground( new Color( 255, 255, 51 ) );
		yellowButton.setText( "Yellow" );

		brownButton.setBackground( new Color( 166, 86, 40 ) );
		brownButton.setText( "Brown" );

		pinkButton.setBackground( new Color( 247, 129, 191 ) );
		pinkButton.setText( "Pink" );
	
		grayButton.setBackground( new Color( 153, 153, 153 ) );
		grayButton.setText( "Gray" );
		
	}
	
	private void addToPanel()
	{
		colorPanel.add( redButton );
		colorPanel.add( blueButton );
		colorPanel.add( greenButton );
		colorPanel.add( purpleButton );
		colorPanel.add( orangeButton );
		colorPanel.add( yellowButton );
		colorPanel.add( brownButton );
		colorPanel.add( pinkButton );
		colorPanel.add( grayButton );
	}
	
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
	public static void createAndShowGUI()
	{
		javax.swing.JFrame jf = new javax.swing.JFrame();
		ColorFrame frame = new ColorFrame( null );
		jf.getContentPane().add( frame );
		jf.pack();
		jf.setVisible( true );
		jf.setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );
	}
}
