import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VenMid extends JPanel {

	
	//ventana de en medio hecha para la seleccion de carpetas y controles
	 // Declaramos nuestro arreglo que declararemos otra vez porque
 	//sepa la chingada con que jale
 	File[] archivosMain;
 	File[] archivosDestino;
 	File folderMain;
 	File folderDestino;
    JButton btnAbrirCarpeta = new JButton("Abrir carpeta");
    JButton btnCarpetaDestino = new JButton("Carpeta Destino");
    
    
    //TODO: Que este metodo jale para ambos metodos
    File abrirFolder() throws IOException {
    	JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);

        System.out.println("Directorio Actual: "+f.getCurrentDirectory());
        System.out.println("Archivo Seleccionado: "+f.getSelectedFile());
        
     // Path of the specific directory 
        String directoryPath = f.getSelectedFile().toString();
        
        // Using File class create an object for specific directory
        File folder = new File(directoryPath);
        System.out.println("Folder abierto: "+folder);
        return folder;
        
        // Using listFiles method we get all the files of a directory 
        // return type of listFiles is array
    }
    
    //TODO: Hacer que esto jale para ambos directorios (destino y carpeta original)
    //metodos para imprimir y asignar que tiene nuestro directorio
    
    //Este metodo recorre la carpeta que se selecciono y te dice si esta duplicada o no
     boolean recorrerCarpeta(File dir) {
    	  File[] files = dir.listFiles();
          // Print name of the all files present in that path
          if (files != null&&files.length!=0) {
          	System.out.println("=============Archivos de la carpeta seleccionada=================");
            for (File file : files) {
              System.out.println(file.getName());
            }
            System.out.println("==================================================================");
          }
          else {
        	  System.out.println("Carpeta seleccionada esta vacia");
        	  return false;
    	  }
          
          //checamos cual carpeta es que vamos a actualizar
          //Esto puede tronar si usamos la misma carpeta pero eso seria muy pendejo
          //TODO: Checar que no seleccione la misma carpeta en los dos, para evitar pedos
//          if (this.folderMain!=null&&this.folderDestino!=null){      
//        	  
//        	  if (dir.getPath()==this.folderMain.getPath()&&dir.getPath()==this.folderDestino.getPath()) {
//	        	  //TODO: Si detecta que son iguales mandalo a la verga
//        		  System.out.println("Elige otra carpeta huevon");
//        		  return false;
//	          }
//	          
//          }
//          else 
    	  //preguntamos si ya existen las variables
          if (this.folderMain!=null&&this.folderDestino!=null) {
        	  //luego preguntamos si estan duplicados
        	  //TODO: Checar porque vergas no jala a pesar de ser la misma carpeta
        	  if (this.folderMain.getAbsolutePath()==this.folderDestino.getAbsolutePath()) {
	        	 System.out.println("No abras la misma carpeta pendejo,nomas "
	        	 				+ "haciendo un desmadre con la logica cabron");
	        	 return false;
        	  }
        	  //sino son iguales checar a cual es igual para poder asignarla
        	  else if(dir.getAbsolutePath()==this.folderMain.getAbsolutePath()){
        		  System.out.println("Carpeta recorrida y asignada al folderMain");
            	  this.archivosMain=files;
            	  return true;
              }//este else if parece estar de mas, ahorita lo checamos
              else if(dir.getAbsolutePath()==this.folderDestino.getAbsolutePath()){
            	  System.out.println("Carpeta recorrida y asignada al folderDestino");
            	  this.archivosDestino=files;
            	  return true;
              }
          }
          //sino preguntamos cual de los dos existe para asignar
          else if(this.folderMain!=null){
    		  System.out.println("Carpeta recorrida y asignada al folderMain");
        	  this.archivosMain=files;
        	  return true;
          }//este else if parece estar de mas, ahorita lo checamos
          else if(this.folderDestino!=null){
        	  System.out.println("Carpeta recorrida y asignada al folderDestino");
        	  this.archivosDestino=files;
        	  return true;
          }
		return false;
          
          
    }
    
    void compararCarpetas() {
    	//TODO: comparar ambas carpetas y ver que existan el mismo archivo en la otra
    	//o sea checar que ya se hayan grabado las lineas
    }
	
	
	
	
	
	
	public VenMid() {
		// TODO Auto-generated constructor stub
		setBackground(Color.magenta);
		setLayout(new GridLayout(4,0,0,5));
		add(btnAbrirCarpeta);
		add(new JButton("Botones"));
		add(new JButton("Botones2"));
		add(btnCarpetaDestino);
		//addFuncion();
	}
}
