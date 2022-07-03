package eng_procedures.parameters_init;

import eng_file_io.IResources;
import eng_parameters.ParametersFileReader;
import eng_parameters.parameters_groups.ConsoleParametersParameters;
import eng_parameters.parameters_groups.GraphicsParameters;

public class ConsoleParametersParametersInit {
	private static final String CONSOLE_PARAMETERS_PARAMETERS_FILE_NAME = "consoleParametersParameters.txt";

	private ConsoleParametersParameters consoleParametersParameters;
	private ParametersInit parametersInit = new ParametersInit();
	private ParametersFileReader parametersFileReader;
	
	public ConsoleParametersParametersInit(IResources iResources) {
		this.parametersFileReader = new ParametersFileReader(CONSOLE_PARAMETERS_PARAMETERS_FILE_NAME, iResources);
	}
	
	public ConsoleParametersParametersInit(ParametersFileReader parametersFileReader) {
		this.parametersFileReader = parametersFileReader;
	}
	
	/**
	 * @return GraphicParameters parameters - object of {@link GraphicsParameters}.
	 */
	public ConsoleParametersParameters initParameters() throws Exception {
		consoleParametersParameters = ConsoleParametersParameters.createInstance();

		return (ConsoleParametersParameters) parametersInit.getParametersObject(parametersFileReader, consoleParametersParameters);
	}
}
