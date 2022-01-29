package eng_parameters;

import java.util.HashMap;

import eng_exceptions.ConsoleParameterNotFoundException;

public class Parameters {
	
	// TODO Confirmation (will be only from console. From code it will be all -force).
	
	private HashMap<String, ConsoleParameter> parametersMap = new HashMap<String, ConsoleParameter>();
	
	/**
	 * @return parametersMap ({String parameterName = ConsoleParameter
	 *         consoleParameterObject, ...}).
	 */
	public HashMap<String, ConsoleParameter> getParametersHashMap() {
		return parametersMap;
	}
	
	/** 
	 * @param name - String name of console parameter. 
	 * @return ConsoleParameter object.
	 * @throws Exception 
	 */
	public ConsoleParameter getParameter(String name) throws ConsoleParameterNotFoundException {
		ConsoleParameter consoleParameter = parametersMap.get(name);
		if (consoleParameter != null) {
			return consoleParameter;
		} else {
			throw new ConsoleParameterNotFoundException(this.getClass().getName(), name);
		}
	}
	
	/** 
	 * @param name - String name of console parameter. 
	 * @return value of ConsoleParameter (can be boolean, int, String or float).
	 * @throws Exception 
	 */
	public Object getParameterValue(String name) throws Exception {
		return getParameter(name).getValue();
	}
	
//	public abstract HashMap<String, ConsoleParameter> getParametersHashMap();
//
//	public abstract ConsoleParameter getParameter(String name) throws Exception;
//
//	public abstract Object getParameterValue(String name) throws Exception;
}
