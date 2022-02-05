package game;

import java.io.File;
import java.net.URISyntaxException;

public class Resource {
	
	private Resource() {
		throw new IllegalStateException();
	}
	
	public static ObjectFiles getObject(String objectName) throws URISyntaxException, NullPointerException {
		
		
		File infoFile = null;
		File textureFile = null;
		
		try {
			infoFile = new File(MainGame.class.getResource("objects/" + objectName + "/info.xml").toURI());
		} catch (URISyntaxException | NullPointerException e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			textureFile = new File(MainGame.class.getResource("objects/" + objectName + "/textures/texture.png").toURI());
		} catch (URISyntaxException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return new ObjectFiles(infoFile, textureFile);
	}
	
	public static File getParametersFile(String parametersName) throws URISyntaxException, NullPointerException {
		return new File(Resource.class.getResource("parameters/" + parametersName).toURI());
	}
	
	public static File getSceneFile(String sceneName) throws URISyntaxException, NullPointerException {
		return new File(Resource.class.getResource("scenes/" + sceneName + "/" + sceneName + ".xml").toURI());
	}
}
