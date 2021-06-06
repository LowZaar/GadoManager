package controllers;

import java.net.URL;

import org.controlsfx.control.Notifications;



import classes.Empresas_Pessoas;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utils.DAOHibernate;

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
	
	
	private DAOHibernate<Usuarios> createCon() {

		DAOHibernate<Usuarios> dao = new DAOHibernate<>();
		return dao;
	}
	
	@FXML
	private void loginOnEnter(KeyEvent e) throws Exception {
		if (e.getCode() == KeyCode.ENTER) {
			login();
		}
	}
	
	@FXML
	private void login() throws Exception {
		
		DAOHibernate<Usuarios> dao = createCon();
		
		String usuario = userLogin.getText();

		String senha = passwordLogin.getText();

		Usuarios query = dao.getFirst("loginCheck", "usuario", usuario, "senha", senha);

		if (query == null) {
			Notifications.create().title("Alerta de Login").text("Usuario ou senha incorreto").showWarning();

		} else {

			URL fxmlMainMenu = getClass().getResource("/fxml/MenuPrincipal.fxml");

			System.out.println("logged in");

			FXMLLoader loader = new FXMLLoader(fxmlMainMenu);

			Parent mainMenuP = loader.load();

			Scene mainMenuScene = new Scene(mainMenuP);

			Stage mainMenu = new Stage();

			mainMenu.setScene(mainMenuScene);

			Stage window = (Stage) btnLogin.getScene().getWindow();

			window.close();
			mainMenu.show();

			mainMenuController mainMenuController = loader.getController();

			mainMenuController.setUserLogin(query);

			Notifications.create().title("Alerta de Login").text("Login bem sucedido").showConfirm();

		}
	}

	@FXML
	private void cadastrar() throws Exception {

		URL fxmlCadastro = getClass().getResource("/fxml/CadastroDeEmpresa.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlCadastro);

		Parent cadastroEmpresaP = loader.load();

		Scene cadastroEmpresaScene = new Scene(cadastroEmpresaP);

		Stage window = (Stage) btnCadastrar.getScene().getWindow();

		cadastroEmpresaController cadastroEmpresaController = loader.getController();
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
