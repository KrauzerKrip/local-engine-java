package ldk.map_editor.model.project.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class can be directly saved by java serialization but also can be used
 * another system (XML, binary and etc.). Anyway all data will be translated to
 * object of this class. 
 */
public class MapProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5317293619781188402L;
	
	public static final int CHUNK_SIZE = 100;
	
	private ArrayList<Region> regions;
	private ArrayList<Brush> brushs;
	private ArrayList<MapObject> mapObjects;
	
	public List<Region> getRegions() {
		return regions;
	}

	public ArrayList<Brush> getBrushs() {
		return brushs;
	}

	public ArrayList<MapObject> getMapObjects() {
		return mapObjects;
	}

}
