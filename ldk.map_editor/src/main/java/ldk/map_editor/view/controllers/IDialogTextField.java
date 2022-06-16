package ldk.map_editor.view.controllers;

import java.io.IOException;

/**
 * Dialog pane with a text field.
 * @param text
 */
public interface IDialogTextField {
	public String raise(String question) throws IOException;
}
