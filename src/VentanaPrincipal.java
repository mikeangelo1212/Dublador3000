import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {
	
//	TODO: Muy importante, controlar todos los botones desde fuera para poder
//	tener asi todas las acciones catalogadas desde fuera y pasar datos entre ventanas
//	Que eso debimos hacerlo desde el principio pero ajaja
//	tengo bien oxidada la logica de programacion, pobre pendejo
	VenIzq venIzq;
	VenMid venMid;
	VenDer venDer;
	//TODO: Ponerle otro icono al programa
	//Image icono="/img/Doge.jpg";
	
	VentanaPrincipal(){
		setTitle("Dublador 3000, a ver pa cuando lo acabas");
		setIconImage(getIconImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,500);
		setLayout(new GridLayout(0,3,5,0));
		
		venIzq = new VenIzq();
		venMid = new VenMid();
		venDer = new VenDer();
		add(venIzq);
		add(venMid);
		add(venDer);
		addVenMidFunciones();
	}
	
	
	void addVenMidFunciones() {
    	
		//Agregamos funciones a los botones de la ventana de en medio
		//btn de abrirCarpeta principal
		venMid.btnAbrirCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("'Abrir carpeta' pulsado");
				try {
					venMid.folderMain=venMid.abrirFolder(venMid.folderMain);
					//Este metodo recorre la carpeta que se selecciono y te dice 
					//si esta duplicada o no.
					if(venMid.recorrerCarpeta(venMid.folderMain)){
						venIzq.listaArchivosMain.removeAll();
						venIzq.listaArchivosMain.setModel(venIzq.actualizarLista(venMid.folderMain,venMid.archivosMain));
						//mete el metodo de abajo arriba pendejo
				        
						//venIzq.actualizarLista(venMid.folderMain,venMid.archivosMain);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				  
				  
			}
		});
		
		//btn de la carpeta destino
		venMid.btnCarpetaDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("'Abrir carpeta destino' pulsado");
				try {
					venMid.folderDestino=venMid.abrirFolder(venMid.folderDestino);
					System.out.println("Folder de destino abierto: "+venMid.folderDestino);
					if(venMid.recorrerCarpeta(venMid.folderDestino)){
						venIzq.listaArchivosDestino.removeAll();
						venIzq.listaArchivosDestino.setModel(venIzq.actualizarLista(venMid.folderDestino,venMid.archivosDestino));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				  //venIzq.actualizarLista(venMid.archivos,venMid.folderDir);
			}
		});
    }
	


}
