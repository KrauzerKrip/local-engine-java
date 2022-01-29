package eng_exceptions;

public class NotMainThreadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5179339687269938116L;

	public NotMainThreadException(String className, String message) {
		super(String.format("%s: %s", className, message));
	}
}
