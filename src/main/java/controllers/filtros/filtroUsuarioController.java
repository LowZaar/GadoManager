package controllers.filtros;

import java.sql.ResultSet;
import java.sql.SQLException;


import classes.Usuarios;
import controllers.consultaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAODatabase;

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
		

		if (!nome.isEmpty()) {
			sql += "WHERE usuarios.nome LIKE '%" + nome + "%' AND ";

		}
		if (!usuario.isEmpty()) {

			sql += "WHERE usuarios.usuario LIKE '%" + usuario + "%' AND ";

		}
		if (!email.isEmpty()) {

			sql += "WHERE usuarios.email like '%" + email + "%' AND ";
		}
		
		sql += "WHERE usuarios.idEmpresa_Pessoa = " + user.getIdEmpresas_Pessoa().getIdEmpresa_Pessoa()+ "";

		DAODatabase daoJDBC = new DAODatabase();
		ResultSet queryResult = daoJDBC.selectLazy(sql);
		ObservableList<Object> result = FXCollections.observableArrayList();

		while (queryResult.next()) {
			
			Usuarios user = new Usuarios();

			String nomeQ = queryResult.getString("nome");
			user.setNome(nomeQ);

			String usuarioQ = queryResult.getString("usuario");
			user.setUsuario(usuarioQ);
			;

			String emailQ = queryResult.getString("email");
			user.setEmail(emailQ);

			String senhaQ = queryResult.getString("senha");
			user.setSenha(senhaQ);

			Boolean usuarioMestre = queryResult.getBoolean("usuarioMestre");
			user.setUsuarioMestre(usuarioMestre);

			result.add(user);

		}

		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
		consultaController.setPerspectiveList(result);
	}

	@FXML
	void cancelar(ActionEvent event) {

		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
	}
}
