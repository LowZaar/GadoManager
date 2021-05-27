package controllers;

import java.util.List;

import classes.Bovinos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.DAOHibernate;

public class cadastroBCSController {
	
	@FXML
	private TextField txtIndiceBCS;
	
	@FXML
	private DatePicker dateData;
	
	@FXML
	private ComboBox<String> comboBovino;
	
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



	public void populateCombo() {
		
		DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
		
		List<Bovinos> query = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", 
				user.getIdEmpresas_Pessoa());
		
		comboBovino.getItems().add("Selecione...");
		for (Bovinos bovinos : query) {
			comboBovino.getItems().add(bovinos.getNome());
		}
		
	}
	
}
