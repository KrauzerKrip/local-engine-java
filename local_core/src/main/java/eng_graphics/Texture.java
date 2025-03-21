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
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

import eng_exceptions.NotMainThreadException;
import eng_exceptions.TextureNotFoundException;
import game.Resource;

public class Texture {
	
	public String filePath;
	
    private final int id;

    public Texture(String filePath) throws Exception {
        this(loadTexture(filePath));
        this.filePath = filePath;
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
	
	private static int loadTexture(String filePath) throws TextureNotFoundException, NotMainThreadException, NullPointerException, URISyntaxException {
		
		if (!("Thread[main".equals(Thread.currentThread().toString().split(",")[0]))) {
			throw new NotMainThreadException(Texture.class.getName(), "can`t load texture from the thread that isn`t the 'main' thread.");
		} 
		
		int width;
		int height;
		ByteBuffer buffer;
		
		
		try (MemoryStack stack = MemoryStack.stackPush()) {
	        IntBuffer w = stack.mallocInt(1);
	        IntBuffer h = stack.mallocInt(1);
	        IntBuffer channels = stack.mallocInt(1);
	        
	        /*OpenGL requires that texture images have a size of a power of two (2, 4, 8, 16, ...).*/
	        
	        buffer = stbi_load(filePath, w, h, channels, 4);
	        
	        if (buffer == null) {
	            new Exception("Image file [" + filePath  + "] not loaded: " + stbi_failure_reason()).printStackTrace();
	            buffer = stbi_load(Resource.getObject("eng_texture_not_found").textureFile().getAbsolutePath(), w, h, channels, 4);
		        if (buffer == null) {
		           throw new TextureNotFoundException(Texture.class.getName(), filePath, stbi_failure_reason());
		        }
	        }

	        width = w.get();
	        height = h.get();
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
