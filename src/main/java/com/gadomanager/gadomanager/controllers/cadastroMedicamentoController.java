package com.gadomanager.gadomanager.controllers;

import java.util.Optional;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.repos.MedicamentoRepository;

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

	private Boolean editMode;


	private Medicamentos medicamentos;

	@FXML
	private Button btnCancelar;

	@Autowired
	private MedicamentoRepository medRepo;
	
	
	public Boolean getEditMode() {
		return editMode;
	}
	
	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
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
		if (this.editMode) {

			Medicamentos medicamentos = this.getMedicamento();
			
			Optional<Medicamentos> medicopt = medRepo.findById(medicamentos.getIdMedicamento());
			Medicamentos medicEdit = medicopt.get();
			
			String desc = txtAPrincipioAtivo.getText();
			medicEdit.setPrincipioAtivo(desc);

			String nome = txtNome.getText();
			medicEdit.setNome(nome);
			
			medRepo.save(medicEdit);
			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();

			Notifications.create().title("Alerta").text("Medicação editada com sucesso").showConfirm();

		} else {

			String nome = txtNome.getText();
			String principioAtivo = txtAPrincipioAtivo.getText();

			Medicamentos med = new Medicamentos(nome, principioAtivo);

			medRepo.save(med);

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
