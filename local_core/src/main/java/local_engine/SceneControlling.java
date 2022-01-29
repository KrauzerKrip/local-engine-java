package local_engine;

import eng_scene.Scene;

/**
 * Scene controlling.
 * */
public class SceneControlling {
	
	private static Scene scene;
	
	public static Scene loadScene(String name) throws Exception {
		scene = new Scene(name);
		scene.loadScene();
		return scene;
	}
	
	public static Scene getScene() {
		return scene;
	}
}
