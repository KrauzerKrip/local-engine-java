package eng_game_objects;

import org.joml.Vector3f;

/**Class of objects in a game.*/
public class Object implements IObject {

	private final Vector3f position;
	private final Vector3f rotation;
	private float scale;

	private String id;
	
	/**
	 * @param sprite - instance of the Sprite class.
	 * */
	public Object() {
		position = new Vector3f();
		rotation = new Vector3f();
	}


	public Vector3f getPosition() {
		return new Vector3f(position);
	}

	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Vector3f getRotation() {
		return new Vector3f(rotation);
	}

	public void setRotation(float x, float y, float z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


}
