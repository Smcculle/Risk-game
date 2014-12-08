package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings( "serial" )
public class MapInternalFrameTest extends JInternalFrame
{
	private JDesktopPane desktopPane;
	private JPanel containingPanel;
	private JButton button;
	private int disp = 10; 
	
	public MapInternalFrameTest()
	{
		containingPanel = new JPanel();
		containingPanel.setBorder( new EmptyBorder( 2, 3, 2, 3 ) );
		containingPanel.setPreferredSize( new Dimension( 640, 480 ) );
		
		desktopPane = new JDesktopPane();
		containingPanel.add( desktopPane, BorderLayout.CENTER );
		
		button = new JButton( "Toggle" );
		
		button.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				toggleButton();
				
			}
		});
		
		containingPanel.add( button, BorderLayout.PAGE_START );
		
		desktopPane.add( this );
		this.setLocation( 200, 200 );
		this.setSize( 100, 100 );
		this.setVisible( true );
	}
	

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension( 100, 100 );
	}
	
	public JPanel getPanel()
	{
		return this.containingPanel;
	}
	

	/**
	 * 
	 */
	protected void toggleButton()
	{
		this.setVisible( !this.isVisible() );		
		JInternalFrame jif = new JInternalFrame();
        desktopPane.add(jif);
        jif.setLocation(disp, disp);
        jif.setSize(100,100); // VERY important!
        disp += 10;
        jif.setVisible(true);
		
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

	protected static void createAndShowGUI()
	{
		
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		MapInternalFrameTest map = new MapInternalFrameTest();
		
		JDesktopPane dtp = new JDesktopPane();
		jf.getContentPane().add( dtp );
		dtp.add(  map );
		
		jf.pack();
		jf.setVisible( true );
	}
	
	
	   

	 
	public void optionalVersion() 
        {
            // the GUI as seen by the user (without frame)
            JPanel gui = new JPanel(new BorderLayout());
            gui.setBorder(new EmptyBorder(2, 3, 2, 3));

            gui.setPreferredSize(new Dimension(400, 100));
            gui.setBackground(java.awt.Color.WHITE);

            final JDesktopPane dtp = new JDesktopPane();
            gui.add(dtp, BorderLayout.CENTER);

            JButton newFrame = new JButton("Add Frame");

            ActionListener listener = new ActionListener() {
            private int disp = 10;
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame jif = new JInternalFrame();
                dtp.add(jif);
                jif.setLocation(disp, disp);
                jif.setSize(100,100); // VERY important!
                disp += 10;
                jif.setVisible(true);
            }
            };
            
            newFrame.addActionListener(listener);

            gui.add(newFrame, BorderLayout.PAGE_START);

            JFrame f = new JFrame("Demo");
            f.getContentPane().add(gui);
            // Ensures JVM closes after frame(s) closed and
            // all non-daemon threads are finished
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // See http://stackoverflow.com/a/7143398/418556 for demo.
            f.setLocationByPlatform(true);

            // ensures the frame is the minimum size it needs to be
            // in order display the components within it
            f.pack();
            // should be done last, to avoid flickering, moving,
            // resizing artifacts.
            f.setVisible(true);
        }
	           
}


