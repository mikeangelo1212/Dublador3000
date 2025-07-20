import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.Mixer;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class VenMicrofonos extends JFrame {
	
	JComboBox<MixerCustom> cbxMicros= new JComboBox<MixerCustom>();
	VenMicrofonosPanel venMicroPanel;
	MixerCustom[] mixers;
		
	//TODO: Agregar micros como botones para seleccionar.
	VenMicrofonos() throws IOException{
		setTitle("Seleccion de microfonos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/letsfkinggo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,200);
		setLayout(new GridLayout(2,0,10,10));
		add(cbxMicros = new JComboBox<MixerCustom>());
		add(venMicroPanel= new VenMicrofonosPanel());
		mixers = new MixerCustom[0];
		System.out.println("Constructor VenMicrofonos: " + this);
		
	}
	
	//TODO: Importante, porque vergas esto no jala
	//no se limpia la lista al parecer, y no entiendo porq
	void llenarLista(List<MixerCustom> mixerCustoms) {
		System.out.println("PUTA MADRE");
		System.out.println("LlenarLista sobre: " + this);
		cbxMicros.removeAllItems();
		revalidate();
		repaint();
		for(MixerCustom x:mixerCustoms) {
			cbxMicros.addItem(x);
		}
	}
	
	


}
