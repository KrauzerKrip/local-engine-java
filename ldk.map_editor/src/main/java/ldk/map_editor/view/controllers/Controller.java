package ldk.map_editor.view.controllers;

import javafx.fxml.FXML;
import ldk.map_editor.App;
import ldk.map_editor.exceptions.ProjectNotLoadedException;
import ldk.map_editor.model.Editor;
import ldk.map_editor.model.enums.EditorModes;
import ldk.map_editor.view.View;

public class Controller extends View{
	
	Editor editor;

	public void setEditor(Editor editor) {
		this.editor = editor;
		super.init();
	}
	
	@FXML
	private void fileSave() throws ProjectNotLoadedException {
		editor.saveProject();
	}
	
	@FXML
	private void fileExport() throws ProjectNotLoadedException {
		editor.export("");
	}
	
	@FXML
	private void fileClose() throws ProjectNotLoadedException {
		App.exitWithConfirmation();
	}

	@FXML
	private void toSelectionMode() {
		editor.setMode(EditorModes.SELECTION);
	}

	@FXML
	private void toMoveXYMode() {
		editor.setMode(EditorModes.MOVE_XY);
	}

	@FXML
	private void toCreateBrushMode() {
		editor.setMode(EditorModes.CREATE_BRUSH);
	}

	@FXML
	private void toCreateObjectMode() {
		editor.setMode(EditorModes.CREATE_OBJECT);
	}
	
	@FXML
	private void toCreateTemplateMode() {
		editor.setMode(EditorModes.CREATE_TEMPALTE);
	}

}
