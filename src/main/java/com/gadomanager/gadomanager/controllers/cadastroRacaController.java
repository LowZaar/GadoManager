package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.repos.RacasRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

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

	private Boolean editMode;
	
	private Racas racaEdit;
	
	@FXML
	public void salvar() {
		
		if (editMode) {

			racaEdit = this.getRacaEdit();

			DAOHibernate<Racas> daoRaca = new DAOHibernate<>(Racas.class);

			Racas editedRaca = daoRaca.getAllById(racaEdit.getIdRaca());

			String nome = txtNome.getText();
			editedRaca.setNomeRaca(nome);


			repo.save(editedRaca);
			
			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();

		
		}else if (txtNome.getText().isEmpty()) {
			
			Notifications.create().title("Cadastro de Raças").text("Nome de raça vazio!!").showError();
		
		}else {
			
			String nome = txtNome.getText();
			
			Racas raca = new Racas(nome);
			
			repo.save(raca);
			
			
			Notifications.create().title("Cadastro de Raças").text("Raça "+ txtNome.getText() + " adicionada com sucesso!").showConfirm();
			
			txtNome.clear();
		}
		
	}
	
	public Racas getRacaEdit() {
		return racaEdit;
	}

	public void setRacaEdit(Racas racaEdit) {
		this.racaEdit = racaEdit;
	}

	@FXML
	public void cancelar() {
		
		Stage currentStage = (Stage) btnCancelar.getScene().getWindow();
		currentStage.close();
	}

	public void populateFields(Racas racas) {
		txtNome.setText(racas.getNomeRaca());
		
	}

	public void setEdit(boolean EditMode) {
		
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
		
	}
}
