package controllers;

import org.controlsfx.control.Notifications;

import classes.Vacina;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroVacinaController {
	
	@FXML
	private TextArea txtADescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	
	@FXML
	public void salvar() {
		
		String Descricao = txtADescricao.getText();
		
		DAOHibernate<Vacina> daoV = new DAOHibernate<>(Vacina.class);
		
		Vacina vac = new Vacina(Descricao);
		
		daoV.beginTransaction().save(vac).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Vacina adicionada com sucesso").showConfirm();
		
		txtADescricao.clear();
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
