package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.repos.RacasRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class cadastroRacaController {
	
	@Autowired
	private RacasRepository repo;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void salvar() {
		
//		DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
		
		if (txtNome.getText().isEmpty()) {
			
			Notifications.create().title("Cadastro de Raças").text("Nome de raça vazio!!").showError();
		
		}else {
			
//			daoRa.beginTransaction().save(new Racas(txtNome.getText())).commitTransaction().closeAll();
			
			String nome = txtNome.getText();
			
			Racas raca = new Racas(nome);
			
			repo.save(raca);
			
			
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
