package eng_tech_display;

import org.joml.Vector3f;

import eng_game_objects.IGraphicObject;
import eng_game_objects.Object;

/**
 * 
 * @author Andy Darson
 * @apiNote
 * To display engine technical stuff.
 */
public class TechDisplay extends Object implements IGraphicObject {
	
	private float height;
	private float width;
	
	public TechDisplay(float height, float width) {
		this.setHeight(height);
		this.setWidth(width);
	}
	
	@Override
	public void render() {
		
	}
	
	public Vector3f getPosition() {
		return super.getPosition();
	}
	public Vector3f getRotation() {
		return super.getRotation();
	}
	public float getScale() {
		return super.getScale();
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}


}
