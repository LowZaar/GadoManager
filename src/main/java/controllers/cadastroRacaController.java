package controllers;

import classes.Racas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroRacaController {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void salvar() {
		
		DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
		
		daoRa.beginTransaction().save(new Racas(txtNome.getText())).commitTransaction().closeAll();
		
	}
	
	@FXML
	public void cancelar() {
		
		Stage currentStage = (Stage) btnCancelar.getScene().getWindow();
		currentStage.close();
	}
}
