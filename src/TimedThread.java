
public class TimedThread implements Runnable{

	private Integer miliseconds;

    public Integer getMiliseconds() { return this.miliseconds; }
    public void setMiliseconds(Integer miliseconds) { this.miliseconds = miliseconds; }

	
	TimedThread(){
		miliseconds=0;
	}
	
	TimedThread(int miliseconds){
		this.miliseconds=miliseconds;
	}
	
	public void run() {
		try {Thread.sleep(1000);} catch(Exception e){System.out.print(e);}
		}
	}
	
