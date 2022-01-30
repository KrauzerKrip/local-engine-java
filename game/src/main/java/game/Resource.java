package game;

import java.io.File;
import java.net.URISyntaxException;

public class Resource {
	
	private Resource() {
		throw new IllegalStateException();
	}
	
	public static ObjectFiles getObject(String objectName) throws URISyntaxException {
		
		File infoFile = new File(MainGame.class.getResource("objects/" + objectName + "/info.xml").toURI());
		File textureFile = new File(MainGame.class.getResource("objects/" + objectName + "/textures/texture.png").toURI());
		
		return new ObjectFiles(infoFile, textureFile);
	}
	
	public static File getParametersFile(String parametersName) throws URISyntaxException {
		return new File(Resource.class.getResource("parameters/" + parametersName).toURI());
	}
	
	public static File getSceneFile(String sceneName) throws URISyntaxException {
		return new File(Resource.class.getResource("scenes/" + sceneName + "/" + sceneName + ".xml").toURI());
	}
}
