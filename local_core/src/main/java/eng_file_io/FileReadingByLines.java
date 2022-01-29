package eng_file_io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadingByLines {
	String fileDirectory;
	
	public FileReadingByLines(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	
	/**@return ArrayList with read lines.*/
	public ArrayList<String> readFile() throws Exception {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
		    File file = new File(fileDirectory);
		    FileReader fileReader = new FileReader(file);
		    BufferedReader reader = new BufferedReader(fileReader);
		    String line = reader.readLine();
		    while (line != null) {
		        lines.add(line);
		        line = reader.readLine();
		    }
			reader.close();
			return lines;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		throw new Exception("FileReadingByLines: something went wrong!");
	 
	}
	
}
