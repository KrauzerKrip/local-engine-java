package eng_graphics.camera;

import org.joml.Vector3f;

import eng_game_objects.Entity;

public class StrongParentEntityCameraController implements ICameraController {
	private Camera camera;
	private Entity parentEntity;
	private Vector3f offset;
	
	public StrongParentEntityCameraController(Camera camera, Entity parentEntity, Vector3f offset) {
		this.camera = camera;
		this.parentEntity = parentEntity;
		this.offset = offset;
	}
	
	@Override
	public void update() {
		Vector3f cameraPosition = parentEntity.getPosition().add(offset);
		camera.setPosition(cameraPosition.x, cameraPosition.y, cameraPosition.z);
	}
	
	
	
}
