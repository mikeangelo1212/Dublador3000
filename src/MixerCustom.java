import javax.sound.sampled.Mixer;

public class MixerCustom extends Mixer.Info {

	protected MixerCustom(String name, String vendor, String description, String version) {
		super(name, vendor, description, version);
		// TODO Auto-generated constructor stub
	}
	
	//a ver si esto jala
	public String ToString() {
		return this.getName();
	}

}
