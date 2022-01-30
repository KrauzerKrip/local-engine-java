package eng_procedures.parameters_init;

import java.util.HashMap;
import java.util.Map.Entry;

import eng_parameters.ConsoleParameter;
import eng_parameters.Parameters;
import eng_parameters.ParametersFileReader;

class ParametersInit {


	/**
	 * 
	 * @param parametersFileReader.
	 * @param parameters - object of class, that inherits {@link Parameters} class.
	 * @return parameters - object of class, that inherits {@link Parameters} class,
	 *         but now its HashMap of parameters filled with {name =
	 *         ConsoleParameter}.
	 * @throws Exception
	 */
	Parameters getParametersObject(ParametersFileReader parametersFileReader, Parameters parameters) throws Exception {
		HashMap<String, ConsoleParameter> parametersHashMap = parameters.getParametersHashMap();

		// Copy all elements to parametersHashMap from getInitedParametersHashMap.
		if (parametersHashMap.isEmpty()) {
			parametersHashMap.putAll(getInitedParametersHashMap(parametersFileReader));
		} else {
			throw new Exception("ParametersInit: parametersHashMap isn`t empty! File: " + parametersFileReader.getFileName());
		}

		return parameters;
	}

	/**
	 * 
	 * @param parametersFileReader
	 * @return HashMap of inited parameters (HashMap that have ConsoleParameters in
	 *         other words).
	 * @throws Exception
	 */
	private HashMap<String, ConsoleParameter> getInitedParametersHashMap(ParametersFileReader parametersFileReader)
			throws Exception {

		HashMap<String, Object[]> parametersRawDataMap = parametersFileReader.getParametersRawDataMap();
		HashMap<String, ConsoleParameter> parametersHashMap = new HashMap<String, ConsoleParameter>();

		for (Entry<String, Object[]> entrySet : parametersRawDataMap.entrySet()) {
			String parameterName = entrySet.getKey();
			String parameterType = (String) entrySet.getValue()[0];
			var parameterValue = entrySet.getValue()[1];
			boolean isConfirmationNeeded = (boolean) entrySet.getValue()[2];
			String comment = (String) entrySet.getValue()[3];
			
			ConsoleParameter consoleParameter;
			
			if (parameterType.equals("BOOLEAN")) {
				consoleParameter = new ConsoleParameter(parameterName, (boolean) parameterValue,
						isConfirmationNeeded, comment);
			} 
			else if (parameterType.equals("INT")) {
				consoleParameter = new ConsoleParameter(parameterName, (int) parameterValue,
						isConfirmationNeeded, comment);
			}
			else if (parameterType.equals("STRING")) {
				consoleParameter = new ConsoleParameter(parameterName, (String) parameterValue,
						isConfirmationNeeded, comment);
			}
			else if (parameterType.equals("FLOAT")) {
				consoleParameter = new ConsoleParameter(parameterName, (float) parameterValue,
						isConfirmationNeeded, comment);
			} else {
				new Exception("ParametersInit: can`t recognize type '"+ parameterType + "'.").printStackTrace();
				return null;
			}

			parametersHashMap.put(parameterName, consoleParameter);
		}

		return parametersHashMap;
	}

}
