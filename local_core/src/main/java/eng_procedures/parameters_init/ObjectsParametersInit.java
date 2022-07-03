package eng_procedures.parameters_init;

import eng_file_io.IResources;
import eng_parameters.ParametersFileReader;
import eng_parameters.parameters_groups.ObjectsParameters;

public class ObjectsParametersInit {
	private static final String OBJECTS_PARAMETERS_FILE_NAME = "objectsParameters.txt";

	private ObjectsParameters objectsParameters;
	private ParametersInit parametersInit = new ParametersInit();
	private ParametersFileReader parametersFileReader;
	
	public ObjectsParametersInit(IResources iResources) {
		this.parametersFileReader = new ParametersFileReader(OBJECTS_PARAMETERS_FILE_NAME, iResources);
	}
	
	public ObjectsParametersInit(ParametersFileReader parametersFileReader) {
		this.parametersFileReader = parametersFileReader;
	}
	
	/**
	 * @return ObjectsParameters parameters - object of {@link ObjectsParameters}.
	 */
	public ObjectsParameters initParameters() throws Exception {
		objectsParameters = ObjectsParameters.createInstance();

		return (ObjectsParameters) parametersInit.getParametersObject(parametersFileReader, objectsParameters);
	}

}
