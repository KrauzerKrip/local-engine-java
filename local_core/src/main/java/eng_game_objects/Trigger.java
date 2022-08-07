package eng_game_objects;

import java.awt.image.BufferedImage;

import org.joml.Vector3f;

import eng_console.Console;
import eng_file_io.IResources;
import eng_graphics.Texture;
import eng_graphics.TextureInfo;
import eng_graphics.sprites.ObjectSprite;
import eng_graphics.sprites.Sprite;
import eng_physics.Collider;
import eng_physics.Physics;

public class Trigger extends Entity implements IGraphicObject {
	
	Sprite sprite;
	
	public Trigger(float height, float width, boolean preloadSprite, IResources resources) throws Exception {
		Physics physics = new Physics(new Collider(height, width));
		super.setPhysics(physics);
		
		if (preloadSprite) {
			TextureInfo textureInfo = new TextureInfo();
			textureInfo.setTextureInfoConstant(1, 1);
			
			BufferedImage textureImage = null;
			
			try {
				textureImage = resources.getObject("eng_trigger").textureImage();
			} catch (Exception e) {
				textureImage = null;
				e.printStackTrace();
				Console.warn(e.toString());
			}
			
			sprite = new ObjectSprite(super.getHeight(), super.getWidth(), new Texture(textureImage, resources), textureInfo);
			sprite.load();
		} else {
			sprite = null;
		}
	}
	
	@Override
	public void render() {
		if (sprite != null) { 
			sprite.render();
		}
		else {
			Console.warn("Trigger: can`t display triggers because console parameter 'preloadTechSprites' is false. Triggers will not be displayed.");
		}
	}
	
	@Override
	public String getID() {
		return super.getId();
	}
	
	@Override 
	public Vector3f getPosition() {
		return super.getPosition();
	}
	
	@Override 
	public Vector3f getRotation() {
		return super.getRotation();
	}
	
	@Override 
	public float getScale() {
		return super.getScale();
	}

}
