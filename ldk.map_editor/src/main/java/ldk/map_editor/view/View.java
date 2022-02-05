package ldk.map_editor.view;

import java.util.EnumMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ldk.map_editor.model.enums.EditorModes;

public class View {
	@FXML
	private Button toolSelection;
	@FXML
	private Button toolMoveXY;
	@FXML
	private Button toolCreateBrush;
	@FXML
	private Button toolCreateObject;
	@FXML
	private Button toolCreateTemplate;
	
	private final EnumMap<EditorModes, Button> toolButtons = new EnumMap<>(EditorModes.class);
	
	public void init() {
		toolButtons.put(EditorModes.SELECTION, toolSelection);
		toolButtons.put(EditorModes.MOVE_XY, toolMoveXY);
		
		toolButtons.put(EditorModes.CREATE_BRUSH, toolCreateBrush);
		toolButtons.put(EditorModes.CREATE_OBJECT, toolCreateObject);
		
		toolButtons.put(EditorModes.CREATE_TEMPALTE, toolCreateTemplate);
	}
	
	public Map<EditorModes, Button> getToolButtons() {
		return toolButtons;
	}
	
	//public void 
}
