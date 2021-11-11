package com.gadomanager.gadomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@SpringBootApplication
public class MainClass extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent root;
	private Stage primary;
	

	@Override
	public void init() throws Exception{
		springContext = SpringApplication.run(MainClass.class);		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		
		root = fxmlLoader.load();
		
		primary = primaryStage;
		
		primary.setScene(new Scene(root));
		
		primary.setTitle("Login - GadoManager");		
		Image tskImg = new Image(getClass().getResourceAsStream("/images/taskIcon.png"));
		primary.getIcons().add(tskImg);
		
		primary.setResizable(false);
		primary.show();
		
	}
	
	public void stop() throws Exception{
		
		springContext.close();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}


	
	
}
