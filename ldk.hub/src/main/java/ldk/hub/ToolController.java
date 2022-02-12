package ldk.hub;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ldk.hub.SAX.ToolsSAX;

public class ToolController {
	
	private static ArrayList<ToolData> toolsData;
	
	public static void parseXML(String pathToXML) throws Exception {
		
		ArrayList<ArrayList<String>> toolsRawData;
		
		try {
			toolsRawData = ToolsSAX.getToolsRawData(pathToXML);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return;
		}
		
		toolsData = new ArrayList<ToolData>();
		
		for (ArrayList<String> toolRawData : toolsRawData) {
			ToolData toolData = new ToolData(toolRawData.get(0), toolRawData.get(1), toolRawData.get(2), toolRawData.get(3));
			toolsData.add(toolData);
		}
		
	}
	
	public static ArrayList<ToolData> getTools(String pathToXML) throws Exception {
		
		return toolsData;
		
	}
	
	public static ToolData getTool(String name) throws Exception {
		
		for (ToolData toolData : toolsData) {
			if (toolData.getName().equals(name)) {
				return toolData;
			}
		}
		
		throw new Exception("Tool not found");
	}
}
