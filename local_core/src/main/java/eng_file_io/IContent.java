package eng_file_io;

import eng_graphics.TextureInfo;
import eng_graphics.sprites.TileSprite;

public interface IContent {
	
	public void loadAtlases() throws Exception;
	public TileSprite getSprite(String textureName, float spriteHeight, float spriteWidth, TextureInfo textureInfo) throws Exception;
}
