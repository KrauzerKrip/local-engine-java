package eng_console.command_types;

import java.util.EnumSet;

import eng_console.Console;
import eng_console.Command.Flag;
import eng_exceptions.ConsoleParameterNotFoundException;
import eng_parameters.ConsoleParameter;
import eng_parameters.parameters_groups.SceneParameters;

public class CommandSet {

	String parameterGroup;
	String parameterName;
	String value;
	EnumSet<Flag> flags;

	public CommandSet(String parameterGroup, String parameterName, String value, EnumSet<Flag> flags) {
		this.parameterGroup = parameterGroup;
		this.parameterName = parameterName;
		this.value = value;
		this.flags = flags;
	}

	public boolean setConsoleParameter() {

		ConsoleParameter consoleParameter = null;

		if (parameterGroup.equals("SceneParameters")) {

			try {
				consoleParameter = SceneParameters.getInstance().getParameter(parameterName);
			} catch (ConsoleParameterNotFoundException e) {
				Console.warn(String.format("Parameter '%s' not found.", parameterName));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else if (parameterGroup.equals("ObjectsParameter")) {
			
		} else {
			Console.warn(String.format("Parameter group '%s' not found.", parameterGroup));
			return false;
		}

		if (consoleParameter != null) {

			if (consoleParameter.getType() == ConsoleParameter.Type.BOOLEAN) {
				return setBooleanParameter(consoleParameter, value);
			} else if (consoleParameter.getType() == ConsoleParameter.Type.INT) {
				return setIntParameter(consoleParameter, value);
			} else if (consoleParameter.getType() == ConsoleParameter.Type.STRING) {
				// TODO String parameter.
			} else if (consoleParameter.getType() == ConsoleParameter.Type.FLOAT) {
				// TODO Float parameter.
			}

		} else {
			Console.warn(String.format("consoleParameter variable is null. Most likely parameter group '%s' not found, but I don`t understand how anyone could break it.", parameterGroup));
			return false;
		}

		return false;
	}

	private boolean setBooleanParameter(ConsoleParameter consoleParameter, String valueRaw) {
		if (consoleParameter != null) {

			if (valueRaw.equals("true")) {
				consoleParameter.setValue(true);
				return true;
			} else if (valueRaw.equals("false")) {
				consoleParameter.setValue(false);
				return true;
			} else {
				Console.warn(String.format("Value '%s' isn`t suitable for the boolean parameter '%s'.", valueRaw,
						consoleParameter.getName()));
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean setIntParameter(ConsoleParameter consoleParameter, String valueRaw) {
		if (consoleParameter != null) {

			if (valueRaw.isBlank() == false) {

				try {
					int value = Integer.parseInt(valueRaw);
					consoleParameter.setValue(value);
					return true;
				} catch (NumberFormatException e) {
					Console.warn(String.format("Value '%s' isn`t suitable for the int parameter '%s'.", valueRaw,
							consoleParameter.getName()));

					// For test.
					StackTraceElement[] STEs = e.getStackTrace();
					for (StackTraceElement STE : STEs) {
						Console.warn(STE.toString());
					}

					return false;
				}

			} else {
				Console.warn("Value may not be blank.");
			}
		}
		return false;
	}
}
