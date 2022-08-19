package eng_file_io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import eng_console.Console;
import eng_file_io.content_descriptor.ContentDescriptorJson;
import eng_file_io.content_descriptor.IContentDescriptor;
import eng_graphics.Texture;
import eng_graphics.TextureInfo;
import eng_graphics.sprites.TileSprite;

public class Content implements IContent {
	
	private static final String ATLAS_FILE_FORMAT = ".png";
	
	private IResources resources;
	private String content;
	
	private HashMap<String, Texture> textureAtlases;
	
	private IContentDescriptor contentDescriptor;
	
	
	public Content(IResources resources, String content) {
		this.resources = resources;
		this.content = content;
	}
	
	@Override
	public void loadAtlases() throws Exception {
		Console.write(String.format("Content: content`s '%s' texture atlases loading started.", content));
		
		String[] atlasNames = contentDescriptor.getAtlasNames();
		
		for (String atlasName : atlasNames) {
			Console.write(String.format("Content: texture atlas '%s' loading started.", atlasName));
			
			BufferedImage textureImage = null;

			try {
				textureImage = ImageIO.read(resources.getContentFileStream(atlasName + ATLAS_FILE_FORMAT));
			} catch (NullPointerException | IllegalArgumentException | IOException e) {
				e.printStackTrace();   
			}

			textureAtlases.put(atlasName, new Texture(textureImage, resources));
			
			Console.write(String.format("Content: texture atlas '%s' loaded successfully.", atlasName));
		}
		
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public TileSprite getSprite(String textureName, float spriteHeight, float spriteWidth, TextureInfo textureInfo) throws Exception {
		
		/* 1. We should to obtain the type of content atlas. 
		 * 2. We should to choose a right descriptor. 
		 * 
		 * !!! We should to load atlas once and use it for multiple textures. 
		 */
		
		InputStream contentFileDescriptor = resources.getContentFileStream("content_descriptor.json");
		Texture textureAtlas = textureAtlases.get(contentDescriptor.getTextureFileName(textureName));
		
		return new TileSprite(spriteHeight, spriteWidth, textureAtlas, textureInfo, contentDescriptor.getTextureCoords(textureName));
	}
	
	private IContentDescriptor getDescriptor(InputStream file) throws Exception {
		
		if (contentDescriptor == null) {
			
			FileReadingByLines fileReadingByLines = new FileReadingByLines(file);
			ArrayList<String> lines = fileReadingByLines.readFile();
			
			String contentType = "PLACEHOLDER"; // TODO
			
			if (contentType.equals("CONTENT_JSON_1")) {
				return new ContentDescriptorJson();
			}
			
			throw new Exception("Content: content type not recognized.");
		} else {
			return contentDescriptor;
		}
	}
	
	private void loadContentDescriptor() throws Exception {
		InputStream contentFileDescriptor = resources.getContentFileStream("content_descriptor.json");
		IContentDescriptor descriptor = getDescriptor(contentFileDescriptor);
		contentDescriptor = descriptor;
	}
	
	
}
 