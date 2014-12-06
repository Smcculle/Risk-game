/**
 * CSCI 2120 Fall 2014
 * Risk Game class CreatePlayersScreenPanel
 * @author Shane McCulley
 * @date November 29, 2014
 **/

package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import engine.RiskUtils;

@SuppressWarnings( "serial" )
public class CreatePlayersScreenPanel extends JPanel
{

	private final String[] COMBO_BOX_ITEMS =
	{ "3", "4", "5", "6" };
	private final int TEXT_FIELD_LENGTH = 20;
	private final int MAX_PLAYERS = 6; 
	private final double SCREEN_OFFSET = 0.33;
	
	/* north panel */
	private JPanel northPanel; 
	private JLabel gameNameLabel;
	private JTextField gameNameField;
	private JLabel numPlayersLabel;
	private JComboBox<String> numPlayersBox;
	
	/* center panel*/
	private JPanel playersPanel; 
	
	/* center west */
	private JButton[] colorButtons;
	private JLabel[] playerLabels; 
	
	/* center east */
	private JTextField[] playerFields;
	
	/* south */
	private JPanel menuButtonPanel;
	private JButton backButton;
	private JButton nextButton;
	
	/* color chooser */
	private JOptionPane colorPane; 
	private ColorFrame colorFrame; 
	private JDialog dialog; 
	
	public CreatePlayersScreenPanel( ActionListener handler )
	{
		
		this.setLayout( new BorderLayout( 0, 15 ) );
		
		/* initialize components  */
		initLabelsAndButtons( handler );
		initColorChooser( handler );
		initFields();
		initComboBox();
		

		northPanel = getLabelPanel();
		this.add( northPanel, BorderLayout.NORTH );

		playersPanel = getPlayersPanel();
		this.add( playersPanel, BorderLayout.CENTER );
		
		
		menuButtonPanel = getMenuButtonPanel( handler );
		this.add( menuButtonPanel, BorderLayout.SOUTH );
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		this.setPreferredSize( this.getPreferredSize() );
		
		/* set the dialog to appear closer to the center of the JFrame */
		dialog.setLocation( RiskUtils.getRelativeScreenLocation( 
				SCREEN_OFFSET, SCREEN_OFFSET ) );
		
		( (CreatePlayersScreenHandler)handler ).setView( this );
		
	}

	private JPanel getLabelPanel()
	{
		JPanel innerPanel = new JPanel();
		
		innerPanel.setBorder( BorderFactory.createRaisedBevelBorder() );

		innerPanel.add( gameNameLabel );
		innerPanel.add( gameNameField );

		innerPanel.add( numPlayersLabel );
		innerPanel.add( numPlayersBox );
		
		JPanel labelPanel = new JPanel( new BorderLayout() );
		labelPanel.add( innerPanel, BorderLayout.CENTER );
		
		return labelPanel; 
	}
	
	private JPanel getPlayersPanel()
	{
		JPanel playersPanel = new JPanel();
		playersPanel.setBorder( new CompoundBorder(
				new EmptyBorder( 0, 0, 0, 0 ),
				BorderFactory.createRaisedBevelBorder() ) ); 
		playersPanel.setLayout( new GridLayout( 6, 2, 10, 10 ) );
		
		for( int i = 0; i < playerLabels.length; i++ )
		{
			//JPanel colorPanel = new JPanel( new GridLayout( 1, 2, 20, 5 ));
			JPanel colorPanel = new JPanel( new GridBagLayout() );
						
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(0, 0, 0, 5);
			constraints.gridx = 0;
			constraints.gridy = 0; 
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1; 
			colorPanel.add( playerLabels[i], constraints );
			playerLabels[i].setPreferredSize( 
					colorButtons[i].getPreferredSize() );
			
			constraints = new GridBagConstraints();
			constraints.gridy = 0;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.gridx = 2;
			constraints.weighty = 1; 
			constraints.insets = new Insets( 0, 0, 0, 40 );
			constraints.fill = GridBagConstraints.VERTICAL;
			colorPanel.add( colorButtons[i], constraints );
			
			playersPanel.add( colorPanel );
			playersPanel.add( playerFields[i] );
		}
		
		return playersPanel;
	}
	private JPanel getMenuButtonPanel( ActionListener handler )
	{
		JPanel menuButtonPanel = new JPanel( new GridLayout( 1, 3, 15, 0 ) );
		backButton = new JButton( "Back" );
		backButton.addActionListener( handler );
		menuButtonPanel.add( new JLabel() );
		menuButtonPanel.add( backButton );

		nextButton = new JButton( "Next" );
		nextButton.addActionListener( handler );
		menuButtonPanel.add( nextButton );
		menuButtonPanel.setPreferredSize( menuButtonPanel.getPreferredSize() );
		
		return menuButtonPanel;
	}
	
	/**
	 * Collects the list of names from the GUI for creation of player 
	 * objects.  
	 * @return a List of names.  
	 */
	public List<String> getNames()
	{
		int numStrings = 
				Integer.parseInt( (String)numPlayersBox.getSelectedItem() );
				
		List<String> result = new ArrayList<String>();
		
		result.add( gameNameField.getText() ) ;
		
		for( int i = 1; i < numStrings; i++ )
		{
			result.add( playerFields[i].getText() );
		}
		
		return result; 
	}
	
	private void updateComboBox( Object obj )
	{
		int numPlayers = Integer.parseInt( (String)obj ) - 1;
		for( int i = 3; i < playerLabels.length; i++ )
		{
			boolean toShow = numPlayers >= i;
			playerLabels[i].setVisible( toShow );
			colorButtons[i].setVisible( toShow );
			playerFields[i].setVisible( toShow );
		}
	}

	/**
	 * Called by pressing the choose color button 
	 */
	public void setColor( int playerIndex )
	{
		
		/*
		JOptionPane pane = new JOptionPane( frame, 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.DEFAULT_OPTION, null, new Object[]{});
	     //pane.set.Xxxx(...); // Configure
	     JDialog dialog = pane.createDialog(null, "Choose a color");
	     dialog.setVisible( true );
	     Object selectedValue = pane.getValue();
	     System.out.println( "Got selectedValue object: " + selectedValue );
	     //If there is an array of option buttons:
	      */
		dialog.setVisible( true );
		Object selectedValue = colorPane.getValue();
		System.out.println( "Chose" + selectedValue );
	}

	/**
	 * Initializes the JComboBox and adds an anonymous item listener to update
	 * the number of players selected.
	 */
	private void initComboBox()
	{
		numPlayersBox = new JComboBox<String>( this.COMBO_BOX_ITEMS );
		numPlayersBox.addItemListener( new ItemListener()
		{
			@Override
			public void itemStateChanged( ItemEvent event )
			{
				if ( event.getStateChange() == ItemEvent.SELECTED )
				{
					updateComboBox( event.getItem() );
				}
			}
		} );

	}


	/**
	 * Initializes labels with the strings to display and default visible.  
	 */
	private void initLabelsAndButtons( ActionListener handler )
	{
		colorButtons = new JButton[MAX_PLAYERS];
		playerLabels = new JLabel[MAX_PLAYERS];
		gameNameLabel = new JLabel( "Game name: " );
		numPlayersLabel = new JLabel( "Number of players: " );
		
		for( int i = 0; i < 6; i++ )
		{
			playerLabels[i] = new JLabel( "Player " + (i+1) + ": ");
			colorButtons[i] = new JButton( "Pick a color");
			
			colorButtons[i].addActionListener( handler );
			colorButtons[i].setName( Integer.toString( i ) );
			colorButtons[i].setActionCommand( "chooseColor" );
			
			/* set default of 3 visible */
			playerLabels[i].setVisible( i < 3 );
			colorButtons[i].setVisible( i < 3 );
		}
	}
	
	/**
	 * Initializes fields with the proper length and visibility.  
	 */
	private void initFields()
	{
		playerFields = new JTextField[MAX_PLAYERS];
		gameNameField = new JTextField( TEXT_FIELD_LENGTH );
		for( int i = 0; i < playerFields.length; i++ )
		{
			playerFields[i] = new JTextField( TEXT_FIELD_LENGTH );
			
			/* set first 3 visible for default option */
			playerFields[i].setVisible( i < 3 );
		}
	}
	
	private void initColorChooser( ActionListener handler )
	{
		colorFrame = new ColorFrame( handler );
		/* blank object array replaces default buttons */
		colorPane = new JOptionPane( colorFrame, 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.DEFAULT_OPTION, null, new Object[]{});
		
		dialog = colorPane.createDialog( null, "Choose a color" );
		
	}
	
	
}