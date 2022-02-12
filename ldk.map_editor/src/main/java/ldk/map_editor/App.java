package ldk.map_editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import ldk.map_editor.exceptions.ProjectNotLoadedException;
import ldk.map_editor.model.Editor;
import ldk.map_editor.model.enums.EditorModes;
import ldk.map_editor.view.controllers.Controller;
import ldk.map_editor.view.controllers.ModeUpdateUIHandler;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static Stage stage;
	private static final String BRANCH_NAME = "Yard Industry";
	private static final String WINDOW_TITLE = "Local` SDK: Map Editor | " + BRANCH_NAME;

	private static Controller controller;
	private Editor editor;
	
	private static String defaultFile;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		App.stage = stage; //
		scene = new Scene(loadFXML("primary"), 1280, 720);
		scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
		stage.setTitle(WINDOW_TITLE);
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.setMaximized(true);
		
		stage.show();

		stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
			try {
				closeWindowEvent(event);
			} catch (ProjectNotLoadedException e) {
				e.printStackTrace();
			}
		});

		editor = new Editor();

		editor.setModeUpdateHandler(new ModeUpdateUIHandler(controller));

		controller.setEditor(editor);
		
		editor.setMode(EditorModes.SELECTION);

		Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {

			exception.printStackTrace();

			String messege = String.format("%s %n%s", exception.toString(), exception.getCause().getCause().toString());

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			String text = sw.toString();

			VBox alertContent = new VBox();

			Label labelMessege = new Label(messege);

			Label labelStackTrace = new Label(String.format("%nStack Trace:"));

			TextArea textArea = new TextArea();
			textArea.setText(text);

			alertContent.getChildren().addAll(labelMessege, labelStackTrace, textArea);

			Alert alert = new Alert(AlertType.ERROR, messege, ButtonType.OK);
			alert.setTitle(WINDOW_TITLE);
			alert.setHeaderText(exception.getMessage());
			alert.getDialogPane().setContent(alertContent);
			alert.show();

		});
		
		if (defaultFile != null) {
			File file = new File(defaultFile);
			if (file.exists() && file.isFile()) {
				editor.loadProject(file.getName().split(".")[0]);
			} else {
				throw new FileNotFoundException(String.format("Path '%s' isn`t a file or doesn`t exist. %n Project ID got: %s", defaultFile, file.getName().split(".")[0]));
			}
		}

	}

//	@Override
//	public void stop() throws ProjectNotLoadedException {
//	}
	
	public static void exitWithConfirmation( ) {
		Event.fireEvent(stage.getScene().getWindow(),
				new WindowEvent(stage.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	private void closeWindowEvent(WindowEvent event) throws ProjectNotLoadedException {

		if (!editor.areThereUnsavedChanges()) {
			System.exit(0);
		}

		Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved changes. Do you want to save them? \n" + defaultFile,
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		
		alert.initStyle(StageStyle.UNDECORATED);
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("css/style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog-pane");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isEmpty()) {
			return;
		}

		if (result.get() == ButtonType.YES) {
			editor.saveProject();
			System.exit(0);
		} else if (result.get() == ButtonType.NO) {
			System.exit(0);
		} else if (result.get() == ButtonType.CANCEL) {
			event.consume();
		}
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

		Parent loaded = fxmlLoader.load();
		controller = fxmlLoader.getController();

		return loaded;
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			
			defaultFile = args[0]
					;
		}
		
		try {
			launch();
		} catch (Exception e) {
//			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
//			alert.showAndWait();
			e.printStackTrace();
		}
	}

	private void showAlert() {

	}

	public static Controller getController() {
		return controller;
	}

	public Editor getEditor() {
		return editor;
	}

}