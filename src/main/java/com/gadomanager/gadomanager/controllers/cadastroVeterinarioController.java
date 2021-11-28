package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Veterinario;
import com.gadomanager.gadomanager.utils.DAOHibernate;
import com.gadomanager.gadomanager.utils.TextFieldFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cadastroVeterinarioController {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtRG;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCRMV;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnAtualizar;

	private Boolean editMode;

	private Veterinario veterinario;

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
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

			DAOHibernate<Veterinario> daoVet = new DAOHibernate<Veterinario>(Veterinario.class);

			Veterinario vetEdit = daoVet.getAllById(veterinario.getIdVeterinario());

			String nome = txtNome.getText();
			vetEdit.setNome(nome);

			String rg = txtRG.getText();
			vetEdit.setRg(rg);

			String cpf = txtCPF.getText();
			vetEdit.setCpf(cpf);

			String crmv = txtCRMV.getText();
			vetEdit.setCrmv(crmv);

			daoVet.beginTransaction().update(vetEdit).commitTransaction().closeAll();
			
			Notifications.create().title("Alerta").text("Veterinario(a) alterado com sucesso!").showConfirm();

		} else {

			String nome = txtNome.getText();
			String rg = txtRG.getText();
			String cpf = txtCPF.getText();
			String crmv = txtCRMV.getText();

			DAOHibernate<Veterinario> daoV = new DAOHibernate<>(Veterinario.class);
			Veterinario vet = new Veterinario(nome, crmv, cpf, rg);

			daoV.beginTransaction().save(vet).commitTransaction().closeAll();

			Notifications.create().title("Alerta").text("Veterinario(a) criado com sucesso!").showConfirm();
		}
	}

	public void populateFields(Veterinario vet) {

		setVeterinario(vet);

		txtNome.setText(vet.getNome());
		txtRG.setText(vet.getRg());
		txtCPF.setText(vet.getCpf());
		txtCRMV.setText(vet.getCrmv());

	}


	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}

	@FXML
	private void formatRG() {

		TextFieldFormatter RGmask = new TextFieldFormatter();
		RGmask.setMask("###########");
		RGmask.setCaracteresValidos("0123456789");
		RGmask.setTf(txtRG);
		RGmask.formatter();
	}

	@FXML
	private void formatCPF() {

		TextFieldFormatter CPFmask = new TextFieldFormatter();
		CPFmask.setMask("###.###.###-##");
		CPFmask.setCaracteresValidos("0123456789");
		CPFmask.setTf(txtCPF);
		CPFmask.formatter();
	}

}
