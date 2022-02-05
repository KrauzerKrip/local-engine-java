package ldk.map_editor.view.controllers;

import java.util.EnumMap;

import javafx.scene.control.Button;
import ldk.map_editor.model.enums.EditorModes;
import ldk.map_editor.view.View;

public class ModeUpdateUIHandler implements IModeUpdateHandler {

	private View view;
	
	private static final String STYLE_STANDARD_BUTTON = "-fx-background-color: rgb(64, 64, 64);";
	private static final String STYLE_HOVERED_BUTTON = "-fx-background-color: rgb(72, 72, 72);";
	private static final String STYLE_ACTIVE_BUTTON = "-fx-background-color: rgb(92, 92, 92);";
	

	public ModeUpdateUIHandler(View view) {
		this.view = view;
	}

	@Override
	public void handle(EditorModes modeActive) {
		EnumMap<EditorModes, Button> toolButtons = (EnumMap<EditorModes, Button>) view.getToolButtons();

		for (var entry : toolButtons.entrySet()) {
			EditorModes mode = entry.getKey();
			Button button = entry.getValue();

			if (mode == modeActive) {

				button.setStyle(STYLE_ACTIVE_BUTTON);

				button.setOnMouseEntered(null);
				button.setOnMouseExited(null);
			} else {
				button.setStyle(STYLE_STANDARD_BUTTON);

				button.setOnMouseEntered(event -> button.setStyle(STYLE_HOVERED_BUTTON));
				button.setOnMouseExited(event -> button.setStyle(STYLE_STANDARD_BUTTON));
			}
		}

	}

}
