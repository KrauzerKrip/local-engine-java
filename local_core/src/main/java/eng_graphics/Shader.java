package eng_graphics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import game.Resource;

public class Shader {
	private final String fileName;
	private String shaderString;
	
	public Shader(String fileName) {
		this.fileName = fileName;
	}
	
	public String load() throws NullPointerException, IOException {
	    try (InputStream in = Resource.getVertexShaderStream(fileName);
	            Scanner scanner = new Scanner(in, java.nio.charset.StandardCharsets.UTF_8.name())) {
	    	shaderString = scanner.useDelimiter("\\A").next();
	    }
	    return shaderString;
	}
	
	public String getShader() {
		return shaderString;
	}
}
