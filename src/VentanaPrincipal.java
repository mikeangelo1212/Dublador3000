import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaPrincipal extends JFrame {
	
//	TODO: Muy importante, controlar todos los botones desde fuera para poder
//	tener asi todas las acciones catalogadas desde fuera y pasar datos entre ventanas
//	Que eso debimos hacerlo desde el principio pero ajaja
//	tengo bien oxidada la logica de programacion, pobre pendejo
	VenIzq venIzq;
	VenMid venMid;
	VenDer venDer;
	VenMicrofonos venMicro;
	
	MicList microfonos = new MicList();
	
	
	
	VentanaPrincipal() throws IOException{
		setTitle("Dublador 3000, a ver pa cuando lo acabas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/letsfkinggo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,500);
		setLayout(new GridLayout(0,3,5,0));
		
		venIzq = new VenIzq();
		venMid = new VenMid();
		venDer = new VenDer();
		venMicro = new VenMicrofonos();
		add(venIzq);
		add(venMid);
		add(venDer);
		addVenIzqFunciones();
		addVenMidFunciones();
	}
	
	/**
	  * Metodo en el constructor que agrega las funciones de la parte
	  * izquierda de la ventana principal
	  */
	void addVenIzqFunciones() {
		venIzq.listaArchivosDestino.addListSelectionListener(
				
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						//el !e.getValueIsAdjusting es para que espere a que el metodo
						//se acabe y haga esto solo una vez
						if(!e.getValueIsAdjusting()){
							 venIzq.seleccionarElemento(venIzq.listaArchivosDestino.getSelectedValue());
					       }
					}
			});
			
		venIzq.listaArchivosMain.addListSelectionListener(
					
					new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							 if(!e.getValueIsAdjusting()){
								 venIzq.seleccionarElemento(venIzq.listaArchivosMain.getSelectedValue());
						       }
							
						}
				});

	}
	
	/**
	  * Renderiza de nuevo ambas listas, quita elementos y asigna modelos
	  * @param xd sexo
	  */
	 void reRenderizarListas() {
		 System.out.println("folderMain: " + venMid.folderMain);
		 System.out.println("folderDestino: " + venMid.folderDestino);
		 System.out.println("archivosMain: " + venMid.archivosMain);
		 System.out.println("archivosDestino: " + venMid.archivosDestino);
		 Boolean main =renderizarMain();
		 Boolean destino = renderizarDestino();
		 System.out.println("Renderizando listas...");
		 if (main) {
			 venIzq.listaArchivosMain.setModel(venIzq.actualizarLista(venMid.folderMain,venMid.archivosMain));
		 }
		 if (destino) {
			 venIzq.listaArchivosDestino.setModel(venIzq.actualizarLista(venMid.folderDestino,venMid.archivosDestino));
		 }
	 }
	 
	 /**
	  * Renderiza el listbox de la carpeta principal.
	  * @return boolean Si es que al recorrer nuestra carpeta la encontro valida
	  */
	 boolean renderizarMain() {
		 System.out.println("Trabajando en carpeta principal...");
		 if(venMid.recorrerCarpeta(venMid.folderMain)){
				venIzq.listaArchivosMain.removeAll();
				venIzq.miCeldasCustom.listaArchivosMain=venIzq.actualizarLista(venMid.folderMain,venMid.archivosMain);
				System.out.println("Finalizando en carpeta principal...");
				return true;
			}
		 else {
			 System.out.println("=Carpeta principal no valida=");
			 return false;
		 }
	 }
	 
	 /**
	  * Renderiza el listbox de la carpeta destino.
	  * @return boolean Si es que al recorrer nuestra carpeta la encontro valida
	  */
	 boolean renderizarDestino() {
		 System.out.println("Trabajando en carpeta destino...");
		 if(venMid.recorrerCarpeta(venMid.folderDestino)){
				venIzq.listaArchivosDestino.removeAll();
				venIzq.miCeldasCustom.listaArchivosDestino=venIzq.actualizarLista(venMid.folderDestino,venMid.archivosDestino);
				System.out.println("Finalizanco en carpeta destino...");
				return true;
			}
		 else {
			 System.out.println("=Carpeta destino no valida=");
			 return false;
		 }
	 }
	
	 /**
	  * Metodo en el constructor que agrega las funciones de la parte
	  * de en medio de la ventana principal
	  */
	void addVenMidFunciones() {
    	
		//Agregamos funciones a los botones de la ventana de en medio
		//btn de abrirCarpeta principal
		venMid.btnAbrirCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("'Abrir carpeta' pulsado");
				try {
					venMid.folderMain=venMid.abrirFolder(venMid.folderMain);
					System.out.println("Folder de principal abierto: "+venMid.folderMain);
					//Este metodo recorre la carpeta que se selecciono y te dice 
					//si esta duplicada o no.
					
					reRenderizarListas();
					
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
					reRenderizarListas();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				  //venIzq.actualizarLista(venMid.archivos,venMid.folderDir);
			}
		});
		
		
		//Panel Principal
		venMid.panelMain.btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!venMid.panelMain.reproduciendo) {
					venMid.panelMain.Reproducir();
				}
				else {
					venMid.panelMain.Pausa();
				}
			}
		});
		
		//Boton microfono
		venMid.panelMain.btnMicrofono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//meter esta madre en venMicro
				microfonos.listarMicrofonosDisponibles();
			}
		});
		
		//Panel Destino
		venMid.panelDestino.btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!venMid.panelDestino.reproduciendo) {
					venMid.panelDestino.Reproducir();
				}
				else {
					venMid.panelDestino.Pausa();
				}
			}
		});
		
		venMid.panelDestino.btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!venMid.panelDestino.grabando) {
					venMid.panelDestino.Grabar();
				}
				else {
					venMid.panelDestino.PararGrabacion();
				}
			}
		});
    }
	


}
