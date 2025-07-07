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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

//parteizquierda de la ventana, la que tendra la listbox

public class VenIzq extends JPanel {
	
    //TODO Agregar otro JList para la carpeta destino
    public JList<String> listaArchivosMain = new JList<String>();
    
    public JList<String> listaArchivosDestino = new JList<String>();
    
    CeldasCustom miCeldasCustom;//Esta madre esta aqui porque luego le quiero poner
    							//verde por un metodo aparte
    
    
    File folderDir;
    File folderDirDestino;
    
    //a  veces se me olvida que si uno no esta dentro de un metodo no puede
    //hacer nada, bueno pero eso no me pasa en php o pyhon porque esas pendejadas
    //son como scripts lineales.
    //otra nota, estoy muy triste el dia de hoy :(
	VenIzq(){
		miCeldasCustom = new CeldasCustom();
		listaArchivosMain.setCellRenderer(miCeldasCustom);
		listaArchivosDestino.setCellRenderer(miCeldasCustom);
		
		//pa que no seleccionen mas de uno
		listaArchivosDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaArchivosMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		listaArchivosDestino.addListSelectionListener(
				
			new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					seleccionarElemento(listaArchivosDestino.getSelectedValue());
				}
		});
		
		listaArchivosMain.addListSelectionListener(
				
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						seleccionarElemento(listaArchivosMain.getSelectedValue());
					}
			});
		
		
		setBackground(Color.blue);
		setLayout(new GridLayout(2,1,0,5));
		add(new JScrollPane(listaArchivosMain));
		add(new JScrollPane(listaArchivosDestino));
	}
	
	//Nota: estas bien pendejo we, ya hiciste setup de las cosas antes, ahora solo
	//falta consumir estos metodos en main
	
	 /**
	  * Devuelve el modelo que podemos meter al JList que se necesite
	  * @param dir direccion de carpeta
	  * @param files arreglo de archivos dentro de la carpeta
	  * @return modelo que en si es los nombres de los archivos.
	  */
	 DefaultListModel<String> actualizarLista(File dir,File[] files){
		 
		 this.folderDir=dir;
		 System.out.println("Direccion de la carpeta principal actualizada: "+folderDir);
		 
		 String extension;
		 
		 DefaultListModel<String> model = new DefaultListModel<String>();
	        
		 for(File y : files){
	        	
	        	
	        	
	        	extension = y.getName();
	        	
	        	if (extension.length()>=4) {
	        		extension = extension.substring(extension.length() - 4);
	        	}
	        	//System.out.println(extension);
	        	//segunda nota, equals compara valores, pero el == compara
	        	//valor de variable en memoria, lo cual no se porque
	        	//putas serviria eso
	        	
	        	//filtramos los wav, solo queremos wavs
	        	if (extension.equals("wave") || extension.equals(".wav")) {
	        		model.addElement(y.getName());
	        	}
	            
	            
	        }    
	        System.out.println("elementos a indtroducir al JList: "+model);
	        
	        return model;
	        
	        //esto de abajo de movio a VentanaPrincipal.
	        //limpiamos JList y le metemos valores nuevos
//	        listaArchivosMain.removeAll();
//	        listaArchivosMain.setModel(model);
	        
	        
	    	
	    }
	 
	 //TODO: Al parecer hay que reestructurar esto porque no puedo checar que valores
	 //ya existen en la otra lista, como que habra que usar un diccionario (Map) de java
	 //pa poder relacionar elementos que ya existan en el otro JList, puras mamadas, aunque ajaja
	 //Se me acaba de ocurrir algo, en la clase de celdas custom le podemos mandar la lista
	 //de nuestros elementos, eso hare, que puta hueva reestructurar todo alv.
	 /**
	  * Hacemos algo con la seleccion del item modelo que podemos meter al JList que se necesite
	 * @param string 
	  */
	 void seleccionarElemento(String seleccion) {
		 System.out.println(seleccion);
	 }
	    
	
	
	
	 

}
