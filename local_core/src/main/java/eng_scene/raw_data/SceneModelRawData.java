package eng_scene.raw_data;

import org.joml.Vector3f;

import eng_scene.SAX.SceneSAX;

public class SceneModelRawData extends SceneEntityRawData {
	
	public String object;
	
	public SceneModelRawData(SceneSAX.SubMode type, String id, String object, String scriptName, boolean isEternalScript, Vector3f defaultPos, Vector3f defaultRot) {
		super(type, id, scriptName, isEternalScript, defaultPos, defaultRot);
		this.object = object;
	}
}
