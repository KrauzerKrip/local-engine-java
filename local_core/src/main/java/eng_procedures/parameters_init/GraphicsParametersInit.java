package eng_procedures.parameters_init;

import eng_parameters.ParametersFileReader;
import eng_parameters.parameters_groups.GraphicsParameters;

public class GraphicsParametersInit {
	private static final String GRAPHICS_PARAMETERS_FILE_NAME = "graphicsParameters.txt";

	private GraphicsParameters graphicsParameters;
	private ParametersInit parametersInit = new ParametersInit();
	private ParametersFileReader parametersFileReader;
	
	public GraphicsParametersInit() {
		this.parametersFileReader = new ParametersFileReader(GRAPHICS_PARAMETERS_FILE_NAME);
	}
	
	public GraphicsParametersInit(ParametersFileReader parametersFileReader) {
		this.parametersFileReader = parametersFileReader;
	}
	
	/**
	 * @return GraphicParameters parameters - object of {@link GraphicsParameters}.
	 */
	public GraphicsParameters initParameters() throws Exception {
		graphicsParameters = GraphicsParameters.createInstance();

		return (GraphicsParameters) parametersInit.getParametersObject(parametersFileReader, graphicsParameters);
	}
}
