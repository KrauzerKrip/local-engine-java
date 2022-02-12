module ldk.hub {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml;

    opens ldk.hub to javafx.fxml;
    exports ldk.hub;
}
