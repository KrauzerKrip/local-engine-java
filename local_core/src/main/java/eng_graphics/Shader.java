package eng_graphics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import eng_file_io.IResources;

public class Shader {
	private final String fileName;
	private IResources resources;
	private String shaderString;
	
	public Shader(String fileName, IResources resources) {
		this.fileName = fileName;
		this.resources = resources;
	}
	
	public String load() throws NullPointerException, IOException {
	    try (InputStream in = resources.getVertexShaderStream(fileName);
	            Scanner scanner = new Scanner(in, java.nio.charset.StandardCharsets.UTF_8.name())) {
	    	shaderString = scanner.useDelimiter("\\A").next();
	    }
	    return shaderString;
	}
	
	public String getShader() {
		return shaderString;
	}
}  
