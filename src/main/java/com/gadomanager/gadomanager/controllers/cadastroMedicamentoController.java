package com.gadomanager.gadomanager.controllers;

import java.util.Optional;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.repos.MedicamentoRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class cadastroMedicamentoController {

	@FXML
	private TextField txtNome;

	@FXML
	private TextArea txtAPrincipioAtivo;

	@FXML
	private Button btnSalvar;

	private Boolean editMode = false;

	private Medicamentos medicamentos;

	@FXML
	private Button btnCancelar;

	@Autowired
	private MedicamentoRepository medRepo;
	
	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
	}

	public Medicamentos getMedicamento() {
		return medicamentos;
	}

	public void setMedicamento(Medicamentos medicamentos) {
		this.medicamentos = medicamentos;
	}

	public void populateFields(Medicamentos medicamentos) {
		setMedicamento(medicamentos);

		txtNome.setText(medicamentos.getNome());
		txtAPrincipioAtivo.setText(medicamentos.getPrincipioAtivo());

	}

	@FXML
	public void salvar() {
		if (editMode) {

			Medicamentos medicamentos = this.getMedicamento();
			
			Optional<Medicamentos> aliopt = medRepo.findById(medicamentos.getIdMedicamento().toString());
			
			Medicamentos medicEdit = aliopt.get();
			

			String desc = txtAPrincipioAtivo.getText();
			medicEdit.setPrincipioAtivo(desc);

			String nome = txtNome.getText();
			medicEdit.setPrincipioAtivo(nome);

			
			
			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();

			Notifications.create().title("Alerta").text("Medicação editada com sucesso").showConfirm();

		} else {

			String nome = txtNome.getText();
			String principioAtivo = txtAPrincipioAtivo.getText();

			DAOHibernate<Medicamentos> daoM = new DAOHibernate<>(Medicamentos.class);

			Medicamentos med = new Medicamentos(nome, principioAtivo);

			daoM.beginTransaction().save(med).commitTransaction().closeAll();

			Notifications.create().title("Alerta").text("Medicação " + med.getNome() + " adicionado com sucesso")
					.showConfirm();

			txtNome.clear();
			txtAPrincipioAtivo.clear();

		}

	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}

}
