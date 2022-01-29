package eng_graphics;

public class TextureInfo {
	public static enum Mode { RELATIVE, CONSTANT }; // Relative texture size / constant texture size.
	private Mode mode;
	private float ratioHeightTextureSprite;
	private float ratioWidthTextureSprite;
	private float textureHeight;
	private float textureWidth;

	public void setTextureInfoRelative(float ratioHeightTextureSprite, float ratioWidthTextureSprite) {
		this.mode = Mode.RELATIVE;
		this.ratioHeightTextureSprite = ratioHeightTextureSprite;
		this.ratioWidthTextureSprite = ratioWidthTextureSprite;
		this.textureHeight = 0;
		this.textureWidth = 0;
	}

	public void setTextureInfoConstant(float textureHeight, float textureWidth) {
		this.mode = Mode.CONSTANT;
		this.ratioHeightTextureSprite = 0;
		this.ratioWidthTextureSprite = 0;
		this.textureHeight = 1 /  textureHeight;
		this.textureWidth = 1 / textureWidth;
	}

	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * @return the textureHeight
	 */
	public float getTextureHeight() {
		return 1 / textureHeight;
	}

	/**
	 * @return the textureWidth
	 */
	public float getTextureWidth() {
		return 1 / textureWidth;
	}

	/**
	 * @return the ratioHeightTextureSprite
	 */
	public float getRatioHeightTextureSprite() {
		return ratioHeightTextureSprite;
	}

	/**
	 * @return the ratioWidthTextureSprite
	 */
	public float getRatioWidthTextureSprite() {
		return ratioWidthTextureSprite;
	}
}
