package eng_procedures.parameters_init;

import eng_file_io.IResources;
import eng_parameters.ParametersFileReader;
import eng_parameters.parameters_groups.SceneParameters;

public class SceneParametersInit {
	
	private static final String SCENE_PARAMETERS_FILE_NAME = "sceneParameters.txt";

	private SceneParameters sceneParameters;
	private ParametersInit parametersInit = new ParametersInit();
	private ParametersFileReader parametersFileReader;
	
	public SceneParametersInit(IResources iResources) {
		this.parametersFileReader = new ParametersFileReader(SCENE_PARAMETERS_FILE_NAME, iResources);
	}
	
	public SceneParametersInit(ParametersFileReader parametersFileReader) {
		this.parametersFileReader = parametersFileReader;
	}
	
	/**
	 * @return SceneParameters parameters - object of {@link SceneParameters}.
	 */
	public SceneParameters initParameters() throws Exception {
		sceneParameters = SceneParameters.createInstance();

		return (SceneParameters) parametersInit.getParametersObject(parametersFileReader, sceneParameters);
	}

}
