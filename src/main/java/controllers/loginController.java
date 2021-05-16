package controllers;

import java.net.URL;

import org.controlsfx.control.Notifications;

import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

	private DAOHibernate<Usuarios> createCon() {

		DAOHibernate<Usuarios> dao = new DAOHibernate<>();
		return dao;
	}

	@FXML
	private void login() throws Exception {

		DAOHibernate<Usuarios> dao = createCon();

		String usuario = userLogin.getText();

		String senha = passwordLogin.getText();

		Usuarios query = dao.getFirst("loginCheck", "usuario", usuario, "senha", senha);

		if (query == null) {
			Notifications.create().title("Alerta de Login").text("Usuario ou senha incorreto").showWarning();

			System.out.println("logged in");
		} else {
			Notifications.create().title("Alerta de Login").text("Login bem sucedido").showConfirm();

			URL fxmlMainMenu = getClass().getResource("/fxml/MenuPrincipal.fxml");

			GridPane mainMenuGrid = FXMLLoader.load(fxmlMainMenu);

			Stage window = (Stage) btnLogin.getScene().getWindow();

			window.setScene(new Scene(mainMenuGrid));

		}
	}

	@FXML
	private void cadastrar() throws Exception {

		URL fxmlCadastro = getClass().getResource("/fxml/CadastroDeEmpresa.fxml");

		GridPane cadastroEmpresa = FXMLLoader.load(fxmlCadastro);

		Stage window = (Stage) btnCadastrar.getScene().getWindow();

		window.setScene(new Scene(cadastroEmpresa));
	}

}
