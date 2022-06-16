package ldk.map_editor.view.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ldk.map_editor.App;

public class DialogTextField implements IDialogTextField {
	
	Stage stage;
	
	/**
	 * @param stage - to change window`s title.
	 */
	public DialogTextField(Stage stage) {
		this.stage = stage;
	}

	@Override
	public String raise(String question) throws IOException {
		TextInputDialog dialog = new TextInputDialog();
		
		dialog.initStyle(StageStyle.UNDECORATED);
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(
		   App.class.getResource("css/style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog-pane");
		
		dialog.setContentText(question);
		
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()) {
			stage.setTitle(stage.getTitle() + " | " + result.get());
			return result.get();
		} else {
			throw new IOException("Result of text field isn`t present");
		}
	}

}
