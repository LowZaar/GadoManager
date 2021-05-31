package controllers;

import org.controlsfx.control.Notifications;

import classes.Veterinario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAOHibernate;

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
	public void salvar() {
		
		String nome = txtNome.getText();
		String rg = txtRG.getText();
		String cpf = txtCPF.getText();
		String crmv = txtCRMV.getText();
		
		DAOHibernate<Veterinario> daoV = new DAOHibernate<>(Veterinario.class);
		Veterinario vet = new Veterinario(nome, crmv, cpf, rg);
		
		daoV.beginTransaction().save(vet).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Veterinario(a) criado com sucesso!").showConfirm();
	}
	
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
