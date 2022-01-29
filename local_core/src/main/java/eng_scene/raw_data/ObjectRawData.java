package eng_scene.raw_data;

public class ObjectRawData {
	
	public String id;
	public String type;
	public int spriteHeight;
	public int spriteWidth;
	public int colliderHeight;
	public int colliderWidth;
	
	public ObjectRawData(String id, String type, int spriteHeight, int spriteWidth, int colliderHeight, int colliderWidth) {
		this.id = id;
		this.type = type;
		this.spriteHeight = spriteHeight;
		this.spriteWidth = spriteWidth;
		this.colliderHeight = colliderHeight;
		this.colliderWidth = colliderWidth;
	}

}
