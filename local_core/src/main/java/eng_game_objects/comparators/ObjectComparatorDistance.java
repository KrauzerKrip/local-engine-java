package eng_game_objects.comparators;

import java.util.Comparator;

import org.joml.Vector3f;

import eng_game_objects.IObject;
import eng_graphics.camera.Camera;

public class ObjectComparatorDistance implements Comparator<IObject> {
	
	Camera camera;
	
	public ObjectComparatorDistance(Camera camera) {
		this.camera = camera;
	}

	@Override
	public int compare(IObject o1, IObject o2) {
		
		Vector3f cameraPosition = camera.getPosition();
		
		float distO1 = cameraPosition.distance(o1.getPosition());
		float distO2 = cameraPosition.distance(o2.getPosition());
		
		return Float.compare(distO2, distO1);
	}
}
