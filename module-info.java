module gadoManager {

	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires org.controlsfx.controls;
	requires java.persistence;
	opens gadoManager to javafx.graphics, javafx.controls;
	exports gadoManager;
}