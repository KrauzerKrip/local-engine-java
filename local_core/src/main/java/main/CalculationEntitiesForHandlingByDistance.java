package main;

import java.util.ArrayList;
import java.util.Comparator;

import org.joml.Vector2f;

import eng_game_objects.Entity;
import eng_game_objects.IObject;
import eng_game_objects.Model;
import eng_game_objects.Trigger;
import eng_game_objects.comparators.ObjectComparatorDistance;
import eng_game_objects.comparators.ObjectComparatorZ;
import eng_graphics.camera.Camera;
import eng_parameters.parameters_groups.ObjectsParameters;
import eng_scene.Scene;

class CalculationEntitiesForHandlingByDistance implements ICalculationEntitiesForHandling {

	private final float HANDLING_DISTANCE;

	private Scene scene;
	private Camera camera;

	private ArrayList<IObject> renderingHandlingEntities;
	private ArrayList<IObject> physicsHandlingEntities;
	private ArrayList<IObject> scriptHandlingEntities;
	private ArrayList<Entity> sceneDynamicDefaultEntities;
	private ArrayList<Entity> sceneStaticDefaultEntities;
	private ArrayList<Model> sceneStaticModels;
	private ArrayList<Model> sceneDynamicModels;
	private ArrayList<Trigger> sceneTriggers;

	protected CalculationEntitiesForHandlingByDistance(Scene scene, Camera camera, float HANDLING_DISTANCE) {
		this.HANDLING_DISTANCE = HANDLING_DISTANCE;
		this.scene = scene;
		this.camera = camera;

	}

	/**
	 * @return List with three lists: renderingHandlingEntities,
	 *         physicsHandlingEntities and scriptHandlingEntities.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<IObject>> calculateEntitiesForHandling() throws Exception {
		sceneStaticModels = scene.getSceneStaticModels();
		sceneDynamicModels = scene.getSceneDynamicModels();
		sceneStaticDefaultEntities = scene.getSceneStaticDefaultEntities();
		sceneDynamicDefaultEntities = scene.getSceneDynamicDefaultEntities();
		sceneTriggers = scene.getSceneTriggers();

		renderingHandlingEntities = new ArrayList<IObject>();
		scriptHandlingEntities = new ArrayList<IObject>();
		physicsHandlingEntities = new ArrayList<IObject>();

		for (Model model : sceneStaticModels) {

			if (isEntityInHandlingDistance(model)) {

				renderingHandlingEntities.add(model);

				if (model.getPhysics() != null) {
					physicsHandlingEntities.add(model);
				}
			}

		}

		for (Model model : sceneDynamicModels) {

			boolean isEntityInHandlingDistance = isEntityInHandlingDistance(model);

			if (isEntityInHandlingDistance) {

				renderingHandlingEntities.add(model);

				if (model.getPhysics() != null) {
					physicsHandlingEntities.add(model);
				}

			}
			
			doScriptActions(model, isEntityInHandlingDistance);

		}

		for (Entity entity : sceneStaticDefaultEntities) {

			if (isEntityInHandlingDistance(entity)) {

				if (entity.getPhysics() != null) {
					physicsHandlingEntities.add(entity);
				}
			}
		}

		for (Entity entity : sceneDynamicDefaultEntities) {

			boolean isEntityInHandlingDistance = isEntityInHandlingDistance(entity);

			if (isEntityInHandlingDistance) {

				if (entity.getPhysics() != null) {
					physicsHandlingEntities.add(entity);
				}

			}

			doScriptActions(entity, isEntityInHandlingDistance);

		}

		for (Trigger trigger : sceneTriggers) {

			boolean isEntityInHandlingDistance = isEntityInHandlingDistance(trigger);

			if (isEntityInHandlingDistance) {
				
				if ((boolean) ObjectsParameters.getInstance().getParameterValue("triggersDisplay")) {
					renderingHandlingEntities.add(trigger);
				}
				
				if (trigger.getPhysics() != null) {
					physicsHandlingEntities.add(trigger);
				} else {
					throw new Exception(String.format(
							"CalculationEntitiesForHandlingByDistance: trigger %s hasn`t physics.", trigger.getId()));
				}

			}

			doScriptActions(trigger, isEntityInHandlingDistance);

		}

		ArrayList<ArrayList<IObject>> listWithLists = new ArrayList<ArrayList<IObject>>();
		
		//renderingHandlingEntities.sort(new ObjectComparatorZ()); // Sort by Z.
		renderingHandlingEntities.sort(new ObjectComparatorDistance(camera)); // Sort by distance from camera. 
		

		listWithLists.add(renderingHandlingEntities);
		listWithLists.add(physicsHandlingEntities);
		listWithLists.add(scriptHandlingEntities);

		return listWithLists;
	}

	private void doScriptActions(Entity entity, boolean isEntityInHandlingDistance) throws Exception {
		if (isEntityInHandlingDistance) {
			if (entity.getScript() != null) {
				scriptHandlingEntities.add(entity);
			}
		} else {
			if (entity.isEternalScript()) {
				if (entity.getScript() != null) {
					scriptHandlingEntities.add(entity);
				} else {
					throw new Exception("Entity " + entity.getId()
							+ " has (isEternalScript == true), but (getScript() == null). Entity hasn`t a script!");
				}
			}
		}
	}

	private boolean isEntityInHandlingDistance(Entity entity) {
		Vector2f objectPosition2f = new Vector2f(entity.getPosition().x, entity.getPosition().y);

		Vector2f cameraPosition2f = new Vector2f(camera.getPosition().x, camera.getPosition().y);

		float height = entity.getHeight();
		float width = entity.getWidth();

		Vector2f minCoordsPointObject = new Vector2f(objectPosition2f.x - width, objectPosition2f.y - height);
		Vector2f maxCoordsPointObject = new Vector2f(objectPosition2f.x + width, objectPosition2f.y + height);

		Vector2f minCoordsPointCamera = new Vector2f(cameraPosition2f.x - HANDLING_DISTANCE,
				cameraPosition2f.y - HANDLING_DISTANCE);
		Vector2f maxCoordsPointCamera = new Vector2f(cameraPosition2f.x + HANDLING_DISTANCE,
				cameraPosition2f.y + HANDLING_DISTANCE);

		if ((maxCoordsPointObject.x < minCoordsPointCamera.x) || (minCoordsPointObject.x > maxCoordsPointCamera.x)) {
			return false;
		}
		if ((maxCoordsPointObject.y < minCoordsPointCamera.y) || (minCoordsPointObject.y > maxCoordsPointCamera.y)) {
			return false;
		}

		return true;

	}

}
