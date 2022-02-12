package eng_file_io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileReadingByLines {
	private File file;
	private InputStream inputStream;

	public FileReadingByLines(String fileDirectory) {
		file = new File(fileDirectory);
	}

	public FileReadingByLines(File file) {
		this.file = file;
	}

	public FileReadingByLines(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/** @return ArrayList with read lines. */
	public ArrayList<String> readFile() throws Exception {

		ArrayList<String> lines = new ArrayList<String>();

		if (file != null) {
			inputStream = new FileInputStream(file);
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			String line = reader.readLine();

			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}

			return lines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new Exception("FileReadingByLines: something went wrong!");

	}

}
