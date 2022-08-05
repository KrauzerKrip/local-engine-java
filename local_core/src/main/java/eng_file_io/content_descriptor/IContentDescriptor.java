package eng_file_io.content_descriptor;

import eng_exceptions.TextureNotFoundException;

public interface IContentDescriptor {
	
	public float[] getTextureCoords(String textureName) throws TextureNotFoundException;
	public String getTextureFileName(String textureName) throws TextureNotFoundException; 
	
}
