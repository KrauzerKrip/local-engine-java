package eng_file_io.content_descriptor;

import java.io.Serializable;
import java.util.HashMap;

import eng_exceptions.TextureNotFoundException;

public class ContentDescriptorJson implements IContentDescriptor, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5256391523661736540L;
	private static final String TYPE = "CONTENT_JSON_1"; //"CONTENT_JSON_FIXEDSQUARE"
	
	private static final String ATLAS_FILE_FORMAT = ".png";
	
	private HashMap<String, float[]> texturesCoordsMap;
	
	public ContentDescriptorJson() {
		this.texturesCoordsMap = new HashMap<>();
	}
	
	@Override
	public float[] getTextureCoords(String textureName) throws TextureNotFoundException {  
		
		float[] textureCoords;
		
		textureCoords = texturesCoordsMap.get(textureName);
		
		if (textureCoords != null) {
			return textureCoords;
		} else 
			throw new TextureNotFoundException("ContentDescriptorJson", "FILEPATH -> " + textureName, "Texture not found in content atlas.");
	}

	@Override
	public String getTextureFileName(String textureName) throws TextureNotFoundException {
		// TODO Auto-generated method stub
		return "atlas" + ATLAS_FILE_FORMAT;
	}
}
