module gadoManager {

	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.collections.ObservableList;
	requires org.apache.poi.ooxml;
	
	opens com.gadomanager.gadomanager to javafx.fxml;
}