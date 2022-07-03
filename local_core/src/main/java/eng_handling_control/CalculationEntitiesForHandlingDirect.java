package eng_handling_control;

import java.util.ArrayList;

import eng_game_objects.Entity;
import eng_game_objects.IObject;
import eng_scene.Scene;

public class CalculationEntitiesForHandlingDirect implements ICalculationEntitiesForHandling{

	private Scene scene;

	private ArrayList<Entity> sceneEntities;

	private ArrayList<IObject> renderingHandlingEntities;
	private ArrayList<IObject> physicsHandlingEntities;
	private ArrayList<IObject> scriptHandlingEntities;

	public CalculationEntitiesForHandlingDirect(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * @return List with three lists: renderingObjects, physicsHandlingEntities and
	 *         scriptHandlingEntities.
	 * @throws Exception
	 */
	public ArrayList<ArrayList<IObject>> calculateEntitiesForHandling() {
		sceneEntities = scene.getSceneEntities();
		renderingHandlingEntities = new ArrayList<IObject>();
		scriptHandlingEntities = new ArrayList<IObject>();
		physicsHandlingEntities = new ArrayList<IObject>();

		for (Entity entity : sceneEntities) {

			renderingHandlingEntities.add(entity);

			if (entity.getPhysics() != null) {
				physicsHandlingEntities.add(entity);
			}

			if (entity.getScript() != null) {
				scriptHandlingEntities.add(entity);
			}

		}

		ArrayList<ArrayList<IObject>> listWithLists = new ArrayList<ArrayList<IObject>>();

		listWithLists.add(renderingHandlingEntities);
		listWithLists.add(physicsHandlingEntities);
		listWithLists.add(scriptHandlingEntities);

		return listWithLists;
	}

}
