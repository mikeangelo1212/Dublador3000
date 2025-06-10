import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

//parteizquierda de la ventana, la que tendra la listbox

public class VenIzq extends JPanel {
	
    //TODO Agregar otro JList para la carpeta destino
    public JList<File> listaArchivosMain = new JList<File>();
    File folderDir;
   
	VenIzq(){
		setBackground(Color.blue);
		setLayout(new GridLayout(2,1));
		add(listaArchivosMain);
	}
	
	//TODO: Ponerle que reciba la JList para que pueda actualizar
	//cualquiera de los dos JList
	//Metodo para actualizar JList
	 void actualizarLista(File dir,File[] files){
	    
		 
		 this.folderDir=dir;
		 System.out.println("Direccion de la carpeta actualizada: "+folderDir);
		 
		 DefaultListModel<File> model = new DefaultListModel<File>();
	        for(File y : files){
	             model.addElement(y);
	        }    
	        System.out.println("elementos a indtroducir al JList: "+model);
	        //limpiamos JList y le metemos valores nuevos
	        listaArchivosMain.removeAll();
	        listaArchivosMain.setModel(model);
	        
	        
	    	
	    }
	    
	
	
	
	 

}
