package game;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joml.Vector3f;

import eng_console.CommandHandler;
import eng_game_objects.Entity;
import eng_game_objects.IGraphicObject;
import eng_game_objects.IObject;
import eng_game_objects.Object;
import eng_graphics.IGameLogic;
import eng_graphics.Render;
import eng_graphics.Window;
import eng_graphics.camera.Camera;
import eng_graphics.camera.StrongParentEntityCameraController;
import eng_input.IInputLogic;
import eng_input.InputHandler;
import eng_parameters.parameters_groups.GraphicsParameters;
import eng_parameters.parameters_groups.ObjectsParameters;
import eng_parameters.parameters_groups.SceneParameters;
import eng_scene.Scene;
import local_engine.SceneControlling;
import local_engine.Time;

public class Game implements IGameLogic {
	
	private ArrayList<IObject> renderingObjects;
	private ArrayList<IObject> scriptEntities;
	private ArrayList<IObject> physicsEntities;
	private ArrayList<IObject> dynamicEntities;

	private Scene scene;

	private int inputVertical;
	private int inputHorizontal;
	private int input2;
	private double[] wheelOffsets;
	private float rotationY = 0.0f;

	private boolean stop = false;

	private final Render render;

	private Time time;

	private final IInputLogic iinputLogic = new InputHandler();

	private final Camera camera = new Camera();

	private SceneEntitiesHandlingController sceneEntitiesHandlingController;
	
	private boolean isFutureEnded = true;
	private ArrayList<IObject> renderingObjects1;
	private ArrayList<IObject> physicsEntities1;
	private ArrayList<IObject> scriptEntities1;
	
	
	public Game() throws Exception {
		render = new Render(camera);
	}

	@Override
	public void init(Window window) throws Exception {
		render.init(window);
		time = new Time();

		/*
		 * How should the object loading system work? The loader loads information about
		 * the object here, and then an instance is created based on it, which is added
		 * to an array of objects to draw.
		 *
		 * S1 - the distance at which the object goes beyond the limits of visibility.
		 * S2 - the distance at which the object is far beyond the limits of visibility.
		 *
		 * Nothing happens at the distance S1. The object simply goes beyond the limits
		 * of visibility, but remains in the array. At the distance S2, the object
		 * instance is removed from the array of objects to be drawn and the instance of
		 * the object is deleted. If you need it again, you can simply create it again
		 * and add to the array.
		 */
		
		scene = SceneControlling.loadScene("ExampleScene");
		
		renderingObjects1 =  new ArrayList<IObject>(); //scene.getSceneObjects(); //
		//dynamicEntities = scene.getSceneDynamicEntities();
		
		scriptEntities1 = new ArrayList<IObject>();
		physicsEntities1 = new ArrayList<IObject>();
		
		ICalculationEntitiesForHandling calculationEntitiesForHandling;
		
		final float HANDLING_DISTANCE = (float) GraphicsParameters.getInstance().getParameterValue("handlingDistance");
		
		if ((boolean) SceneParameters.getInstance().getParameterValue("objectDynamicHandling")) { // It should be gotten from a method too.
			calculationEntitiesForHandling = new CalculationEntitiesForHandlingByDistance(scene, camera, HANDLING_DISTANCE);
			System.out.println("objectDynamicHandling == true");
		} else { 
			calculationEntitiesForHandling = new CalculationEntitiesForHandlingDirect(scene);
			System.out.println("objectDynamicHandling == false");
		}
		
		sceneEntitiesHandlingController = new SceneEntitiesHandlingController(calculationEntitiesForHandling);
		
		window.engGlSetClearColor(0.25f, 0.25f, 0.25f, 1);
		
		for (Entity entity : scene.getSceneEntities()) {
			if (entity.getId().equals("example_object_1")) {
				camera.setCameraController(new StrongParentEntityCameraController(camera, entity, new Vector3f(0f, 0f, 2f)));
			}
		}
		
		

	}

	@Override
	public void input(Window window) {

		if (iinputLogic.getKeyPressed(window, "W")) {
			inputVertical = 1;
		}

		else if (iinputLogic.getKeyPressed(window, "S")) {
			inputVertical = -1;
		}

		else {
			inputVertical = 0;
		}

		if (iinputLogic.getKeyPressed(window, "D")) {
			inputHorizontal = 1;
		}

		else if (iinputLogic.getKeyPressed(window, "A")) {
			inputHorizontal = -1;
		}

		else {
			inputHorizontal = 0;
		}

		if (iinputLogic.getKeyPressed(window, "SPACE")) {
			stop = true;
		}

		else {
			stop = false;
		}

		if (iinputLogic.getKeyPressed(window, "ARROW_RIGHT")) {
			camera.movePosition(-0.1f, 0.0f, 0.0f);
		}

		if (iinputLogic.getKeyPressed(window, "ARROW_LEFT")) {
			camera.movePosition(0.1f, 0.0f, 0.0f);
		}

		if (iinputLogic.getKeyPressed(window, "ARROW_UP")) {
			camera.movePosition(0.0f, 0.1f, 0.0f);
		}

		if (iinputLogic.getKeyPressed(window, "ARROW_DOWN")) {
			camera.movePosition(0.0f, -0.1f, 0.0f);
		}

		if (iinputLogic.getKeyPressed(window, "T")) {
			input2 = 1;
		}

		else if (iinputLogic.getKeyPressed(window, "G")) {
			input2 = -1;
		}

		else {
			input2 = 0;
		}

		wheelOffsets = iinputLogic.getWheelOffsets(window);

	}

	@Override
	public void update(float interval) {
		
		if (isFutureEnded == true) {
			isFutureEnded = false;
			CompletableFuture<ArrayList<ArrayList<IObject>>> calculatingObjectsForHandling = CompletableFuture
					.supplyAsync(() -> {
						try {
							return sceneEntitiesHandlingController.getEntitiesForHandling();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("EXCEPTION: " + e);
							throw new IllegalStateException(e);
						}
					});	        
			calculatingObjectsForHandling.thenAccept(listWithLists -> {
				// listWithLists: renderingObjects (0), physicsHandlingObjects (1) and scriptHandlingObjects (2).
				
				// SCRIPT - ALWAYS DYNAMIC, RENDERING - BOTH, PHYSICS - BOTH !!!
				
				renderingObjects1 = new ArrayList<IObject>();
				renderingObjects1.addAll(listWithLists.get(0));

				physicsEntities1 = new ArrayList<IObject>();
				physicsEntities1.addAll(listWithLists.get(1));
				
				scriptEntities1 = new ArrayList<IObject>();
				scriptEntities1.addAll(listWithLists.get(2));
				
				isFutureEnded = true;				
				
			});
			
		    // The fix in another branch.
			renderingObjects = renderingObjects1;
			physicsEntities = physicsEntities1;
			scriptEntities = scriptEntities1;
		}
		
		try {
			if ((boolean) ObjectsParameters.getInstance().getParameterValue("triggersDisplay")) {
				// TODO Move it to CalculationEntitiesForHandling classes.
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		for (IObject iObject : physicsEntities) {
			Entity entity = (Entity) iObject;
		}

		
		for (IObject iObject : scriptEntities) {
			
			Entity entity = (Entity) iObject;

			if (entity.getId().equals("example_object_1")) { 

				float rotation = 0.0f; // sprite.getRotation().x + 0.001f;

				float rotationChange = ((float) (wheelOffsets[1] * time.deltaTime() * 0.1));

				if (stop) {
					rotationChange = 0.0f;
				}

				rotationY += rotationChange;

				if (rotation > 360) {
					rotation = 0;
					System.out.println("ops!");
				}

				if (rotationY > 360) {

					rotationY = 0;
				}

				if (rotationY < 0) {
					rotationY = 360;
				}

				entity.setRotation(rotation, rotationY, rotation);

				float x = entity.getPosition().x;
				float y = entity.getPosition().y;
				float z = entity.getPosition().z;

				float dT = time.deltaTime();

				float x1 = x + (inputHorizontal * 0.005f * dT);
				float y1 = y + (inputVertical * 0.005f * dT);
				float z1 = z + (input2 * 0.005f * dT);

				entity.setPosition(x1, y1, z1);

				//System.out.println(camera.getPosition() + " " + entity.getPosition()+ " CAM ENT ");
				//System.out.println(entity.getPosition().z);
				
				//object.setPosition(1, 1, 0);

			}

			else {
				//
			}
			

		}
		
		camera.update();
	}

	@Override
	public void render(Window window) {
		// Render graphics.
//        window.engGlSetClearColor(color, color, color, 0.0f);
		render.render(window, renderingObjects);
	}

	@Override
	public void cleanup() {
		render.cleanup();
	}

}