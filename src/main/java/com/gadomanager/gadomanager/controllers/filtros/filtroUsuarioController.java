package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class filtroUsuarioController {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtUsuario;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnFiltrar;

	@FXML
	private Button btnCancelar;

	private consultaController consultaController;
	
	private Usuarios user;
	
	public void setConsultaController(consultaController consultaController) {
		this.consultaController = consultaController;
	}
	
	public void setUser(Usuarios user) {
		this.user = user;
	}
	
	@FXML
	public void filtrar() throws SQLException {

		String nome = txtNome.getText();
		String usuario = txtUsuario.getText();
		String email = txtEmail.getText();

		String sql = "SELECT * from usuarios ";
		
		sql += "WHERE 1=1 ";
		if (!nome.isEmpty()) {
			sql += "AND usuarios.nome LIKE '%" + nome + "%' ";

		}
		if (!usuario.isEmpty()) {

			sql += "AND usuarios.usuario LIKE '%" + usuario + "%' ";

		}
		if (!email.isEmpty()) {

			sql += "AND usuarios.email like '%" + email + "%' ";
		}
		
		sql += "AND usuarios.idEmpresa_Pessoa = " + user.getIdEmpresas_Pessoa().getIdEmpresa_Pessoa()+ "";

		System.out.println(sql);
		
		DAODatabase daoJDBC = new DAODatabase();
		ResultSet queryResult = daoJDBC.selectLazy(sql);
		ObservableList<Object> result = FXCollections.observableArrayList();

		while (queryResult.next()) {
			
			Usuarios user = new Usuarios();

			String nomeQ = queryResult.getString("nome");
			user.setNome(nomeQ);

			String usuarioQ = queryResult.getString("usuario");
			user.setUsuario(usuarioQ);

			String emailQ = queryResult.getString("email");
			user.setEmail(emailQ);

			String senhaQ = queryResult.getString("senha");
			user.setSenha(senhaQ);

			Boolean usuarioMestre = queryResult.getBoolean("usuarioMestre");
			user.setUsuarioMestre(usuarioMestre);
			
			result.add(user);

		}
		
		consultaController.setPerspectiveList(result);
		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
		
	}

	@FXML
	void cancelar(ActionEvent event) {
		consultaController.setPerspectiveList(null);
		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
	}
}
