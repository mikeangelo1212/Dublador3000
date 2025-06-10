import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VenDer extends JPanel {
	
	//parte derecha incluyendo informacion de los clips

	public VenDer() {
		// TODO Auto-generated constructor stub
		setBackground(Color.orange);
		setLayout(new GridLayout(2,0,0,5));
		add(new JButton("Audio1"));
		add(new JButton("Audio2"));
	}
	
	
}
