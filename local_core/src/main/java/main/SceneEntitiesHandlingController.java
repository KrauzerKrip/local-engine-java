package main;

import java.util.ArrayList;

import org.joml.Vector2f;

import eng_game_objects.Entity;
import eng_game_objects.IObject;
import eng_game_objects.Object;
import eng_graphics.Sprite;
import eng_graphics.camera.Camera;
import eng_scene.Scene;

public class SceneEntitiesHandlingController {

	/**
	 * Thinks what scene objects will be or will not be handled and will be added to
	 * the objects arrays.
	 * 
	 * Rendering objects - objects to be rendered, Script handling objects - objects
	 * whose scripts will be handled,
	 */

	
	private final ICalculationEntitiesForHandling calculationEntitiesForHandling;

	public SceneEntitiesHandlingController(ICalculationEntitiesForHandling calctulationObjectsForHandling) {
		
		this.calculationEntitiesForHandling = calctulationObjectsForHandling;

	}

	/**
	 * @return List with three lists: renderingObjects, physicsHandlingObjects and
	 *         scriptHandlingObjects.
	 * @throws Exception
	 */
	public ArrayList<ArrayList<IObject>> getEntitiesForHandling() throws Exception {
		return calculationEntitiesForHandling.calculateEntitiesForHandling();
	}

}
