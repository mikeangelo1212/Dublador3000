import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VenMidPanel2 extends JPanel {
		
	JButton btnGrabar = new JButton("Grabar");
    JButton btnReproducir = new JButton("Reproducir");
	
	
	public VenMidPanel2() {
		// TODO Auto-generated constructor stub
		setBackground(Color.magenta);
		setLayout(new GridLayout(0,2,0,5));
		add(btnReproducir);
		add(btnGrabar);
		//addFuncion();
	}
}
