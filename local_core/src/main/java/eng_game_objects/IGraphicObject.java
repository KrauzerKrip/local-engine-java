package eng_game_objects;

import org.joml.Vector3f;

public interface IGraphicObject extends IObject{
	public void render();
	public Vector3f getPosition();
	public Vector3f getRotation();
	public float getScale();
	
	public String getID(); // For debug
}
