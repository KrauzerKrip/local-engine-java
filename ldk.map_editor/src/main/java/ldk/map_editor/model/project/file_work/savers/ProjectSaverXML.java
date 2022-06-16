package ldk.map_editor.model.project.file_work.savers;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ldk.map_editor.model.project.Project;
import ldk.map_editor.model.project.file_work.IProjectSaver;
import ldk.map_editor.model.project.map.Brush;
import ldk.map_editor.model.project.map.MapObject;
import ldk.map_editor.model.project.map.MapProject;

public class ProjectSaverXML implements IProjectSaver {

	@Override
	public void save(File file, Project project) {
		// TODO Auto-generated method stub

	}

	public void saveToXML(File fileXML) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element root = document.createElement("LOCAL_MAP_PROJECT");
		document.appendChild(root);
		


		root.appendChild(createUser(document, "1", "Robert", "Brown", "programmer"));
		root.appendChild(createUser(document, "2", "Pamela", "Kyle", "writer"));
		root.appendChild(createUser(document, "3", "Peter", "Smith", "teacher"));

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource source = new DOMSource(document);

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(fileXML);

		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	private void saveMap(MapProject mapProject, Document document, Element root) {
		
		Element mapElement = document.createElement("MAP");
		root.appendChild(mapElement);
		Element mapObjectsElement = document.createElement("MAP_OBJECTS");
		mapElement.appendChild(mapObjectsElement);
		Element mapBrushesElement = document.createElement("BRUSHES");
		mapElement.appendChild(mapBrushesElement);
		
		ArrayList<MapObject> mapObjects = mapProject.getMapObjects();
		ArrayList<Brush> brushs = mapProject.getBrushs();
		
		
		for (MapObject mapObject : mapObjects) {
			Element mapObjectElement = document.createElement("MapObject");
			mapObjectElement.setAttribute("texture", String.format("%s -> %s", mapObject.getTexture().content(), mapObject.getTexture().textureInfo()));
			mapObjectElement.setAttribute("textureInfo", mapObject.getTexture().textureInfo());
			mapObjectElement.setAttribute("textureInfoHeight", String.valueOf(mapObject.getTexture().textureInfoHeight()));
			mapObjectElement.setAttribute("textureInfoWidth", String.valueOf(mapObject.getTexture().textureInfoWidth()));
			mapObjectElement.setAttribute("height", String.valueOf(mapObject.getHeight()));
			mapObjectElement.setAttribute("width", String.valueOf(mapObject.getWidth()));
			mapObjectElement.setAttribute("positionX", String.valueOf(mapObject.getPositionX()));
			mapObjectElement.setAttribute("positionY", String.valueOf(mapObject.getPositionY()));
			mapObjectElement.setAttribute("positionZ", String.valueOf(mapObject.getPositionZ()));
		}
		
		for (Brush brush : brushs) {
			
		}
	}
	
	private void saveTech() {
		
	}

	private Node createUser(Document doc, String id, String firstName, String lastName, String occupation) {

		Element user = doc.createElement("user");

		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "lastname", lastName));
		user.appendChild(createUserElement(doc, "occupation", occupation));

		return user;
	}

	private Node createUserElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;
	}

}
