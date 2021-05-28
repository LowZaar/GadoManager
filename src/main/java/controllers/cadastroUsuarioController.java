package controllers;

import org.controlsfx.control.Notifications;

import classes.Empresas_Pessoas;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroUsuarioController {

	@FXML
	private CheckBox checkboxMestre;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField passSenha;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	private Usuarios user;

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void salvar() {

		String nome = txtNome.getText();
		String usuario = txtUsuario.getText();
		String senha = passSenha.getText();
		String email = txtEmail.getText();
		boolean usuarioMestre = false;
		Empresas_Pessoas empresa = user.getIdEmpresas_Pessoa();

		DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);

		if (checkboxMestre.isSelected()) {
			usuarioMestre = true;
		}
		Usuarios user = new Usuarios(nome, email, usuario, senha, usuarioMestre, empresa);

		daoUser.beginTransaction().save(user).commitTransaction().closeAll();

		Notifications.create().title("Alerta").text("Usuario criado com sucesso! " + "\n" + "Nome de usuario = "
				+ user.getUsuario() + "\n" + "Senha = " + user.getSenha()).showConfirm();
	}
	
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
