package eng_parameters.parameters_groups;

import eng_parameters.Parameters;

public class ObjectsParameters extends Parameters {

	private static ObjectsParameters instance;

	/**
	 * All params of method are launch parameters (parameters that will be
	 * initialized immediately after start. Other will be set by setter methods.
	 */
	private ObjectsParameters() {
	}

	public static ObjectsParameters createInstance() throws Exception {
		if (instance == null) {
			instance = new ObjectsParameters();
			return instance;
		} else {
			throw new Exception("Instance of ObjectsParameters has already been created.");
		}
	}

	public static ObjectsParameters getInstance() throws Exception {
		if (instance != null) {
			return instance;
		} else {
			throw new Exception("Instance of ObjectsParameters hasn`t been created.");
		}
	}

}

