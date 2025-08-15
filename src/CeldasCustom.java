
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class CeldasCustom extends JLabel implements ListCellRenderer<String> {
 
	public DefaultListModel<String> listaArchivosMain;
    
    public DefaultListModel<String> listaArchivosDestino;
	
    public CeldasCustom() {
    	listaArchivosMain = new DefaultListModel<String>();
    	listaArchivosDestino = new DefaultListModel<String>();
        setOpaque(true);
    }
    
    //TODO: Tener copias locales de las listbox y luego preguntar abajo
    //(Que es donde se agregan) si existen entre los dos para darles
    //formato custom y marcarlos de verde o rojo o lo que quieras :p
 
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String elemento, int index,
            boolean isSelected, boolean cellHasFocus) {
 
    	//xd
    	//esto basicamente pregunta que si nuestra chingadera existe en ambas, se pinte verde
    	//pero ahora no se porq vergas se dejo de pintar los dos
    	setText(elemento);
    	if(listaArchivosDestino.contains(elemento)&&listaArchivosMain.contains(elemento)) {
    		if (isSelected) {
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.GREEN);
            }
    	}
    	else {
	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }
        }
 
        return this;
    }
} 