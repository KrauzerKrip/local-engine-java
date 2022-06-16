module ldk.map_editor {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.xml;
	
    opens ldk.map_editor.view.controllers to javafx.fxml;
    opens ldk.map_editor to javafx.fxml;
    opens ldk.map_editor.view to javafx.fxml;
    
    exports ldk.map_editor to javafx.graphics;
}

