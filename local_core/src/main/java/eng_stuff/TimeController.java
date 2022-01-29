package eng_stuff;
import java.util.Date;

public class TimeController {

	    private double lastLoopTime;
	    
	    public void init() {
	        lastLoopTime = getTime();
	    }

	    public double getTime() {
	    	Date date = new Date();
	    	return date.getTime();
	    }

	    public float getElapsedTime() {
	        double time = getTime();
	        float elapsedTime = (float) (time - lastLoopTime);
	        lastLoopTime = time;
	        return elapsedTime;
	    }

	    public double getLastLoopTime() {
	        return lastLoopTime;
	    }
}
