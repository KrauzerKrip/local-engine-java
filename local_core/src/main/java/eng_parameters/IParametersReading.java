package eng_parameters;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Needed to get main parameters from a file.
 */
public interface IParametersReading {

	/**
	 * Reads parameters and its values from a file.
	 * 
	 * @return HashMap<String, Object[]> where String is name of parameter and
	 *         Object[] is {value of parameter, is confirmation needed, comment}.
	 * @throws Exception
	 */
	HashMap<String, Object[]> getParametersRawDataMapByStrategy(InputStream fileStream) throws Exception;
}
