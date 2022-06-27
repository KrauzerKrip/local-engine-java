 package eng_graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.stbi_image_free;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import eng_console.Console;
import eng_exceptions.NotMainThreadException;
import eng_exceptions.TextureNotFoundException;
import game.Resource;

public class Texture {
	
    private final int id;
    private static final int BYTES_PER_PIXEL = 4;

    public Texture(BufferedImage image) throws Exception {
        this(loadTexture(image));
    }

    public Texture(int id) {
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }
	
	private static int loadTexture(BufferedImage image) throws TextureNotFoundException, NotMainThreadException, NullPointerException, URISyntaxException, IOException {
		
		if (!("Thread[main".equals(Thread.currentThread().toString().split(",")[0]))) {
			throw new NotMainThreadException(Texture.class.getName(), "can`t load texture from the thread that isn`t the 'main' thread.");
		} 
		
		int width;
		int height;
		ByteBuffer buffer;
		
		
		try (MemoryStack stack = MemoryStack.stackPush()) {

	        /*OpenGL requires that texture images have a size of a power of two (2, 4, 8, 16, ...).*/
	        
	        //buffer = stbi_load(filePath, w, h, channels, 4);
			
            
	        try {
	        	
		        width = image.getWidth();
		        height = image.getHeight();
	        	
	            buffer = BufferUtils.createByteBuffer(width * height * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB
	            
			} catch (Exception e) {
				e.printStackTrace();
				
				Console.warn("Texture: can`t load texture"); // TODO Error texture info printing.
				
				image = Resource.getObject("eng_texture_not_found").textureImage();
				
		        width = image.getWidth();
		        height = image.getHeight();
				
	            buffer = BufferUtils.createByteBuffer(width * height * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB
	            
			}
	        
	        int[] pixels = new int[width * height];
	        
            image.getRGB(0, 0, width, height, pixels, 0, width);

            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    int pixel = pixels[y * image.getWidth() + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                    buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                    buffer.put((byte) (pixel & 0xFF));               // Blue component
                    buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
                }
            }

            buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

	     }
		
		int textureId = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, textureId);
		 
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);    // About blending: https://learnopengl.com/Advanced-OpenGL/Blending
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		
		// Upload the texture data.
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		glGenerateMipmap(GL_TEXTURE_2D);
		
		stbi_image_free(buffer);
		
		return textureId;
	}
	
}
