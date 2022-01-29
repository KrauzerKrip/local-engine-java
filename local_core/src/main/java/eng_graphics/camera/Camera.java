package eng_graphics.camera;

import org.joml.Vector3f;

public class Camera {

	private final Vector3f position;
    private final Vector3f rotation;
    
    private ICameraController cameraController;
    
    public Camera() {
        position = new Vector3f();
        rotation = new Vector3f();
    }
    
    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }
    
    
    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if (offsetZ != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if (offsetX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
        }
        position.y += offsetY;
    }
    
    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }
    
    public void moveRotation(double offsetX, double offsetY, double offsetZ) {
        rotation.x += offsetX;
        rotation.y += offsetY;
        rotation.z += offsetZ;
    }

    public Vector3f getPosition() {
    	return new Vector3f(position);
	}


	public Vector3f getRotation() {
    	return new Vector3f(rotation);
	}

	/**
	 * @return the cameraController
	 */
	public ICameraController getCameraController() {
		return cameraController;
	}

	/**
	 * @param cameraController the cameraController to set
	 */
	public void setCameraController(ICameraController cameraController) {
		this.cameraController = cameraController;
	}
	
	public void update() {
		cameraController.update();
	}

}