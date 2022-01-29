package eng_game_objects;

import eng_physics.Physics;
import eng_script.Script;

public class Entity extends Object {

	private Script script;
	private Physics physics;

	private boolean isDynamic;
	private boolean eternalScript; // When script always works.

	public void setIsDynamic(boolean value) {
		isDynamic = value;
	}

	public boolean isDynamic() {
		return isDynamic;
	}
	
	public float getHeight() {
		if (physics != null) {
			return physics.getCollider().getHeight();
		} else {
			return 0.0f;
		}
	}
	
	public float getWidth() {
		if (physics != null) {
			return physics.getCollider().getWidth();
		} else {
			return 0.0f;
		}
	}

	/**
	 * @return the eternalScript
	 */
	public boolean isEternalScript() {
		return eternalScript;
	}

	/**
	 * @param eternalScript the eternalScript to set
	 */
	public void setEternalScript(boolean eternalScript) {
		this.eternalScript = eternalScript;
	}

	/**
	 * @return the script
	 */
	public Script getScript() {
		return script;
	}

	/**
	 * @param script the script to set
	 */
	public void setScript(Script script) {
		this.script = script;
	}

	/**
	 * @return the physics
	 */
	public Physics getPhysics() {
		return physics;
	}

	/**
	 * @param physics the physics to set
	 */
	public void setPhysics(Physics physics) {
		this.physics = physics;
	}
}
