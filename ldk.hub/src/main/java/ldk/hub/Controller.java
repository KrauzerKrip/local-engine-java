package ldk.hub;

//import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Controller {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void goToWiki() throws IOException, URISyntaxException {
    	 //Desktop.getDesktop().browse(new URI("https://wikipedia.org"));
    }
    
    private void openTool(String name) {
    	
    	ToolData toolData = null;
    	
    	try {
			toolData = ToolController.getTool(name);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Tool '" + name + "' not found. Stack trace message: " + e.getMessage(), ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    alert.close();
			    return;
			}
		}
    	
    	if (toolData != null) {
        	File exePath = toolData.getExeFile();
        	// TODO Tool launch.
		}

    }
    
    @FXML
    private void openSceneEditor() {
    	
    	openTool("Scene Editor");
    	
    }
    
    @FXML
    private void openMapEditor() {
    	openTool("Map Editor");
    }
}
