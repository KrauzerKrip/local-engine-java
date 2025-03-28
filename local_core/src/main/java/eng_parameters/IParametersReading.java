package eng_parameters;

import java.util.HashMap;

/**
 * Needed to get game parameters from a file.
 */
public interface IParametersReading {

	/**
	 * Reads parameters and its values from a file.
	 * 
	 * @return HashMap<String, Object[]> where String is name of parameter and
	 *         Object[] is {value of parameter, is confirmation needed, comment}.
	 * @throws Exception
	 */
	HashMap<String, Object[]> getParametersRawDataMapByStrategy(String fileName) throws Exception;
}
