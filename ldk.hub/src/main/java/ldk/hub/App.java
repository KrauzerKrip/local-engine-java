
package ldk.hub;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private String pathToXML;
    
    public App() {
        try {
			String pathToXMLRaw = getClass().getResource("xml/tools.xml").toExternalForm();
			pathToXML = pathToXMLRaw.split("file:")[1];
		} catch (Exception e) {
	    	pathToXML = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()); 
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Local` SDK: Hub");
        stage.show();
        
        try {
			ToolController.parseXML(pathToXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}