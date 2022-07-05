package eng_handling_control;

import java.util.ArrayList;

import eng_game_objects.IObject;

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
