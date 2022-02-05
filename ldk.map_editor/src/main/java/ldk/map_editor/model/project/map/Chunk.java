package ldk.map_editor.model.project.map;

import java.io.Serializable;

public class Chunk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2825924499005724510L;

	private final int[] position; // Smallest coords (round int down). 
	
	private final Brush[] brushs;
	private final MapObject[] mapObjects;
	
	public Chunk(int[] position, Brush[] brushPolygons, MapObject[] mapObject) {
		this.position = position;
		this.brushs = brushPolygons;
		this.mapObjects = mapObject;
	}
	
	public void init(String mapTexturesPath) throws Exception {
		for (Brush brush : brushs) {
			//brush.init(mapTexturesPath);
		}
		for (MapObject mapObject : mapObjects) {
			//mapObject.init(mapTexturesPath);
		}
	}
	
	public int[] getPosition() {
		return position;
	}

	public Brush[] getBrushs() {
		return brushs;
	}

	public MapObject[] getMapObjects() {
		return mapObjects;
	}


}
