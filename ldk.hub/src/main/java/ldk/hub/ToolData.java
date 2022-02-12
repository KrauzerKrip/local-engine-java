package ldk.hub;

import java.io.File;
import java.io.FileNotFoundException;

public class ToolData {
	
	private final String name;
	private final File imagePath;
	private final File exeFile;
	private final int padding;
	
	public ToolData(String name, String imagePathString, String exeFileString, String paddingString) throws Exception {
		this.name = name;
		
		imagePath = new File(imagePathString);
		exeFile = new File(exeFileString);
		
		if (!imagePath.exists()) {
			throw new FileNotFoundException("Image file not found! Tool: " + name);
		}
		if (!exeFile.exists()) {
			throw new FileNotFoundException("Tool executable file not found! Tool: " + name);
		}
		
		padding = Integer.parseInt(paddingString);
		
		if (padding < 0) {
			throw new Exception("Number can`t be negative");
		}
		
	}

	public String getName() {
		return name;
	}

	public File getImagePath() {
		return imagePath;
	}

	public File getExeFile() {
		return exeFile;
	}

	public int getPadding() {
		return padding;
	}
}
