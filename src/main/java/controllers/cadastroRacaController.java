package controllers;

import org.controlsfx.control.Notifications;

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
		
		if (txtNome.getText().isEmpty()) {
			
			Notifications.create().title("Cadastro de Raças").text("Nome de raça vazio!!").showError();
		
		}else {
			
			daoRa.beginTransaction().save(new Racas(txtNome.getText())).commitTransaction().closeAll();
			
			Notifications.create().title("Cadastro de Raças").text("Raça "+ txtNome.getText() + " adicionada com sucesso!").showConfirm();
			
			txtNome.clear();
		}
		
	}
	
	@FXML
	public void cancelar() {
		
		Stage currentStage = (Stage) btnCancelar.getScene().getWindow();
		currentStage.close();
	}
}
