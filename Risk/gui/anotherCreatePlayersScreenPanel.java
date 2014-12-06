/**
 * CSCI 2120 Fall 2014
 * Risk class anotherCreatePlayersScreenPanel
 * @author Shane McCulley
 * @date Nov 30, 2014
 **/
package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;

public class anotherCreatePlayersScreenPanel extends JPanel
{

	private final String[] COMBO_BOX_ITEMS = { "3", "4", "5", "6" };
	private final int TEXT_FIELD_LENGTH = 20;
	private ActionListener handler;

	private JLabel gameNameLabel;
	private JTextField gameNameField;

	private JLabel numPlayersLabel;
	private JComboBox<String> numPlayersBox;

	private JLabel player1Label;
	private JLabel player1Label_1;
	private JTextField player1Field;

	private JLabel player2Label;
	private JTextField player2Field;

	private JLabel player3Label;
	private JTextField player3Field;

	private JLabel player4Label;
	private JTextField player4Field;

	private JLabel player5Label;
	private JTextField player5Field;

	private JLabel player6Label;
	private JTextField player6Field;

	JPanel menuButtonPanel;
	private JButton backButton;
	private JButton nextButton;
	private Component horizontalStrut;

	public anotherCreatePlayersScreenPanel( ActionListener handler )
	{
		// TODO Auto-generated constructor stub
		// super( new CardLayout() );
		this.handler = handler;

		/*
		 * CardLayoutDemo demo = new CardLayoutDemo();
		 * demo.addComponentToPane();
		 */
		//this.setLayout( new GridLayout( 11, 2, 10, 10 ) );
		this.setLayout( new BorderLayout(0, 15) );
		/* initialize JLabels and JTextFields */
		initLabels();
		initFields();
		initComboBox();
		
		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		topPanel.setBorder( BorderFactory.createRaisedBevelBorder() );
	
		topPanel.add( gameNameLabel );
		topPanel.add( gameNameField );
		horizontalStrut = Box.createHorizontalStrut(15);
		topPanel.add(horizontalStrut);

		topPanel.add( numPlayersLabel );
		topPanel.add( numPlayersBox );
		JPanel outP = new JPanel( new BorderLayout() );
		outP.add( topPanel, BorderLayout.CENTER );
		//this.add( new JLabel() );
		//this.add( new JLabel() );
		//topPanel.setPreferredSize( topPanel.getPreferredSize() );
		//topPanel.setMinimumSize( topPanel.getPreferredSize() );
		this.add( outP, BorderLayout.NORTH );
		
		JPanel playersPanel = new JPanel();
		playersPanel.setBorder( new CompoundBorder(
				new EmptyBorder( 10, 0, 10, 0 ), 
				BorderFactory.createRaisedBevelBorder() ) );
		playersPanel.setLayout( new GridLayout( 6, 2, 10, 10 ) );
		
		GridBagLayout gbl_gridPanel = new GridBagLayout();
		JPanel gridPanel = new JPanel( gbl_gridPanel );
		//gridPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JButton color = new JButton( "Pick a color" );
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 5);
		c.gridx = 0;
		c.gridy = 0; 
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1; 
		//playersPanel.add( player1Label );
		
		//player1Label = new JLabel(new javax.swing.ImageIcon(getClass().getClassLoader().getResource(s)));
		
		player1Label_1 = new JLabel( "Player 1: ");
		player1Label_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		player1Label_1.setPreferredSize(new Dimension(87, 23));
		player1Label_1.setHorizontalTextPosition(SwingConstants.LEFT);
		player1Label_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		player1Label.setPreferredSize( color.getPreferredSize() );
		gridPanel.add( player1Label, c);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.CENTER;
		c2.gridx = 2;
		c2.weighty = 1; 
		c2.insets = new Insets( 0, 0, 0, 80 );
		gridPanel.add( color, c2 );
		
		playersPanel.add( gridPanel );
		playersPanel.add( player1Field );

		playersPanel.add( player2Label );
		playersPanel.add( player2Field );

		playersPanel.add( player3Label );
		playersPanel.add( player3Field );

		playersPanel.add( player4Label );
		playersPanel.add( player4Field );

		playersPanel.add( player5Label );
		playersPanel.add( player5Field );

		playersPanel.add( player6Label );
		playersPanel.add( player6Field );
		//playersPanel.setPreferredSize( playersPanel.getPreferredSize() );
		this.add( playersPanel, BorderLayout.CENTER );
		//this.add( new JLabel() );
		//this.add( new JLabel() );
		//this.add( new JLabel() );

		menuButtonPanel = new JPanel( new GridLayout( 1, 3, 15, 0 ) );
		backButton = new JButton( "Back" );
		backButton.addActionListener( handler );
		menuButtonPanel.add( new JLabel() );
		menuButtonPanel.add( backButton );
		
		nextButton = new JButton( "Next" );
		nextButton.addActionListener( handler );
		menuButtonPanel.add( nextButton );
		menuButtonPanel.setPreferredSize( menuButtonPanel.getPreferredSize() );
		this.add( menuButtonPanel, BorderLayout.SOUTH );
		this.setSize( 640, 380 );
		this.setBorder( new EmptyBorder(10, 10, 10, 10) );
	}

	/**
	 * Initializes the JComboBox and adds an anonymous item listener to update
	 * the number of players selected.
	 */
	private void initComboBox()
	{
		numPlayersBox = new JComboBox<String>();
		for( String s : this.COMBO_BOX_ITEMS )
			numPlayersBox.addItem( s );
		
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

	private void updateComboBox( Object obj )
	{
		int numPlayers = Integer.parseInt( (String)obj );
		player4Label.setVisible( numPlayers >= 4 );
		player5Label.setVisible( numPlayers >= 5 );
		player6Label.setVisible( numPlayers >= 6 );

		player4Field.setVisible( numPlayers >= 4 );
		player5Field.setVisible( numPlayers >= 5 );
		player6Field.setVisible( numPlayers >= 6 );

	}

	/**
	 * Initializes labels with the strings to display.
	 */
	private void initLabels()
	{
		Border lineB = BorderFactory.createLineBorder( Color.BLACK );
		gameNameLabel = new JLabel( "Game name: " );
		gameNameLabel.setBorder( lineB );
		numPlayersLabel = new JLabel( "Number of players: " );
		numPlayersLabel.setBorder( lineB );
		player1Label = new JLabel( "Player 1: " );
		player1Label.setBorder( lineB );
		
		String s = "images/cannon.png";
		
		player2Label = new JLabel( "Player 2: " );
		player2Label.setBorder( lineB );
		
		player3Label = new JLabel( "Player 3: " );
		player3Label.setBorder( lineB );
		
		player4Label = new JLabel( "Player 4: " );
		player4Label.setBorder( lineB );
		player5Label = new JLabel( "Player 5: " );
		player5Label.setBorder( lineB );
		player6Label = new JLabel( "Player 6: " );
		player6Label.setBorder( lineB );

		/* default option of 3 players on JComboBox */

		player4Label.setVisible( false );
		player5Label.setVisible( false );
		player6Label.setVisible( false );
	}

	private void initFields()
	{

		gameNameField = new JTextField( TEXT_FIELD_LENGTH );
		player1Field = new JTextField( TEXT_FIELD_LENGTH );
		player2Field = new JTextField( TEXT_FIELD_LENGTH );
		player3Field = new JTextField( TEXT_FIELD_LENGTH );
		player4Field = new JTextField( TEXT_FIELD_LENGTH );
		player5Field = new JTextField( TEXT_FIELD_LENGTH );
		player6Field = new JTextField( TEXT_FIELD_LENGTH );
	
		/* default option of 3 players on JComboBox */
		player4Field.setVisible( false );
		player5Field.setVisible( false );
		player6Field.setVisible( false );
	}

	public static void main( String[] args )
	{
		System.out.println( "Tested it " );
		CreatePlayersScreenHandler handler = new CreatePlayersScreenHandler(
				null );
		anotherCreatePlayersScreenPanel sp = new anotherCreatePlayersScreenPanel( handler );
		sp.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		JFrame frame = new JFrame( "Test frame" );

		JPanel spj = testGridBag();
		
		frame.getContentPane().add( spj );
		//frame.pack();
		frame.setVisible( true );
		
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Dimension minSize = new Dimension(640, 380);
		
		frame.setPreferredSize( minSize );
		frame.setMinimumSize( minSize );
		frame.setLocationRelativeTo( null );
		
	}

	private static JPanel testGridBag()
	{
		anotherCreatePlayersScreenPanel sp = new anotherCreatePlayersScreenPanel(
				new CreatePlayersScreenHandler( null ) );

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1; 
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.insets =  new Insets( 0, 10, 10, 10 );
		JPanel thisz = new JPanel( new GridBagLayout() );
		
		thisz.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
	
		// first column components 
		c.anchor = GridBagConstraints.EAST;
		thisz.add( sp.gameNameLabel, c);
		c.gridx = 1; 
		thisz.add( new JLabel(), c);
		c.gridx = 0; 
		c.gridy = 1;
		c.insets = new Insets( 5, 10, 5, 10);
		thisz.add( sp.numPlayersLabel, c);
		
		c.gridy = 3;
		c.weightx = 0.05;
		c.weighty = 0.05;
		thisz.add( sp.player1Label_1, c);
		
		c.gridy++;
		thisz.add( sp.player2Label, c);
		
		c.gridy++;
		thisz.add( sp.player3Label, c);
		
		c.gridy++;
		thisz.add( sp.player4Label, c);
		
		c.gridy++;
		thisz.add( sp.player5Label, c);
		
		c.gridy++;
		thisz.add( sp.player6Label, c);
		
		//Second column components 
		c.gridy = 0;
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3; 
		thisz.add( sp.gameNameField, c);
	
		c.gridy++;
		thisz.add( sp.numPlayersBox, c);
		
		c.gridy += 2; 
		thisz.add( sp.player1Field, c);
		
		c.gridy++;
		thisz.add( sp.player2Field, c);

		c.gridy++;
		thisz.add( sp.player3Field, c);

		c.gridy++;
		thisz.add( sp.player4Field, c);

		c.gridy++;
		thisz.add( sp.player5Field, c);

		c.gridy++;
		thisz.add( sp.player6Field, c);

		c.gridx = 2; 
		c.gridy = 10;
		c.gridwidth = 2; 
		c.anchor = GridBagConstraints.PAGE_END;
		sp.menuButtonPanel = new JPanel( new GridLayout( 1, 3, 20, 0 ) );
		sp.backButton = new JButton( "Back" );
		sp.backButton.addActionListener( sp.handler );
		sp.menuButtonPanel.add( new JLabel() );
		sp.menuButtonPanel.add( sp.backButton );
		
		sp.nextButton = new JButton( "Next" );
		sp.nextButton.addActionListener( sp.handler );
		sp.menuButtonPanel.add( sp.nextButton );

		thisz.add( sp.menuButtonPanel, c);
		
		
		return thisz;
		
	}

}