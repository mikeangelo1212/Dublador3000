import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VenMicrofonosPanel extends JPanel{
	
	JButton btnAceptar = new JButton("Seleccionar");
	JButton btnCancelar = new JButton("Cancelar");
	
	VenMicrofonosPanel(){
		setLayout(new GridLayout(0,2,10,0));
		add(btnAceptar);
		add(btnCancelar);
	}
}
