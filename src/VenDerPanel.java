import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VenDerPanel extends JPanel {
	
	JLabel lblDirAudio;
	JLabel lblNombreArchivo;
	JLabel lblDuracion;
	JLabel lblEstatusReproduccion;

	public VenDerPanel(Color color) {
		// TODO Auto-generated constructor stub
		setBackground(color);
		setLayout(new GridLayout(4,0,0,5));
		add(lblDirAudio = new JLabel("URL: "));
		add(lblNombreArchivo = new JLabel("Archivo: "));
		add(lblDuracion = new JLabel("00:00/00:00"));
		add(lblEstatusReproduccion= new JLabel("En Pausa."));
		
	}
	
	
}
