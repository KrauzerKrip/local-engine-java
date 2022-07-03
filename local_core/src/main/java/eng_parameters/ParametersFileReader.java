 package eng_parameters;

import java.util.HashMap;

import eng_file_io.IResources;
import eng_parameters.parameters_readers.ParametersTXTReader;

/**
 * Needed to get main parameters from a file.
 */
public class ParametersFileReader {
	private final String fileName;
	private final IResources iResources;
	
	/**
	 * @params filePath - name of the file (NOT A DIRECTORY!).
	 */
	public ParametersFileReader(String fileName, IResources iResources) {
		this.fileName = fileName;
		this.iResources = iResources;
	}

	/**
	 * Reads parameters and its values from a file.
	 * 
	 * Choosing right way to read parameters.
	 * 
	 * @return HashMap<String, Object[]> where String is name of parameter and
	 *         Object[] is {type of parameter, value of parameter, is confirmation needed, comment}.
	 * @throws Exception
	 */
	public HashMap<String, Object[]> getParametersRawDataMap() throws Exception {

		IParametersReading parametersReading = null;

		if (fileName.matches(".+\\.txt")) {
			parametersReading = new ParametersTXTReader();
		} else if (fileName.matches(".+\\.EXAMPLE")) {
			// CODE
		}

		if (parametersReading != null) {
			return parametersReading.getParametersRawDataMapByStrategy(iResources.getParametersFile(fileName));
		} else {
			throw new Exception("ParametersFileReader: parametersReading is null!");
		}

	}

	/**
	 * @return the filePath
	 */
	public String getFileName() {
		return fileName;
	}

}
