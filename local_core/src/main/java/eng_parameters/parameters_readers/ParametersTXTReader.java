package eng_parameters.parameters_readers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import eng_file_io.FileReadingByLines;
import eng_parameters.IParametersReading;
import game.Resource;

public class ParametersTXTReader implements IParametersReading {

	private HashMap<String, Object[]> parametersRawData = new HashMap<String, Object[]>();

	/**
	 * @param filePath - name with format, e.g "example.txt".
	 */
	@Override
	public HashMap<String, Object[]> getParametersRawDataMapByStrategy(String fileName) throws Exception {

		// Format of line : "TYPE CONFIRMATION=TRUE/FALSE name=value //Comment.".

		ArrayList<String> lines = new FileReadingByLines(Resource.getParametersFile(fileName)).readFile();

		for (String line : lines) {
			String[] tokensTypeConfirmationParameterComment = line.split(" "); // {"TYPE", "CONFRIMATION=TRUE/FALSE",
																				// "name=value", "//comment"}
			String[] tokensParameterNameValue = tokensTypeConfirmationParameterComment[2].split("="); // {"name",
																										// "value"}

			String tokenType = tokensTypeConfirmationParameterComment[0]; // "TYPE"
			String tokenConfirmation = tokensTypeConfirmationParameterComment[1]; // "CONFIRMATION=TRUE/FALSE"
			String name = tokensParameterNameValue[0]; // "name"
			String tokenValue = tokensParameterNameValue[1]; // "value"
			String tokenConfirmationValue = tokenConfirmation.split("=")[1]; // "TRUE"/"FALSE"
			String comment = tokensTypeConfirmationParameterComment[3].split("//")[0]; // "COMMENT". MAYBE IT CAN BE
																						// BUGGY!!!

			List<String> digitsListINT = Arrays
					.asList(new String[] {"+", "-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" });

			List<String> digitsListFLOAT = new ArrayList<String>();
			digitsListFLOAT.addAll(digitsListINT);
			digitsListFLOAT.add(".");

			boolean isConfirmationNeeded;

			if (tokenConfirmationValue.equals("TRUE")) {
				isConfirmationNeeded = true;
			} else if (tokenConfirmationValue.equals("FALSE")) {
				isConfirmationNeeded = false;
			} else {
				throw new Exception(String.format(
						"ParametersTXTReading: console parameter %s has incorrect value of 'CONFIRMATION': %s", name,
						tokenConfirmationValue));
			}

			if (tokenType.equals("BOOLEAN")) {

				boolean value;

				if (tokenValue.equals("true")) {
					value = true;
				} else if (tokenValue.equals("false")) {
					value = false;
				} else {
					throw new Exception(String.format(
							"ParametersTXTReading: console parameter %s declared as BOOLEAN, but has incorrect value: %s",
							name, tokenValue));
				}

				parametersRawData.put(name, new Object[] {tokenType,  value, isConfirmationNeeded, comment });
			}

			else if (tokenType.equals("INT")) {

				if (tokenValue.chars().allMatch((s) -> digitsListINT.contains((Character.toString(s))))) {
					parametersRawData.put(name,
							new Object[] {tokenType, Integer.parseInt(tokenValue), isConfirmationNeeded, comment });
				} else {
					throw new Exception(String.format(
							"ParametersTXTReading: console parameter %s declared as INT, but has incorrect value: %s",
							name, tokenValue));
				}

			}

			else if (tokenType.equals("FLOAT")) {
				if (tokenValue.chars().allMatch((s) -> digitsListFLOAT.contains((Character.toString(s))))) {
					parametersRawData.put(name,
							new Object[] {tokenType, Float.parseFloat(tokenValue), isConfirmationNeeded, comment });
				} else {
					throw new Exception(String.format(
							"ParametersTXTReading: console parameter %s declared as FLOAT, but has incorrect value: %s",
							name, tokenValue));
				}

			}

			else if (tokenType.equals("STRING")) {
				parametersRawData.put(name, new Object[] {tokenType, tokenValue, isConfirmationNeeded, comment });
			} else {
				throw new Exception("ParametersTXTReading: unknown parameter type: " + tokenType);
			}

		}

		return parametersRawData;
	}
}
