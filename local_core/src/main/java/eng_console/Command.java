package eng_console;

import java.util.EnumSet;

import eng_console.command_types.CommandSet;

public class Command {
	public static enum Type {
		SET, EXECUTE
	};

	public static enum Flag {
		FORCE
	};
	
	/**
	 * Parameter will be set immediately after instancing this class.
	 * 
	 * @param type - type for confirmation. It is an element of enum Command.Type.
	 * @param parameterGroup - parameter`s group.
	 * @param parameterName - parameter`s name.
	 * @param value - raw String value of parameter.
	 * @param flags - flags.
	 * @throws Exception
	 */
	public Command(Type type, String parameterGroup, String parameterName, String value, EnumSet<Flag> flags)
			throws Exception {
		if (type == Type.SET) {
			CommandSet commandSet = new CommandSet(parameterGroup, parameterName, value, flags);
			commandSet.setConsoleParameter();
		} else {
			throw new Exception("Command: type '" + type.toString()
					+ "' isn`t suitable for the constructor '(Type type, String parameterGroup, String parameterName, String value, EnumSet<Flag> flags)'");
		}
	}
	
	/**
	 * Command will be executed immediately after instancing this class.
	 * 
	 * @param type
	 * @param flags
	 * @throws Exception
	 */
	public Command(Type type, EnumSet<Flag> flags) throws Exception {
		if (type == Type.EXECUTE) {
			
		} else {
			throw new Exception("Command: type '" + type.toString() + "' isn`t suitable for the constructor '(TODO)'");
		}
	}

}
