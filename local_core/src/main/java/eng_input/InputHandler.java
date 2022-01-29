package eng_input;

import static org.lwjgl.glfw.GLFW.*;

import eng_console.Console;
import eng_graphics.Window;

public class InputHandler implements IInputLogic{
	
	/*
	 * Separate handler is needed for the API.
	 * */
	
	@Override
	public void init( ) {
		Console.write("InputHandler: initialized.");
	}
	
	@Override
	public boolean getKeyPressed(Window window, String keyboard_key) {
		switch (keyboard_key) {
			case ("ARROW_UP"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_UP);
			case ("ARROW_DOWN"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_DOWN);
			case ("ARROW_LEFT"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_LEFT);
			case ("ARROW_RIGHT"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_RIGHT);
			case ("SPACE"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_SPACE);
			case ("W"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_W);
			case ("A"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_A);
			case ("S"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_S);
			case ("D"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_D);
			case ("T"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_T);
			case ("G"):
				return window.engGlfwIsKeyPressed(GLFW_KEY_G);
			
			default:
				Console.warn("InputHandler: unknown key code: " + keyboard_key);
				return false;
		}
	}
	
	public double[] getWheelOffsets(Window window) {
		double[] offsets = {window.engGlfwGetWheelOffsetX(), window.engGlfwGetWheelOffsetY()};
		return offsets;
	}
}

//@Override
//public void input(Window window) {
//    if ( window.isKeyPressed(GLFW_KEY_UP) ) {
//        direction = 1;
//    } else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
//        direction = -1;
//    } else {
//        direction = 0;
//    }
//}