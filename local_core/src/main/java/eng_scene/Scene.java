package eng_scene;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.joml.Vector3f;
import org.xml.sax.SAXException;

import eng_console.Console;
import eng_file_io.IResources;
import eng_game_objects.Entity;
import eng_game_objects.Model;
import eng_game_objects.Object;
import eng_game_objects.Trigger;
import eng_graphics.Texture;
import eng_graphics.TextureInfo;
import eng_graphics.sprites.ObjectSprite;
import eng_graphics.sprites.Sprite;
import eng_parameters.parameters_groups.GraphicsParameters;
import eng_physics.Collider;
import eng_physics.Physics;
import eng_scene.SAX.ObjectSAX;
import eng_scene.SAX.SceneSAX;
import eng_scene.raw_data.ObjectRawData;
import eng_scene.raw_data.SceneDefaultEntityRawData;
import eng_scene.raw_data.SceneEntityRawData;
import eng_scene.raw_data.SceneModelRawData;
import eng_scene.raw_data.SceneTriggerRawData;
import eng_script.Script;

/**
 * Class of a scene. It has methods to interact with a scene. Do not forget to
 * send to a constructor name of a scene!
 */
public class Scene {

	private String name;
	private IResources resources;

	private ArrayList<Entity> sceneEntities = new ArrayList<Entity>();
	private ArrayList<Entity> sceneStaticEntities = new ArrayList<Entity>();
	private ArrayList<Entity> sceneDynamicEntities = new ArrayList<Entity>();

	private ArrayList<Model> sceneModels = new ArrayList<Model>();
	private ArrayList<Model> sceneStaticModels = new ArrayList<Model>();
	private ArrayList<Model> sceneDynamicModels = new ArrayList<Model>();

	private ArrayList<Entity> sceneDefaultEntities = new ArrayList<Entity>();
	private ArrayList<Entity> sceneStaticDefaultEntities = new ArrayList<Entity>();
	private ArrayList<Entity> sceneDynamicDefaultEntities = new ArrayList<Entity>();

	private ArrayList<Trigger> sceneTriggers = new ArrayList<Trigger>();

	/**
	 * @param name - name of a scene as in files.
	 */
	public Scene(String name, IResources resources) {
		this.name = name;
		this.resources = resources;
	}

	/**
	 * Loads scene. To get list of objects please use getters.
	 */
	public void loadScene() throws Exception {
		ArrayList<ArrayList<SceneEntityRawData>> listWithLists = SceneSAX.parseXML(name, resources);

		ArrayList<SceneEntityRawData> staticModelsData = listWithLists.get(0);
		ArrayList<SceneEntityRawData> dynamicModelsData = listWithLists.get(1);
		ArrayList<SceneEntityRawData> staticDefaultEntitiesData = listWithLists.get(2);
		ArrayList<SceneEntityRawData> dynamicDefaultEntitiesData = listWithLists.get(3);
		ArrayList<SceneEntityRawData> triggersData = listWithLists.get(4);

		for (SceneEntityRawData sceneEntityTemp : staticModelsData) {

			SceneModelRawData sceneEntity = (SceneModelRawData) sceneEntityTemp;

			Model entity = (Model) createPrimalObjectInstance(sceneEntity);

			entity.setIsDynamic(false);

			sceneEntities.add(entity);
			sceneStaticEntities.add(entity);
			sceneModels.add(entity);
			sceneStaticModels.add(entity);
		}

		for (SceneEntityRawData sceneEntityTemp : dynamicModelsData) {

			SceneModelRawData sceneEntity = (SceneModelRawData) sceneEntityTemp;

			Model entity = (Model) createPrimalObjectInstance(sceneEntity);

			entity.setIsDynamic(true);

			sceneEntities.add(entity);
			sceneDynamicEntities.add(entity);
			sceneModels.add(entity);
			sceneDynamicModels.add(entity);
		}

		for (SceneEntityRawData sceneEntityTemp : staticDefaultEntitiesData) {

			SceneDefaultEntityRawData sceneEntity = (SceneDefaultEntityRawData) sceneEntityTemp;

			Entity entity = (Entity) createPrimalObjectInstance(sceneEntity);

			entity.setIsDynamic(false);

			sceneEntities.add(entity);
			sceneStaticEntities.add(entity);
			sceneDefaultEntities.add(entity);
			sceneStaticDefaultEntities.add(entity);
		}

		for (SceneEntityRawData sceneEntityTemp : dynamicDefaultEntitiesData) {

			SceneDefaultEntityRawData sceneEntity = (SceneDefaultEntityRawData) sceneEntityTemp;

			Entity entity = (Entity) createPrimalObjectInstance(sceneEntity);

			entity.setIsDynamic(true);

			sceneEntities.add(entity);
			sceneDynamicEntities.add(entity);
			sceneDefaultEntities.add(entity);
			sceneDynamicDefaultEntities.add(entity);
		}

		for (SceneEntityRawData sceneEntityTemp : triggersData) {

			SceneTriggerRawData sceneEntity = (SceneTriggerRawData) sceneEntityTemp;

			Trigger entity = (Trigger) createPrimalObjectInstance(sceneEntity);

			entity.setIsDynamic(false);

			sceneEntities.add(entity);
			sceneDynamicEntities.add(entity);
			sceneTriggers.add(entity);
		}

	}

	/**
	 * Creates instance of {@link Object} using {@link createObjectInstance} with
	 * data about sceneObject and entityRawData.
	 */
	private Entity createPrimalObjectInstance(SceneEntityRawData sceneEntityData)
			throws ParserConfigurationException, SAXException, IOException, Exception {

		Entity entity;
		SceneModelRawData sceneModelData;
		SceneDefaultEntityRawData sceneStandardEntityData;
		SceneTriggerRawData sceneTriggerData;

		if (sceneEntityData.type == SceneSAX.SubMode.MODEL) {
			sceneModelData = (SceneModelRawData) sceneEntityData;
			ObjectRawData objectRawData = ObjectSAX.getInfo(sceneModelData.object, resources);
			entity = createEntityInstance(sceneModelData, objectRawData);
		} else if (sceneEntityData.type == SceneSAX.SubMode.DEFAULT_ENTITY) {
			sceneStandardEntityData = (SceneDefaultEntityRawData) sceneEntityData;
			entity = createEntityInstance(sceneStandardEntityData);
		} else if (sceneEntityData.type == SceneSAX.SubMode.TRIGGER) {
			sceneTriggerData = (SceneTriggerRawData) sceneEntityData;
			entity = createEntityInstance(sceneTriggerData);
		} else {
			throw new Exception("Scene: type of SceneEntityRawData sceneEntityData is incorrect.");
		}

		return entity;
	}

	/**
	 * Creates instance of {@link Model} with given params.
	 * 
	 * @throws Exception
	 */
	private Entity createEntityInstance(SceneModelRawData sceneEntityData, ObjectRawData objectRawData)
			throws Exception {

		String id = sceneEntityData.id;
		float spriteHeight = objectRawData.spriteHeight;
		float spriteWidth = objectRawData.spriteWidth;
		
		BufferedImage textureImage = null;
		
		try {
			textureImage = resources.getObject(objectRawData.id).textureImage();
		} catch (Exception e) {
			textureImage = null;
			e.printStackTrace();
			Console.warn(e.toString());
		}
		
		Texture texture = new Texture(textureImage, resources);
		Vector3f defaultPosition = sceneEntityData.defaultPos;
		Vector3f defaultRotation = sceneEntityData.defaultRot;
		String scriptName = sceneEntityData.scriptName;
		boolean isEternalScript = sceneEntityData.isEternalScript;
		float colliderHeight = objectRawData.colliderHeight;
		float colliderWidth = objectRawData.colliderWidth;
		
		TextureInfo textureInfo = new TextureInfo();
		textureInfo.setTextureInfoRelative(1f, 1f);

		Sprite sprite = new ObjectSprite(spriteHeight, spriteWidth, texture, textureInfo);
		sprite.load();
		Entity entity = new Model(sprite);
		
		entity.setScale(1f);

		setAttributes(entity, id, scriptName, isEternalScript, colliderHeight, colliderWidth, defaultPosition, defaultRotation);

		return entity;
	}

	/**
	 * Creates instance of {@link Entity} with given params.
	 * 
	 * @throws Exception
	 */
	private Entity createEntityInstance(SceneDefaultEntityRawData sceneEntityData) throws Exception {

		String id = sceneEntityData.id;
		Vector3f defaultPosition = sceneEntityData.defaultPos;
		Vector3f defaultRotation = sceneEntityData.defaultRot;
		String scriptName = sceneEntityData.scriptName;
		boolean isEternalScript = sceneEntityData.isEternalScript;

		float colliderHeight = sceneEntityData.colliderHeight;
		float colliderWidth = sceneEntityData.colliderWidth;

		Entity entity = new Entity();
		
		entity.setScale(1f);

		setAttributes(entity, id, scriptName, isEternalScript, colliderHeight, colliderWidth, defaultPosition, defaultRotation);

		return entity;
	}

	/**
	 * Creates instance of {@link Trigger} with given params.
	 * 
	 * @throws Exception
	 */
	private Entity createEntityInstance(SceneTriggerRawData sceneEntityData) throws Exception {

		String id = sceneEntityData.id;
		Vector3f defaultPosition = sceneEntityData.defaultPos;
		Vector3f defaultRotation = sceneEntityData.defaultRot;
		String scriptName = sceneEntityData.scriptName;
		boolean isEternalScript = sceneEntityData.isEternalScript;

		float colliderHeight = sceneEntityData.colliderHeight;
		float colliderWidth = sceneEntityData.colliderWidth;

		Entity entity = new Trigger(colliderHeight, colliderWidth, (boolean) GraphicsParameters.getInstance().getParameterValue("preloadTechSprites"), resources);
		entity.setScale(1f);

		setAttributes(entity, id, scriptName, isEternalScript, colliderHeight, colliderWidth, defaultPosition, defaultRotation);

		return entity;
	}

	/**
	 * Set attributes of {@code entity}.
	 * 
	 * @param entity
	 * @param id
	 * @param scriptName
	 * @param colliderHeight
	 * @param colliderWidth
	 * @param defaultPosition
	 * @param defaultRotation
	 */
	private void setAttributes(Entity entity, String id, String scriptName, boolean isEternalScript, float colliderHeight, float colliderWidth,
			Vector3f defaultPosition, Vector3f defaultRotation) {
		entity.setPosition(defaultPosition.x, defaultPosition.y, defaultPosition.z);
		entity.setRotation(defaultRotation.x, defaultRotation.y, defaultRotation.z);

		entity.setPhysics(new Physics(new Collider(colliderHeight, colliderWidth)));

		entity.setId(id);

		if (scriptName != null) {
			if ((!scriptName.isEmpty()) || (scriptName.equals("null"))) {
				entity.setScript(new Script(scriptName));
				entity.setEternalScript(isEternalScript);
			}
		}
	}

	/**
	 * @return the sceneEntities
	 */
	public ArrayList<Entity> getSceneEntities() {
		return sceneEntities;
	}

	/**
	 * @return the sceneDynamicEntities
	 */
	public ArrayList<Entity> getSceneDynamicEntities() {
		return sceneDynamicEntities;
	}

	/**
	 * @return the sceneStaticEntities
	 */
	public ArrayList<Entity> getSceneStaticEntities() {
		return sceneStaticEntities;
	}

	/**
	 * @return the sceneEntities
	 */
	public ArrayList<Model> getSceneModels() {
		return sceneModels;
	}

	/**
	 * @return the sceneStaticEntities
	 */
	public ArrayList<Model> getSceneStaticModels() {
		return sceneStaticModels;
	}

	/**
	 * @return the sceneDynamicEntities
	 */
	public ArrayList<Model> getSceneDynamicModels() {
		return sceneDynamicModels;
	}

	/**
	 * @return the sceneDefaultEntities
	 */
	public ArrayList<Entity> getSceneDefaultEntities() {
		return sceneDefaultEntities;
	}

	/**
	 * @return the sceneStaticDefaultEntities
	 */
	public ArrayList<Entity> getSceneStaticDefaultEntities() {
		return sceneStaticDefaultEntities;
	}

	/**
	 * @return the sceneDynamicDefaultEntities
	 */
	public ArrayList<Entity> getSceneDynamicDefaultEntities() {
		return sceneDynamicDefaultEntities;
	}

	/**
	 * @return the sceneTriggers
	 */
	public ArrayList<Trigger> getSceneTriggers() {
		return sceneTriggers;
	}

}
