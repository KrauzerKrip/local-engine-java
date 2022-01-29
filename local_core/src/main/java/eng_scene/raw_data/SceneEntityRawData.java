package eng_scene.raw_data;

import org.joml.Vector3f;

import eng_scene.SAX.SceneSAX;

public class SceneEntityRawData {

	public final SceneSAX.SubMode type;

	public String id;
	public String scriptName;
	public boolean isEternalScript;
	public Vector3f defaultPos;
	public Vector3f defaultRot;

	public SceneEntityRawData(SceneSAX.SubMode type, String id, String scriptName, boolean isEternalScript,
			Vector3f defaultPos, Vector3f defaultRot) {
		this.type = type;
		this.id = id;
		this.scriptName = scriptName;
		this.isEternalScript = isEternalScript;
		this.defaultPos = defaultPos;
		this.defaultRot = defaultRot;
	}
}
