package game;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

public class Resource {
	
	private Resource() {
		throw new IllegalStateException();
	}
	
	public static ObjectFiles getObject(String objectName) throws URISyntaxException, NullPointerException {
		
		
		File infoFile = null;
		File textureFile = null;
		
		try {
			infoFile = new File(MainGame.class.getResource("objects/" + objectName + "/info.xml").toExternalForm());
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			textureFile = new File(MainGame.class.getResource("objects/" + objectName + "/textures/texture.png").toExternalForm());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return new ObjectFiles(infoFile, textureFile);
	}
	
	public static InputStream getParametersFile(String parametersName) throws NullPointerException {
		return Resource.class.getResourceAsStream("parameters/" + parametersName);
	}
	
	public static File getSceneFile(String sceneName) throws NullPointerException {
		return new File(Resource.class.getResource("scenes/" + sceneName + File.separator + sceneName + ".xml").toExternalForm());
	}
}
