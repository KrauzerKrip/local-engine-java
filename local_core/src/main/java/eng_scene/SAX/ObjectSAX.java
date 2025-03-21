package eng_scene.SAX;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import eng_console.Console;
import eng_scene.raw_data.ObjectRawData;
import eng_stuff.FilePaths;
import game.Resource;

/**
 * It is needed to parse XML objects files.
 */
public class ObjectSAX {

	static ObjectRawData entityRawData;

	/**
	 * @param objectName - name of an object as in files.
	 * @return instance ObjectRawData with data about an object.
	 */
	public static ObjectRawData getInfo(String objectName)
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		XMLHandler handler = new XMLHandler();
		
		try {
			Console.write(objectName);
			parser.parse(Resource.getObject(objectName).infoFile(), handler);
		} catch (IOException | URISyntaxException e) {
			Console.warn(e.toString());
			parser.parse(new File(String.format("%s/%s/info.xml", FilePaths.getObjectsPath(), "eng_object_not_found")), handler); // if object not found
		}

		return entityRawData;

	}

	private static class XMLHandler extends DefaultHandler {

		private String id;
		private String type;
		private int spriteHeight;
		private int spriteWidth;
		private int colliderHeight;
		private int colliderWidth; 

		@Override
		public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
				throws SAXException {

			if (qName.equals("object")) {
				id = attributes.getValue("id");
				type = attributes.getValue("type");
				spriteHeight = Integer.parseInt(attributes.getValue("sprite_height"));
				spriteWidth = Integer.parseInt(attributes.getValue("sprite_width"));
				colliderHeight = Integer.parseInt(attributes.getValue("collider_height"));
				colliderWidth = Integer.parseInt(attributes.getValue("collider_width"));
			}

		}

		@Override
		public void endElement(String namespaceURI, String localName, String qName) {
			if ((id != null && !id.isEmpty()) && (type != null && !type.isEmpty()) && (spriteHeight != 0)
					&& (spriteWidth != 0)) {

				entityRawData = new ObjectRawData(id, type, spriteHeight, spriteWidth, colliderHeight, colliderWidth);

				id = null;
				type = null;
				spriteHeight = 0;
				spriteWidth = 0;
				colliderHeight = 0;
				colliderWidth = 0;

			} else {

				Console.write("ObjectSAX: some parameter of object data is null or empty.");

				System.out.println("\n OBJECT");
				System.out.println(id);
				System.out.println(type);
				System.out.println(spriteHeight);
				System.out.println(spriteWidth);
				System.out.println(colliderHeight);
				System.out.println(colliderWidth);
			}
		}
	}

}
