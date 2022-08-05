package eng_file_io;

import eng_graphics.Texture;

public interface IContent {
	
	public Texture getTexture(String textureName) throws Exception;
}
