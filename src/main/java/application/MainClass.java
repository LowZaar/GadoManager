package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");
		
		GridPane loginGrid = FXMLLoader.load(fxmlFile);
	
		Scene mainScene = new Scene(loginGrid);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login - GadoManager");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		System.out.println("test");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
