package ldk.hub.SAX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ToolsSAX {
	
	static ArrayList<ArrayList<String>> toolsData;

	/**
	 * @param objectName - name of an object as in files.
	 * @return instance ObjectRawData with data about an object.
	 */
	public static ArrayList<ArrayList<String>> getToolsRawData(String pathToXML)
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		XMLHandler handler = new XMLHandler();
		
		toolsData = new ArrayList<ArrayList<String>>();
		
		try {
			parser.parse(new File(pathToXML), handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toolsData;

	}

	private static class XMLHandler extends DefaultHandler {

		private String name;
		private String imagePath;
		private String toolExe;
		private String padding;

		@Override
		public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
				throws SAXException {

			if (qName.equals("tool")) {
				name = attributes.getValue("name");
				imagePath = attributes.getValue("image_path");
				toolExe = attributes.getValue("tool_executable");
				padding = attributes.getValue("padding_left");
			}

		}

		@Override
		public void endElement(String namespaceURI, String localName, String qName) {
			if ((name != null && !name.isEmpty()) && (imagePath != null && !imagePath.isEmpty()) && (toolExe != null && !toolExe.isEmpty()) && (padding != null && !padding.isEmpty())) {

				ArrayList<String> toolData = new ArrayList<String>();
				toolData.add(name);
				toolData.add(imagePath);
				toolData.add(toolExe);
				toolData.add(padding);
				toolsData.add(toolData);

				name = null;
				imagePath = null;
				toolExe = null;
				padding = null;

			} else {
//
//				System.out.println("ToolsSAX: some parameter of object data is null or empty.");
//
//				System.out.println("\n TOOL");
//				System.out.println(name);
//				System.out.println(imagePath);
//				System.out.println(toolExe);
//				System.out.println(padding);
			}
		}
	}

}

