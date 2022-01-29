package local_engine;

import eng_graphics.Loop;

public class Time {
	
	public Loop glfwctrlr;
	
	public Time() throws Exception {
		glfwctrlr = Loop.getInstance();
	}
	
	public float deltaTime() { 
		return glfwctrlr.engGetElapsedTime() / 1000.0f;
	}
}
