package eng_exceptions;

public class TextureNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8635885784509926750L;

	public TextureNotFoundException(String className, String filePath, String failureReason) {
		super(String.format("%s: texture '%s' not found. FailureReason: %s", className, filePath, failureReason));
	}
}
