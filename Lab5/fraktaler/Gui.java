package fraktaler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Gui {
	private JButton quitButton, toggleTraceButton;
	private boolean tracing = false;
	private JFrame frame;
	private static Canvas canvas;
	private JTextField previousInputField;
	private FlakeDB flakeDB;
	private Turtle turtle;
	
	public Gui() {
		flakeDB = new FlakeDB();
		makeFrame();
		turtle = new Turtle(canvas);
	}
	
	/**
	 * Create the Swing frame and its content.
	 */
	private void makeFrame() {
		frame = new JFrame("Fractal flake displayer");
		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		// Specify the layout manager with nice spacing
		contentPane.setLayout(new BorderLayout(6, 6));
		// Create the flake canvas to the right
		canvas = new Canvas(frame,Color.white);
		contentPane.add(canvas,BorderLayout.CENTER);
		contentPane.add(createInputPanel(),BorderLayout.WEST);
		frame.pack();
		
		// place the frame at the center of the screen and show
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(d.width,d.height);
		frame.setVisible(true);
		canvas.setVisible(true);
		previousInputField = null;
	}
	
	private JPanel createInputPanel() {
		// Create the toolbar with the input text fields
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(flakeDB.theFlakes.size()*3, 1, 10, 0));
		
		for ( Map.Entry<String,Flake> me : flakeDB.theFlakes.entrySet() ) {
			String name = me.getKey();
			Flake flake = me.getValue();      	
			inputPanel.add(createPanelElement(name,flake));
		}
		
		toggleTraceButton = new JButton("Trace ON");
		toggleTraceButton.setBorder(new EtchedBorder(Color.black,Color.white));
		toggleTraceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleTrace();
			}
		});
		toggleTraceButton.setEnabled(true);
		inputPanel.add(toggleTraceButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBorder(new EtchedBorder(Color.black,Color.white));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		quitButton.setEnabled(true);
		inputPanel.add(quitButton);
		
		return inputPanel;
	}
	
    private JPanel createPanelElement(String name,final Flake flake) {
        JPanel element = new JPanel();
        element.setLayout(new GridLayout(2,1,3,3));
        element.setBorder(new EtchedBorder());
        element.add(new JLabel(name));
        
        JPanel sizePart = new JPanel();
        sizePart.add(new JLabel("Size"));
        final JTextField sizeField = new JTextField(1);       
        sizeField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                // If the user pressed a digit key, obtain the numeric key value.
                int keyChar = e.getKeyChar();
                sizeField.setText(null);
                if ( keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 ) {
                    int keyValue = keyChar - KeyEvent.VK_0;
                    drawLevelsUpTo(flake,keyValue);
                }   
                // erase the digits from the last input (except if in this field)
                if ( previousInputField != null && previousInputField != sizeField )
                    previousInputField.setText(null);
                previousInputField = sizeField;
            }
        });
        
        sizePart.add(sizeField);
        element.add(sizePart);
        return element;
    }
	
	private void drawLevelsUpTo(Flake flake,int maxLevel) {
		canvas.erase();      
		int size = computeAppropriateFlakeSize();
		for ( int level = 0; level <= maxLevel; level++) {
			int x = level % 4;
			int y = level / 4 + 1;
			turtle.jumpTo(new Point(size/2 + 2*size*x, size/2 + 2*size*y));
			flake.draw(turtle,level,size);
		}
	}
	
	private int computeAppropriateFlakeSize() {
		Dimension d = canvas.getSize();
		int width = (int) d.getWidth();
		return width/8;
	}
	
	/**
	 * Toggles the tracing of the turle drawing on and off
	 */
	private void toggleTrace() {
		canvas.toggleTrace();
		tracing = ! tracing;
		toggleTraceButton.setText(tracing ? "Trace OFF" : "Trace ON");
	}
	
	/**
	 * Quit function: quit the application.
	 */
	private void quit() {
		System.exit(0);
	}
}
