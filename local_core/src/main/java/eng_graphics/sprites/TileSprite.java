package eng_graphics.sprites;

import eng_graphics.Texture;
import eng_graphics.TextureInfo;

public class TileSprite extends Sprite{
	
	TextureInfo textureInfo;
	float[] textureCoords;

	public TileSprite(float spriteHeight, float spriteWidth, Texture texture, TextureInfo textureInfo, float[] textureCoords) {
		super(spriteHeight, spriteWidth, texture);
		
		this.textureInfo = textureInfo;
		this.textureCoords = textureCoords;
	}

	@Override
	protected float[] getTexturePositions() {
			return new float[] {
					// VO
					-getWidth() / 2, getHeight() / 2, 0.0f,
					// V1
					-getWidth() / 2, -getHeight() / 2, 0.0f,
					// V2
					getWidth() / 2, -getHeight() / 2, 0.0f,
					// V3
					getWidth() / 2, getHeight() / 2, 0.0f, };
	}

	@Override
	protected float[] getTextureCoords() throws Exception {
		float textureX = 0;
		float textureY = 0;
		
		if (textureInfo.getMode() == TextureInfo.Mode.RELATIVE) {
			
			textureX = textureInfo.getRatioWidthTextureSprite();
			textureY = textureInfo.getRatioHeightTextureSprite();
			
		} 
		else if (textureInfo.getMode() == TextureInfo.Mode.CONSTANT) {
		
			textureX = getWidth() / textureInfo.getTextureWidth();
			textureY = getHeight() / textureInfo.getTextureHeight();
			
		} else {
			throw new Exception("Sprite <--- TileSprite: mode " + textureInfo.getMode() + " doesn`t exist.");
		}
		
		return textureCoords;
		
//		return new float[] { 
//				0.0f, 0.0f,
//				0.0f, textureY, 
//				textureX, textureY, 
//				textureX, 0.0f, };
	}

	@Override
	protected int[] getTextureIndices() {
		return new int[] {
				// Front face
				0, 1, 3, 3, 1, 2, };
	}
}
