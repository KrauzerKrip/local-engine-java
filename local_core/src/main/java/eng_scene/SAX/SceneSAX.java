package eng_scene.SAX;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.joml.Vector3f;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import eng_console.Console;
import eng_scene.raw_data.SceneDefaultEntityRawData;
import eng_scene.raw_data.SceneEntityRawData;
import eng_scene.raw_data.SceneModelRawData;
import eng_scene.raw_data.SceneTriggerRawData;
import game.Resource;

/**
 * It is needed to parse XML scenes files.
 */
public class SceneSAX {

	private static ArrayList<SceneEntityRawData> staticModelsData = new ArrayList<SceneEntityRawData>();
	private static ArrayList<SceneEntityRawData> dynamicModelsData = new ArrayList<SceneEntityRawData>();
	private static ArrayList<SceneEntityRawData> staticDefaultEntitesData = new ArrayList<SceneEntityRawData>();
	private static ArrayList<SceneEntityRawData> dynamicDefaultEntitesData = new ArrayList<SceneEntityRawData>();
	private static ArrayList<SceneEntityRawData> triggersData = new ArrayList<SceneEntityRawData>();

	private static enum Mode {
		DYNAMIC_ENTITIES, STATIC_ENTITIES
	};

	public static enum SubMode {
		MODEL, DEFAULT_ENTITY, TRIGGER
	};

	/**
	 * @return staticModelsData, dynamicModelsData, staticDefaultEnititesData, dynamicDefaultEntitiesData, triggersData.
	 * 
	 * Element in list has: Child of SceneEntityRawData instance.
	 */
	public static ArrayList<ArrayList<SceneEntityRawData>> parseXML(String sceneName)
			throws ParserConfigurationException, SAXException, IOException, URISyntaxException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		XMLHandler handler = new XMLHandler();
		
		try {
			parser.parse(Resource.getSceneFile(sceneName), handler);
		} catch (Exception e) {
			Console.warn("SceneSAX: can`t parse scene file " + sceneName);
			e.printStackTrace();
		}

		ArrayList<ArrayList<SceneEntityRawData>> listWithLists = new ArrayList<ArrayList<SceneEntityRawData>>();

		listWithLists.add(staticModelsData);
		listWithLists.add(dynamicModelsData);
		listWithLists.add(staticDefaultEntitesData);
		listWithLists.add(dynamicDefaultEntitesData);
		listWithLists.add(triggersData);

		return listWithLists;

	}

	private static class XMLHandler extends DefaultHandler {

		private static Mode mode;
		private static SubMode subMode;
		private static String id;
		private static String object;
		private static String scriptName;
		private static boolean isEternalScript;
		private static float defaultPosX;
		private static float defaultPosY;
		private static float defaultPosZ;
		private static float defaultRotX;
		private static float defaultRotY;
		private static float defaultRotZ;
		private static float colliderHeight;
		private static float colliderWidth;

		@Override
		public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
				throws SAXException {

			if (qName.equals("static_entities")) {
				mode = Mode.STATIC_ENTITIES;
			}
			if (qName.equals("dynamic_entities")) {
				mode = Mode.DYNAMIC_ENTITIES;
			}

			if (qName.equals("model")) {
				subMode = SubMode.MODEL;
				id = attributes.getValue("id");
				object = attributes.getValue("object");
				if (mode == Mode.DYNAMIC_ENTITIES) {
					isEternalScript = Boolean.parseBoolean(attributes.getValue("is_eternal_script"));
					scriptName = attributes.getValue("script");
				}
			}

			if (qName.equals("entity")) {
				subMode = SubMode.DEFAULT_ENTITY;
				id = attributes.getValue("id");
				object = attributes.getValue("object");
				if (mode == Mode.DYNAMIC_ENTITIES) {
					isEternalScript = Boolean.parseBoolean(attributes.getValue("is_eternal_script"));
					scriptName = attributes.getValue("script");
				}
			}

			if (qName.equals("trigger")) {
				subMode = SubMode.TRIGGER;
				try {
					if (mode == Mode.STATIC_ENTITIES) { // For peace of soul.
						throw new Exception("SceneSAX: trigger can`t be static! Trigger`s ID: " + id);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				id = attributes.getValue("id");
				isEternalScript = Boolean.parseBoolean(attributes.getValue("is_eternal_script"));
				scriptName = attributes.getValue("script");
			}

			if (qName.equals("collider")) {
				colliderHeight = Float.parseFloat(attributes.getValue("collider_height"));
				colliderWidth = Float.parseFloat(attributes.getValue("collider_width"));
			}

			if (qName.equals("default_pos")) {
				defaultPosX = Float.parseFloat(attributes.getValue("default_pos_x"));
				defaultPosY = Float.parseFloat(attributes.getValue("default_pos_y"));
				defaultPosZ = Float.parseFloat(attributes.getValue("default_pos_z"));
			}

			if (qName.equals("default_rot")) {
				defaultRotX = Float.parseFloat(attributes.getValue("default_rot_x"));
				defaultRotY = Float.parseFloat(attributes.getValue("default_rot_y"));
				defaultRotZ = Float.parseFloat(attributes.getValue("default_rot_z"));
			}

		}

		@Override
		public void endElement(String namespaceURI, String localName, String qName) {
			if (qName == "end_entity") {
				if ((id != null && !id.isEmpty())) {

					Vector3f defaultPos = new Vector3f(defaultPosX, defaultPosY, defaultPosZ);
					Vector3f defaultRot = new Vector3f(defaultRotX, defaultRotY, defaultRotZ);

					if (mode == Mode.STATIC_ENTITIES) {
						scriptName = null;

						try {
							if (subMode == SubMode.MODEL)
								staticModelsData.add(new SceneModelRawData(subMode, id, object, scriptName, isEternalScript, defaultPos, defaultRot));
							else if (subMode == SubMode.DEFAULT_ENTITY)
								staticDefaultEntitesData.add(new SceneDefaultEntityRawData(subMode, id, scriptName, isEternalScript, colliderHeight,
										colliderWidth, defaultPos, defaultRot));
							else if (subMode == SubMode.TRIGGER)
								throw new Exception("SceneSAX: Trigger can`t be static! Trigger`s ID: " + id);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (mode == Mode.DYNAMIC_ENTITIES) {
						if (subMode == SubMode.MODEL) 
							dynamicModelsData.add(new SceneModelRawData(subMode, id, object, scriptName, isEternalScript, defaultPos, defaultRot));
						else if (subMode == SubMode.DEFAULT_ENTITY)
							dynamicDefaultEntitesData.add(new SceneDefaultEntityRawData(subMode, id, scriptName, isEternalScript, colliderHeight,
									colliderWidth, defaultPos, defaultRot));
						else if (subMode == SubMode.TRIGGER)
							triggersData.add(new SceneTriggerRawData(subMode, id, scriptName, isEternalScript, colliderHeight, colliderWidth,
									defaultPos, defaultRot));
					}

				} else {
					Console.write("SceneSAX: id parameter of object data is null or empty.");

					System.out.println(id);
					System.out.println(object);
					System.out.println(scriptName);
					System.out.println(new Vector3f(defaultPosX, defaultPosY, defaultPosZ));
					System.out.println(new Vector3f(defaultRotX, defaultRotY, defaultRotZ));
				}

			}
		}
	}

}
