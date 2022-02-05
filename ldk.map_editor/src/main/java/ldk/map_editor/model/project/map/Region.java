package ldk.map_editor.model.project.map;

import java.io.Serializable;

public class Region implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317972874095567082L;
	
	private final String ID;
	private final int[] maxMinCoords;

	
	public Region(String id, int[] maxMinCoords) {
		this.ID = id;
		this.maxMinCoords = maxMinCoords;
	}
	

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the maxMinCoords
	 */
	public int[] getMaxMinCoords() {
		return maxMinCoords;
	}

}
