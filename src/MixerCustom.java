import javax.sound.sampled.Mixer;

public class MixerCustom {

	Mixer.Info mixerInfo;
	
	MixerCustom(Mixer.Info info){
		mixerInfo = info;
	}
	
	//a ver si esto jala
	public String toString() {
		return mixerInfo.getName();
	}

}
