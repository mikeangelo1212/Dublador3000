import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dubladormain {
	
	//iconos de https://github.com/prmack/16pxls
	
	// TODO Auto-generated method stub
	
	//declaracion de variables
	AudioInputStream audioInputStream;
	AudioFileFormat.Type fileType;
	AudioFormat format; 
	long frames;
	double durationInSeconds;
	int miliseconds;
	File newFile;
	File file;
	TargetDataLine microphone;
	AudioFormat newFormat;
	Thread stopper;
	
	
	//constructor de testing con valores default
	//TODO crear espectrograma de archivo de audio
	Dubladormain() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		
		String currentPath = new java.io.File(".").getCanonicalPath();
		
//		//propiedades de archivo, que tipo de archivo es, cual agarrara como base y pa donde va
//		fileType = AudioFileFormat.Type.WAVE;
//		file = new File(currentPath);
//		newFile= new File(currentPath);
//				
//		
//		//aqui decimos de donde sacara nuestro audio o propiedades, en este caso del archivo
//		audioInputStream = AudioSystem.getAudioInputStream(file);
//		format = audioInputStream.getFormat();
//		frames = audioInputStream.getFrameLength();
//		durationInSeconds = (frames+0.0) / format.getFrameRate(); 
//		miliseconds=(int)(durationInSeconds*1000);
//		
//		
//		//ahora crearemos un archivo al parecer, un formato de audio.
//		//con los siguientes parametros
//		//AudioFormat(AudioFormat.Encoding encoding, float sampleRate, int sampleSizeInBits, int channels, int frameSize, float frameRate, boolean bigEndian)
//		newFormat = new AudioFormat(format.getEncoding(), 
//		format.getSampleRate(), format.getSampleSizeInBits(), 
//		format.getChannels(), format.getFrameSize(), 
//		format.getFrameRate(), format.isBigEndian());
//
//		//Ahora crearemos una variable que capturara nuestro input
//		//getTargetDataLine agarra el microfono por defecto de nuesto Sistema Operativo		
//		microphone = AudioSystem.getTargetDataLine(newFormat);
//		
//		//este hilo lo usaremos para parar la grabacion, se llamara justo despues de abrir el microfono
//		stopper = new Thread(new Runnable() {
//          public void run() {
//              try {
//                  Thread.sleep(miliseconds+100);
//              } catch (InterruptedException ex) {
//                  ex.printStackTrace();
//              }
//              finish();
//              try {
//					imprimirDatos(newFile.getPath());
//				} catch (UnsupportedAudioFileException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//          }
//      });

	}
	
	//Bonche de codigo que se supone sacar la duracion de nuestro archivo de
			//audio
		
		//aqui declaramos el tipo de nuestro archivo, cual leemos y en cual saldra
		
		void imprimirDatos() {
		System.out.println(String.format("Formato: %s", format));
		System.out.println(String.format("Frames: %s \nFramerate: %s", frames, format.getFrameRate()));
		System.out.println(String.format("Duracion en segundos: %s Milisegundos: %s", durationInSeconds, miliseconds));
		System.out.println(String.format("===Datos para nuevo archivo==="
				+ "\nEnconding: %s, Sample Rate: %s"
				+ "\nSample Size Bits: %s, Channels: %s"
				+ "\nFrame Size: %s, Framerate: %s, Big Endian?: %s", 
				format.getEncoding().toString(), format.getSampleRate(),
				format.getSampleSizeInBits(),format.getChannels(),
				format.getFrameSize(),format.getFrameRate(),format.isBigEndian()
				));
		}
		
		void imprimirDatos(String fileURL) throws UnsupportedAudioFileException, IOException {
		file = new File(fileURL);
		audioInputStream = AudioSystem.getAudioInputStream(file);
		System.out.println(String.format("Formato: %s", format));
		System.out.println(String.format("Frames: %s \nFramerate: %s", frames, format.getFrameRate()));
		System.out.println(String.format("Duracion en segundos: %s Milisegundos: %s", durationInSeconds, miliseconds));
		System.out.println(String.format("===Datos para nuevo archivo==="
				+ "\nEnconding: %s, Sample Rate: %s"
				+ "\nSample Size Bits: %s, Channels: %s"
				+ "\nFrame Size: %s, Framerate: %s, Big Endian?: %s", 
				format.getEncoding().toString(), format.getSampleRate(),
				format.getSampleSizeInBits(),format.getChannels(),
				format.getFrameSize(),format.getFrameRate(),format.isBigEndian()
				));
		}
		
		
		
		//Estamos clonando todas las propiedades de nuestro archivo base, esto
		//nos servira a la hora de doblar archivos
		
		//Esto imprimira la lista de microfonos o inputs disponibles
		//codigo robado de internet, sepa la verga que son los mixers
		void imprimirDispositivos()
		{
			Mixer.Info[] mixers = AudioSystem.getMixerInfo();
			System.out.println("==========Dispositivos disponibles==========");
			for (Mixer.Info mixerInfo : mixers){
			    System.out.println(mixerInfo);
			}
			System.out.println("==========================");
		}
		
		
		
		
		
		
		//System.out.println(microphone.getLineInfo());
		void start(){
		 try {
			 
	            //AudioFormat format = getAudioFormat();
	            DataLine.Info info = new DataLine.Info(TargetDataLine.class, newFormat);
	 
	            // checks if system supports the data line
	            if (!AudioSystem.isLineSupported(info)) {
	                System.out.println("Line not supported");
	                System.exit(0);
	            }
	            microphone = (TargetDataLine) AudioSystem.getLine(info);
	            microphone.open(format);
	            microphone.start();   // microfono se abre :0
	            
	            
	            System.out.println("Start capturing...");
	            
	            AudioInputStream newAudioInputSteam = new AudioInputStream(microphone);
	            
	            
	            System.out.println("Start recording...");
	 
	            //graba e indicamos para donde va
	            // start recording
	            stopper.start();
	            AudioSystem.write(newAudioInputSteam, fileType, newFile);
	 
	        } catch (LineUnavailableException ex) {
	            ex.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
		 }
		 
		 
		 void finish()
		 {
			microphone.stop();
			microphone.close();
	        System.out.println("Finished");
		 }
	
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		final Dubladormain dublador = new Dubladormain();
		VentanaPrincipal ventana = new VentanaPrincipal();
		
		ventana.setVisible(true);
		
//		Thread stopper = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(dublador.miliseconds+400);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                dublador.finish();
//                try {
//					dublador.imprimirDatos(dublador.newFile.getPath());
//				} catch (UnsupportedAudioFileException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
 
		
		
//        dublador.imprimirDatos();
//        // start recording
//        
//        System.out.println(dublador.miliseconds);
//        dublador.start();
    }
		
}


