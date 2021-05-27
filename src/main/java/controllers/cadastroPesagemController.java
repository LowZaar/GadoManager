package controllers;

import java.util.List;

import classes.Bovinos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.DAOHibernate;

public class cadastroPesagemController {

	@FXML
	private TextArea txtAObservacoes;

	@FXML
	private DatePicker dateDataPesagem;

	@FXML
	private TextField txtPeso;

	@FXML
	private ComboBox<String> comboBovino;

	private Usuarios user;
	
	public Usuarios getUser() {
		return user;
	}



	public void setUser(Usuarios user) {
		this.user = user;
	}


	public void populateCombo() {

		DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);

		List<Bovinos> query = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());

		comboBovino.getItems().add("Selecione...");
		for (Bovinos bovinos : query) {
			comboBovino.getItems().add(bovinos.getNome());
		}

	}

}
