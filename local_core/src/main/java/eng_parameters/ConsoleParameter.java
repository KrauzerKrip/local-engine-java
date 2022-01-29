package eng_parameters;

public class ConsoleParameter {

	public static enum Type {BOOLEAN, INT, STRING, FLOAT};
	
	private final String name;
	private final Type type;
	private java.lang.Object value;
	private boolean isConfirmationNeeded;
	private String comment;
	
	/**
	 * @param value - boolean, int, String or float.
	 * */
	public ConsoleParameter(String name, boolean value, boolean isConfirmationNeeded, String comment) {
		this.name = name;
		this.type = Type.BOOLEAN;
		this.setValue(value);
		this.isConfirmationNeeded = isConfirmationNeeded;
		this.comment = comment;
	}
	/**
	 * @param value - boolean, int, String or float.
	 * */
	public ConsoleParameter(String name, int value, boolean isConfirmationNeeded, String comment) {
		this.name = name;
		this.type = Type.INT;
		this.setValue(value);
		this.isConfirmationNeeded = isConfirmationNeeded;
		this.comment = comment;
	}
	/**
	 * @param value - boolean, int, String or float.
	 * */
	public ConsoleParameter(String name, String value, boolean isConfirmationNeeded, String comment) {
		this.name = name;
		this.type = Type.STRING;
		this.setValue(value);
		this.isConfirmationNeeded = isConfirmationNeeded;
		this.comment = comment;
	}
	/**
	 * @param value - boolean, int, String or float.
	 * */
	public ConsoleParameter(String name, float value, boolean isConfirmationNeeded, String comment) {
		this.name = name;
		this.type = Type.FLOAT;
		this.setValue(value);
		this.isConfirmationNeeded = isConfirmationNeeded;
		this.comment = comment;
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public java.lang.Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set.
	 */
	public void setValue(boolean value) {
		this.value = value;
	}
	/**
	 * @param value the value to set.
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * @param value the value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @param value the value to set.
	 */
	public void setValue(float value) {
		this.value = value;
	}
	

	/**
	 * @return the isConfirmationNeeded
	 */
	public boolean isConfirmationNeeded() {
		return isConfirmationNeeded;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	
	
}
