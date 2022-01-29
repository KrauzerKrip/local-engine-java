package eng_scene.raw_data;

import org.joml.Vector3f;

import eng_scene.SAX.SceneSAX;

public class SceneTriggerRawData extends SceneEntityRawData {
	
	public float colliderHeight;
	public float colliderWidth;
	
	public SceneTriggerRawData(SceneSAX.SubMode type, String id, String scriptName, boolean isEternalScript, float colliderHeight, float colliderWidth, Vector3f defaultPos, Vector3f defaultRot) {
		super(type, id, scriptName, isEternalScript, defaultPos, defaultRot);
		this.colliderHeight = colliderHeight;
		this.colliderWidth = colliderWidth;
	}
}
