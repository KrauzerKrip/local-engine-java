package eng_game_objects;

import org.joml.Vector3f;

public interface IObject {
	public Vector3f getPosition();
	public Vector3f getRotation();
	public float getScale();
}
