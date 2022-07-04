package local_engine;

import eng_file_io.IResources;
import eng_scene.Scene;

/**
 * Scene controlling.
 * */
public class SceneControlling {
	
	private static Scene scene;
	private static IResources iResources;
	
	private SceneControlling() {
		
	}
	
	public static Scene loadScene(String name) throws Exception {
		scene = new Scene(name, getResourcesClass());
		scene.loadScene();
		return scene;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setResourcesClass(IResources iResources) {
		SceneControlling.iResources = iResources;
	}
	
	private static IResources getResourcesClass() throws Exception {
		if (SceneControlling.iResources != null) {
			return SceneControlling.iResources;
		} else {
			throw new Exception("SceneControlling: Resources class wasn`t set.");
		}
	}
}
