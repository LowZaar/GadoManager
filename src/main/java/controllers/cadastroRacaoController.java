package controllers;

import org.controlsfx.control.Notifications;

import classes.Racoes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroRacaoController {

	@FXML
	private TextField txtObservacao;
	
	@FXML
	private TextArea txtADescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void salvar() {
		
		String observacao = txtObservacao.getText();
		
		String descricao = txtADescricao.getText();
		
		DAOHibernate<Racoes> daoR = new DAOHibernate<>(Racoes.class);
		
		Racoes racao = new Racoes(descricao,observacao);
		
		daoR.beginTransaction().save(racao).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Nova Racão adicionada com sucesso!").showConfirm();
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
