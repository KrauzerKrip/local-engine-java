package ldk.map_editor.exceptions;

public class ProjectNotLoadedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2392655837566398470L;

	public ProjectNotLoadedException(String className) {
		super(String.format("%s: project wasn`t loaded.", className));
	}
}
