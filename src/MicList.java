import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

//Codigo de aca
//https://www.technogumbo.com/tutorials/Java-Microphone-Selection-And-Level-Monitoring/Java-Microphone-Selection-And-Level-Monitoring.html

//TODO Ventana que te haga seleccionar micro o sacarme un dropdown del culo.
public class MicList {

	// Setup a Line.Info instance specifically of the TargetDataLine class.
    Line.Info targetDLInfo;

    // Get all the mixers from the Java AudioSystem
    Mixer.Info[] mixerInfo;
	
    public MicList() {
//        listAvailableMicrophones();
    	// Setup a Line.Info instance specifically of the TargetDataLine class.
        targetDLInfo = new Line.Info(TargetDataLine.class);

        // Get all the mixers from the Java AudioSystem
        mixerInfo = AudioSystem.getMixerInfo();
    }

    void listarMicrofonosDisponibles() {
        // Iterate through each mixer and see if it supports TargetDataLine
        for (int x = 0; x < mixerInfo.length; x++) {

            // Get a temporary instance of the current mixer and ask if
        	// it supports recording
            if (AudioSystem.getMixer(mixerInfo[x]).isLineSupported(targetDLInfo)) {
                System.out.println("Microfono encontrado: " + mixerInfo[x].getName());
            }
        }
    }
    
    void seleccionarMicrofono() {
    	
    }
}
