package com.gadomanager.gadomanager.controllers;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Parametros;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cadastroParamsController {

	@FXML
	private TextField txtAlertaPesagem;
	
	@FXML
	private TextField txtAlertaEngorda;
	
	@FXML
	private TextField txtTaxaEngorda;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Usuarios user;
	
	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	private Parametros params;
	
	
	
	public Parametros getParams() {
		return params;
	}

	public void setParams(Parametros params) {
		this.params = params;
	}

	public void populateParams() {
		DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);
		
		Parametros params = daoParams.getFirst("selectParamsbyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		this.params = params;
		daoParams.closeAll();
		
		txtAlertaPesagem.setText(String.valueOf(params.getAlertaDiasSemPesar()));
		txtAlertaEngorda.setText(String.valueOf(params.getAlertaEngordaDiario()));
		txtTaxaEngorda.setText(String.valueOf(params.getTaxaEngordaDiario()));
	}
	
	@FXML
	public void salvar() {
		
		DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);
		this.params.setAlertaDiasSemPesar(Integer.parseInt(txtAlertaPesagem.getText()));
		this.params.setAlertaEngordaDiario(Integer.parseInt(txtAlertaEngorda.getText()));
		this.params.setTaxaEngordaDiario(Double.parseDouble(txtTaxaEngorda.getText()));
		
		daoParams.beginTransaction().update(params).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Parametros atualizados com sucesso!").showConfirm();
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
