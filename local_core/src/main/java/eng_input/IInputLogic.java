package eng_input;

import eng_graphics.Window;

public interface IInputLogic {
	void init();
	boolean getKeyPressed(Window window, String key);
	double[] getWheelOffsets(Window window);
}
