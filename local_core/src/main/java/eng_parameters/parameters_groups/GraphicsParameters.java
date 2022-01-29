package eng_parameters.parameters_groups;

import eng_parameters.Parameters;

public class GraphicsParameters extends Parameters{
	private static GraphicsParameters instance;

	/**
	 * All params of method are launch parameters (parameters that will be
	 * initialized immediately after start. Other will be set by setter methods.
	 */
	private GraphicsParameters() {
	}

	public static GraphicsParameters createInstance() throws Exception {
		if (instance == null) {
			instance = new GraphicsParameters();
			return instance;
		} else {
			throw new Exception("Instance of GraphicParameters has already been created.");
		}
	}

	public static GraphicsParameters getInstance() throws Exception {
		if (instance != null) {
			return instance;
		} else {
			throw new Exception("Instance of GraphicParameters hasn`t been created.");
		}
	}
}
