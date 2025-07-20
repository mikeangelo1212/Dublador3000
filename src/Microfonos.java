import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

//Codigo de aca
//https://www.technogumbo.com/tutorials/Java-Microphone-Selection-And-Level-Monitoring/Java-Microphone-Selection-And-Level-Monitoring.html

//TODO Ventana que te haga seleccionar micro o sacarme un dropdown del culo.
public class Microfonos {

	// Setup a Line.Info instance specifically of the TargetDataLine class.
    Line.Info targetDLInfo;

    // Get all the mixers from the Java AudioSystem
    Mixer.Info[] mixerInfo;
    List<MixerCustom> mixers;
	
    public Microfonos() {
//        listAvailableMicrophones();
    	// Setup a Line.Info instance specifically of the TargetDataLine class.
        targetDLInfo = new Line.Info(TargetDataLine.class);

        // Get all the mixers from the Java AudioSystem
        mixerInfo = AudioSystem.getMixerInfo();
        mixers = new ArrayList<>();
        //refactorizar esto en lista pero que putisima hueva ajajja
    }
    

    /**
	  * Asi como dice, lista los microfonos disponibles
     * @return mixers Lista de los microfonos validos
	  */
    List<MixerCustom> listarMicrofonosDisponibles() {
        // Iterate through each mixer and see xif it supports TargetDataLine
        for (int x = 0; x < mixerInfo.length; x++) {

            // Get a temporary instance of the current mixer and ask if
        	// it supports recording
            if (AudioSystem.getMixer(mixerInfo[x]).isLineSupported(targetDLInfo)) {
                System.out.println("Microfono encontrado: " + mixerInfo[x].getName());
                mixers.add(new MixerCustom(mixerInfo[x]));
            }
        }	
        return mixers;
    }
    
    //este metodo creo que deberia ir en la ventana xd
    void seleccionarMicrofono() {
    	
    }
}
