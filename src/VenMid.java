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

public class VenMid extends JPanel {

	
	//ventana de en medio hecha para 			la seleccion de carpetas y controles
	 // Declaramos nuestro arreglo que declararemos otra vez porque
 	//sepa la chingada con que jale
 	File[] archivosMain;
 	File[] archivosDestino;
 	File folderMain;
 	File folderDestino;
    JButton btnAbrirCarpeta = new JButton("Abrir carpeta");
    JButton btnCarpetaDestino = new JButton("Carpeta Destino");
    VenMidPanelMain panelMain = new VenMidPanelMain();
    VenMidPanelDestino panelDestino = new VenMidPanelDestino();
    
	
	public VenMid() {
		// TODO Auto-generated constructor stub
		setBackground(Color.magenta);
		setLayout(new GridLayout(4,0,0,5));
		add(btnAbrirCarpeta);
		add(panelMain);
		add(panelDestino);
		add(btnCarpetaDestino);
		//addFuncion();
	}
    
    //TODO: Que este metodo jale para ambos metodos
    //Nota, este metodo checara la carpeta actual, si existe la direccion te metera a
    //la direccion si es que sigue existiendo, por si algun pendejo la borra
    /**
	  * Nota, este metodo checara la carpeta actual, si existe la direccion te metera a
	  * la direccion si es que sigue existiendo, por si algun pendejo la borra
	  * @param directorioActual Direccion de carpeta, si ya existe. Sino
	  * @return Una variable File, si no es exitosa, null.
	  */
    File abrirFolder(File directorioActual) throws IOException {
    	JFileChooser f = new JFileChooser();
    	
    	//preguntamos si la carpeta existe, le mandaremos la variable ya sea la
    	//main o la destino. Y si existe le ponemos el path ese pa que lo abra
    	if(directorioActual!=null) {
    		if (Files.exists(directorioActual.toPath())) {
        		System.out.println("Directorio si existe, abriendo folder guardado anteriormente");
        		f = new JFileChooser(directorioActual);
        	}
    	}
    	
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        if(f.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
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
        else if(directorioActual!=null) {
    		if (Files.exists(directorioActual.toPath())) {
        		System.out.println("Abriendo folder guardado con anterioridad, "
        				+ "no se abrio ninguno nuevo");
        		return directorioActual;
        	}
    	}
        
        System.out.println("No se selecciono archivo"); 
    	return null;
    }
    
    //TODO: Hacer que esto jale para ambos directorios (destino y carpeta original)
    //metodos para imprimir y asignar que tiene nuestro directorio
    
    //Este metodo recorre la carpeta que se selecciono y te dice si esta duplicada o no
    /**
	  * Este metodo recorre la carpeta que se selecciona y te dice si esta duplicada o no con un boleano
	  * @param dir Direccion de la carpeta
	  * @return booleano que dice si no esta duplicado o no
	  */
     boolean recorrerCarpeta(File dir) {
    	 
    	 //preguntamos si siquiera hay archivo
    	 if (dir==null) {
    		 return false;
    	 }
    	 
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
        	  //return false;
        	  //este false yo creo estaba de mas ajjaja
    	  }
          
          //checamos cual carpeta es que vamos a actualizar
          //Esto puede tronar si usamos la misma carpeta pero eso seria muy pendejo

          
          //NOTA CHISTOSA:
//         == compara referencias (si son el mismo objeto).
//        .equals() compara valores (si el contenido es igual).
    	  //preguntamos si ya existen las variables
           if (this.folderMain!=null&&this.folderDestino!=null) {
        	  //luego preguntamos si estan duplicados
        	  //TODO: Checar porque vergas no jala a pesar de ser la misma carpeta
        	  if (this.folderMain.getAbsolutePath().equals(this.folderDestino.getAbsolutePath())) {
	        	 System.out.println("No abras la misma carpeta pendejo,nomas "
	        	 				+ "haciendo un desmadre con la logica cabron");
	        	 return false;
        	  }
        	  //sino son iguales checar a cual es igual para poder asignarla
        	  else if(dir.getAbsolutePath().equals(this.folderMain.getAbsolutePath())){
        		  System.out.println("Carpeta recorrida y asignada al folderMain");
            	  this.archivosMain=files;
            	  return true;
              }//este else if parece estar de mas, ahorita lo checamos
              else if(dir.getAbsolutePath().equals(this.folderDestino.getAbsolutePath())){
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
	
}
