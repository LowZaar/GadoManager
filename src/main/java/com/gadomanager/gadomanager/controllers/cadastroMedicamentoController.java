package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cadastroMedicamentoController {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextArea txtAPrincipioAtivo;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void salvar() {
		
		String nome = txtNome.getText();
		String principioAtivo = txtAPrincipioAtivo.getText();
		
		
		DAOHibernate<Medicamentos> daoM = new DAOHibernate<>(Medicamentos.class);
		
		Medicamentos med = new Medicamentos(nome,principioAtivo);
		
		daoM.beginTransaction().save(med).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Medicação " + med.getNome() + " adicionado com sucesso").showConfirm();
		
		txtNome.clear();
		txtAPrincipioAtivo.clear();
		
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
