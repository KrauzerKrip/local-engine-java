package eng_stuff;

import java.net.URL;

import game.MainGame;

public final class FilePaths {
	
	private final static URL PARAMETERS_PATH = MainGame.class.getResource("parameters/"); 
	private final static URL OBJECTS_PATH = MainGame.class.getResource("objects/");
	private final static URL SCENES_PATH = MainGame.class.getResource("scenes/");

	/**
	 * @return the PARAMETERS_PATH.
	 */
	public final static URL getParametersPath() {
		return PARAMETERS_PATH;
	}

	/**
	 * @return the OBJECTS_PATH
	 */
	public final static URL getObjectsPath() {
		return OBJECTS_PATH;
	}

	/**
	 * @return the SCENES_PATH
	 */
	public final static URL getScenesPath() {
		return SCENES_PATH;
	}
}
