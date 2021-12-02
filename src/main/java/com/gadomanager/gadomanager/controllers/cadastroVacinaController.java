package com.gadomanager.gadomanager.controllers;

import java.util.Optional;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Vacina;
import com.gadomanager.gadomanager.repos.VacinaRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

@Component
public class cadastroVacinaController {
	
	@Autowired
	private VacinaRepository repoVacina;
	
	@FXML
	private TextArea txtADescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Boolean editMode;
	
	private Vacina vacina;
	
	private Vacina vacinaEdit;
	
	public Vacina getVacinaEdit() {
		return vacinaEdit;
	}

	public void setVacinaEdit(Vacina vacinaEdit) {
		this.vacinaEdit = vacinaEdit;
	}

	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
	}
	
	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	
	public void populateFields(Vacina vac) {

		setVacina(vac);

		txtADescricao.setText(vac.getDescricao());

	}
	
	
	@FXML
	public void salvar() {
		
		if (editMode) {

			vacina = this.getVacina();

			Optional<Vacina> vacinaopt = repoVacina.findById(vacinaEdit.getIdVacina());
			Vacina vacinaEdit = vacinaopt.get();
			
			String desc = txtADescricao.getText();
			vacinaEdit.setDescricao(desc);
			
			repoVacina.save(vacinaEdit);
			

			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();
			
			Notifications.create().title("Alerta").text("Vacina editada com sucesso").showConfirm();
			

		}else { 
		
		String Descricao = txtADescricao.getText();
		Vacina vac = new Vacina(Descricao);
		
		repoVacina.save(vac);
		
		Notifications.create().title("Alerta").text("Vacina adicionada com sucesso").showConfirm();
		
		txtADescricao.clear();
		}
	}
	
	public Vacina getVacina() {
		return vacina;
	}

	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
