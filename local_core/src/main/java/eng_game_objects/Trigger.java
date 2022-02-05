package eng_game_objects;

import org.joml.Vector3f;

import eng_console.Console;
import eng_graphics.Sprite;
import eng_graphics.Texture;
import eng_graphics.TextureInfo;
import eng_physics.Collider;
import eng_physics.Physics;
import game.Resource;

public class Trigger extends Entity implements IGraphicObject {
	
	Sprite sprite;
	
	public Trigger(float height, float width, boolean preloadSprite) throws Exception {
		Physics physics = new Physics(new Collider(height, width));
		super.setPhysics(physics);
		
		if (preloadSprite) {
			TextureInfo textureInfo = new TextureInfo();
			textureInfo.setTextureInfoConstant(1, 1);
			
			String texturePath = null;
			
			try {
				texturePath = Resource.getObject("eng_trigger").textureFile().getAbsolutePath();
			} catch (Exception e) {
				texturePath = "";
				e.printStackTrace();
				Console.warn(e.toString());
			}
			
			sprite = new Sprite(super.getHeight(), super.getWidth(), new Texture(texturePath), textureInfo);
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
