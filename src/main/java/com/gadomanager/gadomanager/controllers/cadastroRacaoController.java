package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Racoes;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cadastroRacaoController {

	@FXML
	private TextArea txtAObservacao;
	
	@FXML
	private TextField txtDescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void salvar() {
		
		String observacao = txtAObservacao.getText();
		
		String descricao = txtDescricao.getText();
		
		DAOHibernate<Racoes> daoR = new DAOHibernate<>(Racoes.class);
		
		Racoes racao = new Racoes(descricao,observacao);
		
		daoR.beginTransaction().save(racao).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Nova Ração adicionada com sucesso!").showConfirm();
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
