package eng_file_io;

import java.io.InputStream;
import java.util.ArrayList;

import eng_file_io.content_descriptor.ContentDescriptorJson;
import eng_file_io.content_descriptor.IContentDescriptor;
import eng_graphics.Texture;

public class Content implements IContent {
	
	private IResources resources;
	private String content;
	
	
	public Content(IResources resources, String content) {
		this.resources = resources;
		this.content = content;
	}
	
	private void loadAtlases() {
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public Texture getTexture(String textureName) throws Exception {
		
		/* 1. We should to obtain the type of content atlas. 
		 * 2. We should to choose a right descriptor. 
		 * 
		 * !!! We should to load atlas once and use it for multiple textures. 
		 */
		
		InputStream contentFileDescriptor = resources.getContentFileStream("content_descriptor.json");
		
		IContentDescriptor descriptor = getDescriptor(contentFileDescriptor);
		
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		
	}
	@Override
	private IContentDescriptor getDescriptor(InputStream file) throws Exception {
		FileReadingByLines fileReadingByLines = new FileReadingByLines(file);
		ArrayList<String> lines = fileReadingByLines.readFile();
		
		String contentType = "PLACEHOLDER"; // TODO
		
		if (contentType.equals("CONTENT_JSON_1")) {
			return new ContentDescriptorJson();
		}
		
		throw new Exception("Content: content texture atlas type not recognized.");
	}
	
	
	
	
}
 