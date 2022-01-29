package eng_game_objects;

import org.joml.Vector3f;

public class Primitive extends Object implements IGraphicObject{
	
	@Override
	public void render() {
		
	}
	
	@Override
	public String getID() {
		return super.getId();
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
}
