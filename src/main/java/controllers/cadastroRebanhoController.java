package controllers;

import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class cadastroRebanhoController {

	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextArea txtADescricao;
	
	private Usuarios user;

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}
	
	
	
}
