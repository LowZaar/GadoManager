package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Vacina;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class cadastroVacinaController {
	
	@FXML
	private TextArea txtADescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Boolean editMode = false;
	
	private Vacina vacina;
	
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

			DAOHibernate<Vacina> daoV = new DAOHibernate<>(Vacina.class);

			Vacina vacinaEdit = daoV.getAllById(vacina.getIdVacina());

			String desc = txtADescricao.getText();
			vacinaEdit.setDescricao(desc);
			
			daoV.beginTransaction().update(vacinaEdit).commitTransaction().closeAll();

			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();
			
			Notifications.create().title("Alerta").text("Vacina editada com sucesso").showConfirm();
			

		}else { 
		
		String Descricao = txtADescricao.getText();
		
		DAOHibernate<Vacina> daoV = new DAOHibernate<>(Vacina.class);
		
		Vacina vac = new Vacina(Descricao);
		
		daoV.beginTransaction().save(vac).commitTransaction().closeAll();
		
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
