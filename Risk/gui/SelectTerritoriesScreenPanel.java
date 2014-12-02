/**
 * CSCI 2120 Fall 2014
 * Risk class SelectTerritoriesScreenPanel
 * @author Shane McCulley
 * @date Dec 1, 2014
 **/
package gui;

import javax.swing.JPanel;

import classes.Player;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

import java.awt.ComponentOrientation;

import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JList;

public class SelectTerritoriesScreenPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public SelectTerritoriesScreenPanel()
	{
		setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		setLayout( new BorderLayout( 0, 0 ) );

		JLabel messageLabel = new JLabel(
				"Player currentPlayer, choose a territory from the list below." );
		messageLabel.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );
		messageLabel.setAlignmentX( Component.CENTER_ALIGNMENT );
		messageLabel.setPreferredSize( new Dimension( 70, 70 ) );
		add( messageLabel, BorderLayout.NORTH );

		JPanel nextButtonPanel = new JPanel();
		nextButtonPanel.setBorder( new EmptyBorder( 10, 0, 0, 0 ) );
		nextButtonPanel.setComponentOrientation( 
				ComponentOrientation.RIGHT_TO_LEFT );
		nextButtonPanel.setPreferredSize( new Dimension( 30, 40 ) );
		add( nextButtonPanel, BorderLayout.SOUTH );
		nextButtonPanel.setLayout( new GridLayout( 0, 3, 0, 0 ) );

		JButton nextButton = new JButton( "Next" );
		nextButton.setBorder( BorderFactory.createRaisedBevelBorder() );
		nextButton.setActionCommand( "nextButton" );
		nextButton.setAlignmentY( Component.BOTTOM_ALIGNMENT );
		nextButton.setAlignmentX( Component.RIGHT_ALIGNMENT );
		nextButtonPanel.add( nextButton );

		JPanel territoryChooserPanel = new JPanel();
		territoryChooserPanel.setBorder( 
				BorderFactory.createRaisedBevelBorder() );
		add( territoryChooserPanel, BorderLayout.CENTER );
		territoryChooserPanel.setLayout( new BorderLayout( 0, 0 ) );

		JComboBox territoryChooser = new JComboBox();
		territoryChooserPanel.add( territoryChooser, BorderLayout.NORTH );

	}

}
