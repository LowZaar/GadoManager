package controllers;

import java.net.URL;

import classes.Cidades;
import classes.Estados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class cadastroEmpresaController {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private PasswordField passCPF;

	@FXML
	private DatePicker dateDataNascimento;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtCEP;

	@FXML
	private ComboBox<Estados> comboEstado;

	@FXML
	private TextField txtIE;

	@FXML
	private TextField txtIM;

	@FXML
	private PasswordField passEmail;

	@FXML
	private TextField txtRG;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtTP;

	@FXML
	private ComboBox<Cidades> comboCidade;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	public void Salvar() {

	}

	@FXML
	public void Cancelar() throws Exception {

		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");

		GridPane loginGrid = FXMLLoader.load(fxmlFile);

		Stage window = (Stage) btnSalvar.getScene().getWindow();

		window.setScene(new Scene(loginGrid));
	}

}
