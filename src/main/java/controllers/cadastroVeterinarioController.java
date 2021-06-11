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
	private Button btnAtualizar;
	
	private Veterinario veterinario;
	
	
	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

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
	
	public void populateFields(Veterinario vet) {
		
		setVeterinario(vet);
		
		btnSalvar.setDisable(true);
		
		txtNome.setText(vet.getNome());
		txtRG.setText(vet.getRg());
		txtCPF.setText(vet.getCpf());
		txtCRMV.setText(vet.getCrmv());
		
		
	}
	
	@FXML
	public Boolean atualizar() {
		
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
		
		return true;
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
