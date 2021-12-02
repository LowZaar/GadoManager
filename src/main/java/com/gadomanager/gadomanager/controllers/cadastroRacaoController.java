package com.gadomanager.gadomanager.controllers;

import java.util.Optional;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Racoes;
import com.gadomanager.gadomanager.repos.RacoesRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class cadastroRacaoController {
	
	@Autowired
	private RacoesRepository repoRacao;

	@FXML
	private TextArea txtAObservacao;
	
	@FXML
	private TextField txtDescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	

	private Boolean editMode;
	
	private Racoes racao;

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	
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
			
			Optional<Racoes> racopt = repoRacao.findById(racao.getIdRacao());
			Racoes racEdit = racopt.get();

			String observacao = txtAObservacao.getText();
			racEdit.setObservacao(observacao);

			String descricao = txtDescricao.getText();
			racEdit.setDescricao(descricao);

			repoRacao.save(racEdit);
			
		} else {
			
		String observacao = txtAObservacao.getText();
		
		String descricao = txtDescricao.getText();
		
		Racoes racao = new Racoes(descricao,observacao);
		
		repoRacao.save(racao);
		
		Notifications.create().title("Alerta").text("Nova Ração adicionada com sucesso!").showConfirm();
      
		}
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
