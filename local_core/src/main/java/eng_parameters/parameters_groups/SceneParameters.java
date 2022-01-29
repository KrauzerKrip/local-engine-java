package eng_parameters.parameters_groups;

import eng_parameters.Parameters;

public class SceneParameters extends Parameters {

	private static SceneParameters instance;


	/**
	 * All params of method are launch parameters (parameters that will be
	 * initialized immediately after start. Other will be set by setter methods.
	 */
	private SceneParameters() {
	}

	public static SceneParameters createInstance() throws Exception {
		if (instance == null) {
			instance = new SceneParameters();
			return instance;
		} else {
			throw new Exception("Instance of SceneParameters has already been created.");
		}
	}

	public static SceneParameters getInstance() throws Exception {
		if (instance != null) {
			return instance;
		} else {
			throw new Exception("Instance of SceneParameters hasn`t been created.");
		}
	}

}
