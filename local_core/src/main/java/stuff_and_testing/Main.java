package stuff_and_testing;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.Date;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
	
	EngineStuff enginestuff = new EngineStuff();

	// The window handle
	private long window;
	
	double fps = 60.0d;
	
	double secondsPerUpdate = 1.0d / fps;
	
	boolean run = true;
	
	private void run() {
		double startTime = enginestuff.engGetTime();
		double steps = 0.0;
		
		while (run) {
			
			double loopStartTime = enginestuff.engGetTime();
			double elapsedTime = loopStartTime - startTime;
			
			startTime = loopStartTime;
			steps += elapsedTime;
			
			handleInput();
			
			while (steps >= secondsPerUpdate) {
				updateGameState();
				steps -= secondsPerUpdate;
			}
			
			render();
			sync(loopStartTime);
			
		}
		
	}
	
	private void sync(double loopStartTime) {
		float loopSlot = 1f / 60;
		double endTime = loopStartTime + loopSlot;
		
		while (enginestuff.engGetTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {}
		}
	}
	
	private void handleInput() {
		
	}
	
	private void updateGameState() {
		
	}
	
	private void render() {
		
	}
	
	
	public static void main(String[] args) {
		
	}


}