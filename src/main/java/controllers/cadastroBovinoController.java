package controllers;

import java.util.List;

import classes.Bovinos;
import classes.Racas;
import classes.Rebanhos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.DAOHibernate;

public class cadastroBovinoController {

	@FXML
	private TextField txtCategoria;

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker dateDataMorte;

	@FXML
	private TextField comboAssociacao;

	@FXML
	private ComboBox<String> comboSexo;

	@FXML
	private ComboBox<String> comboRaca;

	@FXML
	private DatePicker dateDataNascimento;

	@FXML
	private TextField txtBrinco;
	
	@FXML
	private ComboBox<String> comboRebanho;
	
	@FXML
	private TextField txtPesoNascimento;

	@FXML
	private ComboBox<String> comboBovinoPai;

	@FXML
	private ComboBox<String> comboBovinoMae;

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

	public void populateCombos() {
		// Combo Sexo
		comboSexo.getItems().addAll("M", "F");

		// Combo Bovino Pai e Mãe
		DAOHibernate<Bovinos> daoB = new DAOHibernate<Bovinos>(Bovinos.class);
		List<Bovinos> queryB = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		daoB.closeAll();
		comboBovinoPai.getItems().add("Selecione...");
		comboBovinoMae.getItems().add("Selecione...");
		for (Bovinos bovinos : queryB) {
			if (bovinos.getSexo() == 'M') {
				comboBovinoPai.getItems().add(bovinos.getNome());
			} else if (bovinos.getSexo() == 'F') {
				comboBovinoMae.getItems().add(bovinos.getNome());
			}
		}
		
		// Combo Raça
		DAOHibernate<Racas> daoR = new DAOHibernate<Racas>(Racas.class);
		List<Racas> queryR = daoR.getAll();
		daoR.closeAll();
		for (Racas racas : queryR) {
			comboRaca.getItems().add(racas.getNomeRaca());
		}
		
		//Combo Rebanho
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRe = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRe) {
			comboRebanho.getItems().add(rebanhos.getNome());
		}
		
	}

}
