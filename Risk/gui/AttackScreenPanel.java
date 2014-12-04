/**
 * CSCI 2120 Fall 2014
 * Risk class AttackScreenPanel
 * @author Shane McCulley
 * @date Dec 4, 2014
 **/
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import classes.Territory;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.BevelBorder;

@SuppressWarnings( "serial" )
public class AttackScreenPanel extends JPanel
{
	private static final int MAX_ATTACK_DICE = 3; 
	private static final int MAX_DEFEND_DICE = 2;
	private static final int TOGGLE_BUTTON_SIZE = 30;
	
	/* north panel description */
	private JPanel descriptionPanel;
	private JLabel descriptionLabel, instructionLabel;
	
	/* center west labels */
	private JPanel labelPanel; 
	private JLabel attackerLabel, defenderLabel;
	
	/* center east buttons */
	private JPanel dicePanel;
	private List<JToggleButton> attackDiceGroup;
	private JToggleButton[] attackDice; 
	private List<JToggleButton> defendDiceGroup;
	private JToggleButton[] defendDice;
	
	/* south menu options */
	private JPanel menuPanel; 
	private JButton attackButton, backButton;
	
	/* defender, attacker information */
	private Territory attacker;
	private Territory defender; 
	
	/**
	 * Create the panel.
	 */
	public AttackScreenPanel()
	{
		dummyTerritory();
		descriptionPanel = getDescriptionPanel();
		this.setLayout( new BorderLayout(10, 10) );
		this.add( descriptionPanel, BorderLayout.NORTH );
		
		labelPanel = getLabelPanel();
		this.add( labelPanel, BorderLayout.WEST );
		
		dicePanel = getDicePanel();
		this.add( dicePanel, BorderLayout.EAST );
		
		menuPanel = getMenuPanel();
		this.add( menuPanel, BorderLayout.SOUTH );
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
	}
	
	//TODO erase
	private void dummyTerritory()
	{
		attacker = new Territory( "Western United States", null );
		defender = new Territory( "Eastern United States", null );
		attacker.setNumArmies( 16 );
		defender.setNumArmies( 11 );
	}
	
	/**
	 * @param attacker2
	 * @param defender2
	 */
	
	//TODO clean constructor 
	public AttackScreenPanel( Territory attacker, Territory defender )
	{
		this.attacker = attacker;
		this.defender = defender; 
		
		descriptionPanel = getDescriptionPanel();
		this.setLayout( new BorderLayout() );
		this.add( descriptionPanel, BorderLayout.NORTH );
		
		labelPanel = getLabelPanel();
		this.add( labelPanel, BorderLayout.WEST );
		
		dicePanel = getDicePanel();
		this.add( dicePanel, BorderLayout.EAST );
		
		menuPanel = getMenuPanel();
		this.add( menuPanel, BorderLayout.SOUTH );
	}

	/**
	 * @return the description panel for the top of the panel 
	 */
	private JPanel getDescriptionPanel()
	{
		JPanel result = new JPanel( new BorderLayout() );
		descriptionLabel = new JLabel(
				"<html>"
				+ "<font size = \"4\">Attacking from "
				+ "<font size = \"5\">" + attacker.getName() 
				+ "<font size = \"4\">" + " to "
				+ "<font size = \"5\">" + defender.getName() 
				+ "</html>");
		descriptionLabel.setBorder( 
				BorderFactory.createBevelBorder(BevelBorder.LOWERED ) );
		
		result.add( descriptionLabel, BorderLayout.WEST );
		
		return result; 
	}
	
	/**
	 * @return the label panel for center west.  
	 */
	private JPanel getLabelPanel()
	{
		JPanel result = new JPanel( new GridLayout(2, 1) );
		attackerLabel = new JLabel();
		defenderLabel = new JLabel();
		
		result.add( attackerLabel );
		result.add( defenderLabel );
		setLabelText();
		
		return result;
	}
	
	private void setLabelText()
	{
		attackerLabel.setText( "<html>Attacker:  " + attacker.getNumArmies() 
				+ " troops<br>"
				+ "Player: " + attacker.getOccupant() + "</html>");
		defenderLabel.setText( "<html>Defender: " + defender.getNumArmies() 
				+ " troops<br>"
				+ "Player: " + defender.getOccupant() + "</html>");
	}
	
	private JPanel getDicePanel()
	{
		attackDice = new JToggleButton[ MAX_ATTACK_DICE ];
		defendDice = new JToggleButton[ MAX_DEFEND_DICE ];
		JPanel result = new JPanel( new BorderLayout() );
		JPanel attackPanel = new JPanel();
		JPanel defendPanel = new JPanel( 
				new FlowLayout( FlowLayout.LEFT ));
		
		attackDiceGroup = new ArrayList<JToggleButton>( MAX_ATTACK_DICE );
		defendDiceGroup = new ArrayList<JToggleButton>( MAX_DEFEND_DICE );
		
		for( int i = 0; i < MAX_ATTACK_DICE; i++ )
		{
			attackDice[i] = new JToggleButton();
			//attackDice[i].setPreferredSize( new Dimension
			//		( TOGGLE_BUTTON_SIZE, TOGGLE_BUTTON_SIZE ) );
			attackDice[i].setText( Integer.toString( i + 1 ) );
			attackDiceGroup.add( attackDice[i] );
			attackPanel.add( attackDice[i] );
			
			/* set the last one active */
			if( i == MAX_ATTACK_DICE - 1 )
				attackDice[i].setSelected( true );
			
		}
		attackDice[1].setSelected( true );
		for( int i = 0; i < MAX_DEFEND_DICE; i++ )
		{
			defendDice[i] = new JToggleButton();
			//defendDice[i].setPreferredSize( new Dimension
			//		( TOGGLE_BUTTON_SIZE, TOGGLE_BUTTON_SIZE ) );
			defendDice[i].setText( Integer.toString( i + 5 ) );
			defendDiceGroup.add( defendDice[i] );
			defendPanel.add( defendDice[i] );
			
			/* set the last one active */
			if( i == MAX_DEFEND_DICE - 1)
				defendDice[i].setSelected( true );
		}
		
		result.add( attackPanel, BorderLayout.CENTER );
		result.add( defendPanel, BorderLayout.SOUTH);
		
		instructionLabel = new JLabel( "Dice to roll" );
		instructionLabel.setHorizontalAlignment( JLabel.RIGHT );
		result.add( instructionLabel, BorderLayout.NORTH );
		
		return result; 
		
	}
	private JPanel getMenuPanel()
	{
		JPanel result = new JPanel();
		attackButton = new JButton( "Attack" );
		backButton = new JButton( "Quit" );
		
		result.add( attackButton );
		result.add( backButton );
		backButton.setPreferredSize( attackButton.getPreferredSize() );
		return result; 
	}
	
	//TODO : remove 
	/** testing***********************/
	public void setAttacker( Territory attacker )
	{
		this.attacker = attacker;
	}
	public void setDefender( Territory defender )
	{
		this.defender = defender; 
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
		Territory attacker = new Territory("Attackistan", null );
		attacker.setNumArmies( 15 );
		Territory defender = new Territory("Defendaria", null );
		defender.setNumArmies( 10 );
		
		AttackScreenPanel asp = new AttackScreenPanel( attacker, defender );
		jf.getContentPane().add( asp );
		jf.pack();
		jf.setVisible( true );
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	

}
