package eng_graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryUtil;

public class Sprite {

	private final int vaoId;

	private final int posVboId;

	private final int idxVboId;

	private final int vertexCount;

	private final int textureVboId;

	private final Texture texture;

	private float spriteHeight;

	private float spriteWidth;

	public Sprite(float spriteHeight, float spriteWidth, Texture texture, TextureInfo textureInfo) throws Exception {

		FloatBuffer posBuffer = null;
		FloatBuffer textureCoordsBuffer = null;
		IntBuffer indicesBuffer = null;
		
		float textureX = 0;
		float textureY = 0;
		
		this.spriteHeight = spriteHeight;
		this.spriteWidth = spriteWidth;
		
		if (textureInfo.getMode() == TextureInfo.Mode.RELATIVE) {
			
			textureX = textureInfo.getRatioWidthTextureSprite();
			textureY = textureInfo.getRatioHeightTextureSprite();
			
		} 
		else if (textureInfo.getMode() == TextureInfo.Mode.CONSTANT) {
		
			textureX = this.spriteWidth / textureInfo.getTextureWidth();
			textureY = this.spriteHeight / textureInfo.getTextureHeight();
			
		} 
		else {
			throw new Exception("Sprite: mode " + textureInfo.getMode() + " doesn`t exist.");
		}

		float[] positions = new float[] {
				// VO
				-this.spriteWidth / 2, this.spriteHeight / 2, 0.0f,
				// V1
				-this.spriteWidth / 2, -this.spriteHeight / 2, 0.0f,
				// V2
				this.spriteWidth / 2, -this.spriteHeight / 2, 0.0f,
				// V3
				this.spriteWidth / 2, this.spriteHeight / 2, 0.0f, };

		float[] textureCoords = new float[] { 
				0.0f, 0.0f,
				0.0f, textureY, 
				textureX, textureY, 
				textureX, 0.0f, };

		int[] indices = new int[] {
				// Front face
				0, 1, 3, 3, 1, 2, };

		try {
			this.texture = texture;
			vertexCount = indices.length;

			vaoId = glGenVertexArrays();
			glBindVertexArray(vaoId);

			// Position VBO
			posVboId = glGenBuffers();
			posBuffer = MemoryUtil.memAllocFloat(positions.length);
			posBuffer.put(positions).flip();
			glBindBuffer(GL_ARRAY_BUFFER, posVboId);
			glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
			glEnableVertexAttribArray(0);

			// Define structure of the data and store it in one of the attribute lists of
			// the VAO | 3D main developments with LWJGL, Rendering, page 35.
			/*
			 * The parameters are: index: Specifies the location where the shader expects
			 * this data. size: Specifies the number of components per vertex attribute
			 * (from 1 to 4). In this case, we are passing 3D coordinates, so it should be
			 * 3. type: Specifies the type of each component in the array, in this case a
			 * float. normalized: Specifies if the values should be normalized or not.
			 * stride: Specifies the byte offset between consecutive generic vertex
			 * attributes. (We will explain it later). offset: Specifies an offset to the
			 * first component in the buffer.
			 */
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

			// Texture VBO
			textureVboId = glGenBuffers();
			textureCoordsBuffer = MemoryUtil.memAllocFloat(textureCoords.length);
			textureCoordsBuffer.put(textureCoords).flip();
			glBindBuffer(GL_ARRAY_BUFFER, textureVboId);
			glBufferData(GL_ARRAY_BUFFER, textureCoordsBuffer, GL_STATIC_DRAW);
			glEnableVertexAttribArray(1);
			glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

			// Index VBO
			idxVboId = glGenBuffers();
			indicesBuffer = MemoryUtil.memAllocInt(indices.length);
			indicesBuffer.put(indices).flip();
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			glBindVertexArray(0);

		} finally {
			if (posBuffer != null) {
				MemoryUtil.memFree(posBuffer);
			}

			if (indicesBuffer != null) {
				MemoryUtil.memFree(indicesBuffer);
			}
			if (textureCoordsBuffer != null) {
				MemoryUtil.memFree(textureCoordsBuffer);
			}
		}
	}

	public int getVaoId() {
		return vaoId;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void render() {
	
		// Activate first texture bank
		glActiveTexture(GL_TEXTURE0);

		// Bind the texture
		glBindTexture(GL_TEXTURE_2D, texture.getId());

		// Draw the sprite
		glBindVertexArray(getVaoId());

		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);

		// Restore state
		glBindVertexArray(0);

	}

	public void cleanUp() {
		glDisableVertexAttribArray(0);

		// Delete the VBOs
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(posVboId);
		glDeleteBuffers(idxVboId);
		glDeleteBuffers(textureVboId);

		// Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}

	/**
	 * @return the spriteHeight
	 */
	public float getHeight() {
		return spriteHeight;
	}

	/**
	 * @return the spriteWidth
	 */
	public float getWidth() {
		return spriteWidth;
	}
}