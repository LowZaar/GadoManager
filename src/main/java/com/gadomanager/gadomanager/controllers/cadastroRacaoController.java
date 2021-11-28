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
	
	private Boolean editMode = false;

	private Racoes racao;
	
	public Racoes getRacoes() {
		return racao;
	}
	
	public void setRacao(Racoes racao) {
		this.racao = racao;
	}
	
	public void populateFields(Racoes rac) {

		setRacao(rac);

		txtAObservacao.setText(rac.getObservacao());
		txtDescricao.setText(rac.getDescricao());
	}
	
	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;

		} else {
			this.editMode = false;
		}
	}
	
	@FXML
	public void salvar() {
		
		if (editMode) {

			DAOHibernate<Racoes> daoRac = new DAOHibernate<Racoes>(Racoes.class);

			Racoes racEdit = daoRac.getAllById(racao.getIdRacao());

			String observacao = txtAObservacao.getText();
			racEdit.setObservacao(observacao);

			String descricao = txtDescricao.getText();
			racEdit.setDescricao(descricao);

			daoRac.beginTransaction().update(racEdit).commitTransaction().closeAll();
		} else {
			
		
		String observacao = txtAObservacao.getText();
		
		String descricao = txtDescricao.getText();
		
		DAOHibernate<Racoes> daoR = new DAOHibernate<>(Racoes.class);
		
		Racoes racao = new Racoes(descricao,observacao);
		
		daoR.beginTransaction().save(racao).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Nova Ração adicionada com sucesso!").showConfirm();
		}
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
