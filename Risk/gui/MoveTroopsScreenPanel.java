/**
 * CSCI 2120 Fall 2014
 * Risk class AttackScreenPanel
 * @author Shane McCulley
 * @date Dec 4, 2014
 **/
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import classes.Player;
import classes.Territory;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

@SuppressWarnings( "serial" )
public class MoveTroopsScreenPanel extends JPanel
{

	private static final String PATH_DIR = "images/";
	
	private MoveTroopsScreenHandler handler;
	private String errorMessage;

	/* north panel description */
	private JPanel descriptionPanel;
	private JLabel descriptionLabel, instructionLabel;

	/* west panel and labels */
	private JPanel labelPanel;
	private JLabel movingFromLabel, movingToLabel;

	/* center buttons, slider */
	private JPanel sliderPanel;
	private JPanel choicePanel;
	private JFormattedTextField textField;
	private JSlider troopSlider;
	private JButton minButton;
	private JButton maxButton;

	/* south menu options */
	private JPanel menuPanel;
	private JButton moveButton;

	/* defender, attacker information */
	private Territory movingFrom;
	private Territory movingTo;
	private Player movingPlayer;

	/**
	 * Create the panel.
	 * 
	 */
	public MoveTroopsScreenPanel( MoveTroopsScreenHandler handler )
	{
		this.handler = handler;
		this.setLayout( new BorderLayout( 10, 10 ) );
		// TODO: remove
		dummyTerritory();
		
		descriptionPanel = getDescriptionPanel();
		this.add( descriptionPanel, BorderLayout.NORTH );

		labelPanel = getLabelPanel();
		this.add( labelPanel, BorderLayout.WEST );

		sliderPanel = getSliderPanel();
		this.add( sliderPanel, BorderLayout.CENTER );

		menuPanel = getMenuPanel();
		this.add( menuPanel, BorderLayout.SOUTH );

		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );

		( (MoveTroopsScreenHandler)handler ).addView( this );
		
		//TODO: remove
		initPanel(3);
	}
	//TODO REMOVE
	public void testMove()
	{
		movingPlayer.moveTroops( movingFrom, movingTo, 
				Integer.parseInt( textField.getText() ) );
	}
	// TODO tie this in with good transition attack-> move panel, and fortify
	// i,e., add territories moving from, to here
	public void initPanel( int minToMove )
	{
		int maxToMove = movingFrom.getNumArmies() - 1;
		errorMessage = "Please enter an integer from " 
				+ minToMove + " to " + maxToMove;
		
		troopSlider.setMinimum( minToMove );
		troopSlider.setMaximum( maxToMove );
		troopSlider.setMajorTickSpacing( 2 );
		troopSlider.setPaintTicks( true );
		troopSlider.setPaintLabels( true );
		troopSlider.setValue( maxToMove );

		/* add action to check for proper input */

		// textField.getActionMap().put("check", anAction)

		
		 // allow user to input a number within specified range */
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(); 
		NumberFormatter formatter = new NumberFormatter( numberFormat );
		formatter.setMinimum( minToMove ); formatter.setMaximum( maxToMove );
		DefaultFormatterFactory nfFactory = 
				new DefaultFormatterFactory( formatter );
		  
		textField.setFormatterFactory( nfFactory );
		  
		  
		textField.getInputMap().put(KeyStroke.getKeyStroke(
				KeyEvent.VK_ENTER, 0), "verify"); 
		textField.getActionMap().put(
						"verify", handler.getVerifyTextAction() );
		 /*
		textField.getInputMap().put( KeyStroke.getKeyStroke(
				KeyEvent.VK_ENTER, 0 ),
				"check" );
		textField.getActionMap().put( "check", new AbstractAction()
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( !textField.isEditValid() )
				{ // The text is invalid.
					Toolkit.getDefaultToolkit().beep();
					textField.selectAll();
				}
				else
					try
					{ // The text is valid,
						textField.commitEdit(); // so use it.
						moveButton.requestFocus();
					}
					catch ( java.text.ParseException exc )
					{
					}
			}
		} );*/

	}

	public void handleValidInput()
	{
		moveButton.setEnabled( true );
		moveButton.requestFocus();
	}
	
	public void handleInvalidInput()
	{
		textField.selectAll();
		moveButton.setEnabled( false );
		JOptionPane.showMessageDialog( this, errorMessage, 
				"Invalid troop selection", 
				JOptionPane.ERROR_MESSAGE);	
		
	}
	
	public void setMaxTroops()
	{
		setTextField( troopSlider.getMaximum() );
	}
	
	public void setMinTroops()
	{
		setTextField ( troopSlider.getMinimum() );
	}
	public void setTextField( int newValue )
	{
		textField.setValue( newValue );
		troopSlider.setValue( newValue );
		handleValidInput();
	}
	
	// TODO erase
	private void dummyTerritory()
	{
		movingPlayer = new Player( "Test 1" );
		movingFrom = new Territory( "Western United States", null, null );
		movingTo = new Territory( "Eastern United States", null, null );
		movingFrom.setNumArmies( 10 );
		movingTo.setNumArmies( 7 );
		movingPlayer.addTerritory( movingFrom );
		movingPlayer.addTerritory( movingTo );

		// TODO test value
	}

	/**
	 * @return the description panel for the top of the panel
	 */
	private JPanel getDescriptionPanel()
	{
		JPanel result = new JPanel( new BorderLayout() );
		descriptionLabel = new JLabel(
				"<html>"
						+ "<font size = \"4\">Moving from "
						+ "<font size = \"5\">" + movingFrom.getName()
						+ "<font size = \"4\">" + " to "
						+ "<font size = \"5\">" + movingTo.getName()
						+ "</html>" );
		descriptionLabel.setBorder(
				BorderFactory.createBevelBorder( BevelBorder.LOWERED ) );

		result.add( descriptionLabel, BorderLayout.WEST );

		return result;
	}

	/**
	 * @return the label panel for center west.
	 */
	private JPanel getLabelPanel()
	{
		JPanel result = new JPanel( new GridLayout( 2, 1 ) );
		movingFromLabel = new JLabel();
		movingToLabel = new JLabel();

		result.add( movingFromLabel );
		result.add( movingToLabel );
		setLabelText();

		return result;
	}

	private void setLabelText()
	{

		movingFromLabel.setText( "<html>" + movingFrom.getName()
				+ "<br>Troops: " + movingFrom.getNumArmies() + "</html>" );

		movingToLabel.setText( "<html>" + movingTo.getName()
				+ "<br>Troops: " + movingTo.getNumArmies() + "</html>" );

	}

	private JPanel getSliderPanel()
	{

		JPanel result = new JPanel( new BorderLayout() );
		JPanel innerPanel = new JPanel();
		choicePanel = new JPanel();

		minButton = new JButton( "Min" );
		minButton.addActionListener( handler );

		maxButton = new JButton( "Max" );
		maxButton.addActionListener( handler );

		/* value will be set in init based on territories */
		troopSlider = new JSlider();
		troopSlider.addChangeListener( handler );
		textField = new JFormattedTextField();
		//textField.addActionListener( handler );
		textField.setActionCommand( "textField" );
		textField.setColumns( 3 );
		textField.setAlignmentX( CENTER_ALIGNMENT );

		innerPanel.add( minButton );
		innerPanel.add( troopSlider );
		innerPanel.add( maxButton );
		result.add( innerPanel, BorderLayout.CENTER );

		instructionLabel = new JLabel( "Troops to move:" );
		instructionLabel.setHorizontalAlignment( JLabel.CENTER );
		choicePanel.add( instructionLabel );
		choicePanel.add( textField );

		result.add( choicePanel, BorderLayout.NORTH );

		return result;
	}

	private JPanel getMenuPanel()
	{
		JPanel result = new JPanel();

		moveButton = new JButton( "Move" );
		moveButton.addActionListener( handler );

		result.add( moveButton );
		return result;
	}

	// TODO : remove
	/** testing ***********************/
	public void setMovingTo( Territory movingTo )
	{
		this.movingTo = movingTo;
	}

	public void setMovingFrom( Territory movingFrom )
	{
		this.movingFrom = movingFrom;
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
		JFrame jf = new JFrame();

		/* fake territory data to view */
		Territory attacker = new Territory( "Attackistan", null, null );
		attacker.setNumArmies( 15 );
		Territory defender = new Territory( "Defendaria", null, null );
		defender.setNumArmies( 10 );

		MoveTroopsScreenPanel msp =
				new MoveTroopsScreenPanel( new MoveTroopsScreenHandler( null ) );

		msp.initPanel( 2 );
		jf.getContentPane().add( msp );
		jf.pack();
		jf.setVisible( true );
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

}
