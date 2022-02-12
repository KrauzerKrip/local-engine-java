package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Resource {
	
	private Resource() {
		throw new IllegalStateException();
	}
	
	public static ObjectStreams getObject(String objectName) throws NullPointerException {
		
		
		InputStream infoStream = null;
		BufferedImage textureImage = null;
		
		try {
			infoStream = Resource.class.getResourceAsStream("objects/" + objectName + "/info.xml");
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		}
		
		try {
			textureImage = ImageIO.read(Resource.class.getResourceAsStream("objects/" + objectName + "/textures/texture.png"));
		} catch (NullPointerException | IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		
		return new ObjectStreams(infoStream, textureImage);
	}
	
	public static InputStream getParametersFile(String parametersName) throws NullPointerException {
		return Resource.class.getResourceAsStream("parameters/" + parametersName);
		
	}
	
	public static InputStream getSceneFile(String sceneName) throws NullPointerException {
		return Resource.class.getResourceAsStream("scenes/" + sceneName + "/" + sceneName + ".xml");
	}
	
	public static InputStream getVertexShaderStream(String shaderFileName) throws NullPointerException {
		return Resource.class.getResourceAsStream("shaders/" + shaderFileName);
	}
	
}
