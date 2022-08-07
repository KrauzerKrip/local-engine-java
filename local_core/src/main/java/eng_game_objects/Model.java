package eng_game_objects;

import org.joml.Vector3f;

import eng_graphics.sprites.Sprite;

public class Model extends Entity implements IGraphicObject {
	
	Sprite sprite;
	
	/**
	 * @param sprite - instance of the Sprite class.
	 * */
	public Model(Sprite sprite) {
		this.sprite = sprite;
	}
	
	@Override
	public float getHeight() {
		return sprite.getHeight();
	}
	
	@Override
	public float getWidth() {
		return sprite.getWidth();
	}
	
	@Override
	public void render() {
		sprite.render();
	}
	
	@Override 
	public Vector3f getPosition() {
		return super.getPosition();
	}
	
	@Override 
	public Vector3f getRotation() {
		return super.getRotation();
	}
	
	@Override 
	public float getScale() {
		return super.getScale();
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	@Override
	public String getID() {
		return super.getId();
	}

}
