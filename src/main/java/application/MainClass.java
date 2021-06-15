package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");
		
		GridPane loginGrid = FXMLLoader.load(fxmlFile);
	
		Scene mainScene = new Scene(loginGrid);
		Image tskImg = new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png"));
		primaryStage.getIcons().add(tskImg);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login - GadoManager");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
