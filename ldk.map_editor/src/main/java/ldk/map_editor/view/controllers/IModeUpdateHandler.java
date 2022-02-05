package ldk.map_editor.view.controllers;

import ldk.map_editor.model.enums.EditorModes;

public interface IModeUpdateHandler {
	public void handle(EditorModes mode);
}
