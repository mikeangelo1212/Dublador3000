import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class VenMicrofonos extends JFrame {
		
	//TODO: Agregar micros como botones para seleccionar.
	VenMicrofonos() throws IOException{
		setTitle("Seleccion de microfonos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/letsfkinggo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLayout(new GridLayout(0,3,5,0));
		add(new JScrollPane());
	}	


}
