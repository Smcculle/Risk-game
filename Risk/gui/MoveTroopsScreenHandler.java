/**
 * CSCI 2120 Fall 2014
 * Risk class MoveTroopsScreenHandler
 * @author Shane McCulley
 * @date Dec 5, 2014
 **/
package gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import engine.RiskGameEngine;


public class MoveTroopsScreenHandler implements ActionListener,
												ChangeListener

{

	private RiskGameEngine model;
	private MoveTroopsScreenPanel view;
	private int[] selectedIndex;

	public MoveTroopsScreenHandler( RiskGameEngine model )
	{
		this.model = model;
	}

	public void addView( MoveTroopsScreenPanel view )
	{
		this.view = view;
	}

	@Override
	public void actionPerformed( ActionEvent event )
	{
		String command = event.getActionCommand();

		if ( command == "Move" )
		{
			//TODO:  Would call player move here.  
			view.testMove();
		}
		
		else if ( command == "Min" )
		{
			view.setMinTroops();
		}
		else if ( command == "Max" )
		{
			view.setMaxTroops();
		}
		else
		{
			System.out.println( "Command of " + event.getActionCommand() );
		}
	}
	
	/**
	 * Creates an action for the textfield to verify correct input when 
	 * the specified key set in MTSP is pressed.   
	 * JFormattedTextField has a formatter factory set with the appropriate
	 * values.  
	 * @return an action to verify input on the field.  
	 */
	@SuppressWarnings( "serial" )
	public Action createVerifyTextAction()
	{
		Action textFieldAction = new AbstractAction()
		{
	            public void actionPerformed( ActionEvent event ) 
	            {
	            	JFormattedTextField textField = 
	            			(JFormattedTextField)event.getSource();
	            	
	            	System.out.println( "Its happening" );
	            	//The text is invalid.
	                if (!textField.isEditValid()) 
	                { 
	                    Toolkit.getDefaultToolkit().beep();
	                    textField.selectAll();
	                    view.handleInvalidInput();
	                } else try {                    //The text is valid,
	                    textField.commitEdit();     //so use it.
	                    
	                } catch (java.text.ParseException e ) 
	                { 
	                	System.out.println( e.getMessage() );
	                }
	            }
	    };
		
	    return textFieldAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 * 
	 * @Override public void propertyChange( PropertyChangeEvent arg0 ) {
	 * 
	 * System.out.printf( "Property change %n" + arg0.getPropertyName() +
	 * " old value of: " + arg0.getOldValue() + " new value of " +
	 * arg0.getNewValue() );
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent
	 * )
	 */
	@Override
	public void stateChanged( ChangeEvent e )
	{
		JSlider slider = (JSlider)e.getSource();
		view.setTextField( slider.getValue() );
	}

}
