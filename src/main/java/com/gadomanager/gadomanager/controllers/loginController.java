package com.gadomanager.gadomanager.controllers;

import java.net.URL;
import java.util.SplittableRandom;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.repos.UsuarioRepository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

@Component
public class loginController {

	@FXML
	private TextField userLogin;

	@FXML
	private PasswordField passwordLogin;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Label labelTitulo;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UsuarioRepository repo;
	

	@FXML
	private void loginOnEnter(KeyEvent e) throws Exception {
		if (e.getCode() == KeyCode.ENTER) {
			login();
		}
	}
	
	@FXML
	private void initialize() {
		SplittableRandom rand = new SplittableRandom();
		int chance = rand.nextInt(0,300);
		if (chance > 180 && chance < 190) {
			labelTitulo.setText("DaviManager");
		}
	}
	
	
	@FXML
	private void login() throws Exception {
		
		
		String usuario = userLogin.getText();

		String senha = passwordLogin.getText();

		Usuarios query = repo.findByUsuarioAndSenha(usuario, senha);
		
		System.out.println(query);

		
		if (query == null) {
			Notifications.create().title("Alerta de Login").text("Usuario ou senha incorreto").showWarning();

		} else {

			System.out.println("logged in");
			
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/MenuPrincipal.fxml"));
			fxmlloader.setControllerFactory(context::getBean);
			
			Parent mainMenuP = fxmlloader.load();
			
			Scene mainMenuScene = new Scene(mainMenuP);

			Stage mainMenu = new Stage();
			mainMenu.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));

			mainMenu.setScene(mainMenuScene);
			mainMenu.setTitle("Menu Principal");
			Stage window = (Stage) btnLogin.getScene().getWindow();
			
			window.close();
			mainMenu.show();

			mainMenuController mainMenuController = fxmlloader.getController();
			mainMenuController.setUserLogin(query);

			Notifications.create().title("Alerta de Login").text("Login bem sucedido").showConfirm();

		}
	}

	@FXML
	private void cadastrar() throws Exception {

		URL fxmlCadastro = getClass().getResource("/fxml/CadastroDeEmpresa.fxml");

		FXMLLoader fxmlloader = new FXMLLoader(fxmlCadastro);
		fxmlloader.setControllerFactory(context::getBean);
		
		Parent cadastroEmpresaP = fxmlloader.load();

		Scene cadastroEmpresaScene = new Scene(cadastroEmpresaP);

		Stage window = (Stage) btnCadastrar.getScene().getWindow();

		cadastroEmpresaController cadastroEmpresaController = fxmlloader.getController();
		cadastroEmpresaController.pfChecked();
		cadastroEmpresaController.estadoCombo();

		window.setTitle("Menu Principal");
		window.setScene(cadastroEmpresaScene);
	}

	public void notifyCadastro(Usuarios user, Empresas_Pessoas empresa) {
		Notifications.create().title("Alerta de Login").text("Usuario mestre criado para empresa " + empresa.getNome() 
				+"\n" 
				+ "Login de usuario : " + user.getUsuario() 
				+ "\n" 
				+ "Senha do usuario : " + user.getSenha())
				.showConfirm();
	}

}
