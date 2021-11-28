package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.repos.UsuarioRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
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

	@FXML
	private Button btnAtualizar;
	
	@Autowired
	private UsuarioRepository repo;
	
	private Usuarios user;

	private Usuarios userEdit;

	private Boolean editMode;

	public Usuarios getUserEdit() {
		return userEdit;
	}

	public void setUserEdit(Usuarios userEdit) {
		this.userEdit = userEdit;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
	}

	@FXML
	public void salvar() {

		if (editMode) {

			userEdit = this.getUserEdit();

			DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);

			Usuarios editedUser = daoUser.getAllById(userEdit.getIdUsuario());

			String nome = txtNome.getText();
			editedUser.setNome(nome);
			String usuario = txtUsuario.getText();
			editedUser.setUsuario(usuario);
			String senha = passSenha.getText();
			editedUser.setSenha(senha);
			String email = txtEmail.getText();
			editedUser.setEmail(email);
			boolean usuarioMestre = false;
			if (checkboxMestre.isSelected()) {
				usuarioMestre = true;

			}
			editedUser.setUsuarioMestre(usuarioMestre);

			repo.save(editedUser);
			
			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();

		} else {

			String nome = txtNome.getText();
			String usuario = txtUsuario.getText();
			String senha = passSenha.getText();
			String email = txtEmail.getText();
			boolean usuarioMestre = false;
			Empresas_Pessoas empresa = user.getIdEmpresas_Pessoa();

			if (checkboxMestre.isSelected()) {
				usuarioMestre = true;
			}
			Usuarios user = new Usuarios(nome, email, usuario, senha, usuarioMestre, empresa);

			repo.save(user);
			
			Notifications.create().title("Alerta").text("Usuario criado com sucesso! " + "\n" + "Nome de usuario = "
					+ user.getUsuario() + "\n" + "Senha = " + user.getSenha()).showConfirm();
		}

	}

	public void populateFields(Usuarios user) {

		txtNome.setText(user.getNome());
		txtUsuario.setText(user.getUsuario());
		passSenha.setText(user.getSenha());
		txtEmail.setText(user.getEmail());
		if (user.isUsuarioMestre()) {
			checkboxMestre.setSelected(true);
		}
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
