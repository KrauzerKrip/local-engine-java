package eng_exceptions;

public class ConsoleParameterNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3689760484731198859L;

	public ConsoleParameterNotFoundException(String className, String consoleParameterName) {
		super(String.format("%s: console parameter '%s' not found.", className, consoleParameterName));
	}
}
