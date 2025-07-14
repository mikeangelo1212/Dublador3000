import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VenMidPanelDestino extends JPanel {
		

	final ImageIcon ICONO_PAUSA= new ImageIcon(((new ImageIcon(getClass().getResource("img/Circle-Pause.png"))).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	final ImageIcon ICONO_PLAY= new ImageIcon(((new ImageIcon(getClass().getResource("img/Circle-Play.png"))).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	final ImageIcon ICONO_GRABAR= new ImageIcon(((new ImageIcon(getClass().getResource("img/Circle-Record.png"))).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	final ImageIcon ICONO_CANCELAR= new ImageIcon(((new ImageIcon(getClass().getResource("img/Circle-Delete.png"))).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	
	
	JButton btnMicrofono = new JButton(new ImageIcon(((new ImageIcon(getClass().getResource("img/Microphone.png"))).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
	//TODO poner imagen en boton alv
    JButton btnReproducir = new JButton(ICONO_PLAY);
    JButton btnGrabar= new JButton(ICONO_GRABAR);
	
    Boolean reproduciendo=false;
    Boolean grabando=false;
    
	public VenMidPanelDestino() {
		// TODO Auto-generated constructor stub
		setBackground(Color.magenta);
		setLayout(new GridLayout(0,2,0,5));
		add(btnReproducir);
		add(btnGrabar);
		//addFuncion();
	}
	
	/**
	  * Reproduce el audio de nuestra carpeta principal
	  */
	void Reproducir() {
		System.out.println("Reproduciendo audio...");
		reproduciendo=true;
		btnReproducir.setIcon(ICONO_PAUSA);
	}
	
	/**
	  * Pausa el audio de nuestra carpeta principal
	  */
	void Pausa() {
		System.out.println("Se para reproduccion...");
		reproduciendo=false;
		btnReproducir.setIcon(ICONO_PLAY);
	}
	
	/**
	  * Empieza la grabacion
	  */
	void Grabar() {
		System.out.println("Grabando audio...");
		grabando=true;
		btnGrabar.setIcon(ICONO_CANCELAR);
	}
	
	/**
	  * Se interrumpe la grabacion
	  */
	void PararGrabacion() {
		System.out.println("Se paro la grabacion...");
		grabando=false;
		btnGrabar.setIcon(ICONO_GRABAR);
	}
}
