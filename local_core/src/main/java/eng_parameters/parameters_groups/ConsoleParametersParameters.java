package eng_parameters.parameters_groups;

import eng_parameters.Parameters;

public class ConsoleParametersParameters extends Parameters{
	private static ConsoleParametersParameters instance;

	/**
	 * All params of method are launch parameters (parameters that will be
	 * initialized immediately after start. Other will be set by setter methods.
	 */
	private ConsoleParametersParameters() {
	}

	public static ConsoleParametersParameters createInstance() throws Exception {
		if (instance == null) {
			instance = new ConsoleParametersParameters();
			return instance;
		} else {
			throw new Exception("Instance of ConsoleParametersParameters has already been created.");
		}
	}

	public static ConsoleParametersParameters getInstance() throws Exception {
		if (instance != null) {
			return instance;
		} else {
			throw new Exception("Instance of ConsoleParametersParameters hasn`t been created.");
		}
	}
}
