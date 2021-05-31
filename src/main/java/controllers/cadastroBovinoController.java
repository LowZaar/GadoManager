package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;

import classes.Bovinos;
import classes.Racas;
import classes.Rebanhos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroBovinoController {

	@FXML
	private TextField txtCategoria;

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker dateDataMorte;

	@FXML
	private TextField txtAssociacao;

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

		// Combo Rebanho
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRe = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa",
				user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRe) {
			comboRebanho.getItems().add(rebanhos.getNome());
		}

	}

	public Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}

	private Rebanhos findRebanho(String rebanhoNome) {
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		Rebanhos rebanho = daoRE.getFirst("selectRebanhobyNomeEmpresa", "nome", rebanhoNome, "empresa",
				user.getIdEmpresas_Pessoa());
		if (rebanho == null) {
			return null;
		} else {
			return rebanho;
		}
	}

	private Racas findRaca(String racaNome) {
		DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
		Racas raca = daoRa.getFirst("selectRacasbyNome", "nome", racaNome);
		if (raca == null) {
			return null;
		} else {
			return raca;
		}
	}
	
	private Bovinos findBovino(String bovinoNome) {
		DAOHibernate<Bovinos> daoB = new DAOHibernate<Bovinos>(Bovinos.class);
		Bovinos bovino = daoB.getFirst("selectBovinobyNomeEmpresa", "nome", bovinoNome, "empresa", user.getIdEmpresas_Pessoa());
		if (bovino == null) {
			return null;
		} else {
			return bovino;
		}
	}
	
	@FXML
	public void salvar() {
		
		Date dataNow = new Date();
		
		Bovinos bovino = new Bovinos();

		String categoria = txtCategoria.getText();
		bovino.setCategoria(categoria);
		
		String nome = txtNome.getText();
		bovino.setNome(nome);
		
		Date dataMorte = localDateToDate(dateDataMorte.getValue());
		if (dataMorte.before(dataNow)) {
			bovino.setDataMorte(dataMorte);
		}

		Long associacao = Long.parseLong(txtAssociacao.getText());
		bovino.setIdAssociacao(associacao);
		
		String sex = comboSexo.getValue();
		bovino.setSexo(sex.charAt(0));
		
		Racas raca = findRaca(comboRaca.getValue());
		bovino.setIdRaca(raca);
		
		Date dataNascimento = localDateToDate(dateDataNascimento.getValue());
		bovino.setDataNascimento(dataNascimento);
		
		Long brinco = Long.parseLong(txtBrinco.getText());
		bovino.setIdBrinco(brinco);
		
		Rebanhos rebanho = findRebanho(comboRebanho.getValue());
		bovino.setIdRebanho(rebanho);
		
		Double pesoNascimento = Double.parseDouble(txtPesoNascimento.getText());
		bovino.setPesoNascimento(pesoNascimento);
		
		Bovinos bovinoPai = findBovino(comboBovinoPai.getValue());
		bovino.setIdBovino_pai(bovinoPai);
		
		Bovinos bovinoMae = findBovino(comboBovinoMae.getValue());
		bovino.setIdBovino_mae(bovinoMae);
		
		DAOHibernate<Bovinos> daoBovino = new DAOHibernate<>(Bovinos.class);
		daoBovino.beginTransaction().save(bovino).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Bovino Criado com sucesso!"
				+ "\n"
				+ "Nome: " + bovino.getNome()
				+"\n"
				+"Sexo: " + bovino.getSexo()
				+"\n"
				+"Brinco: #" + bovino.getIdBrinco()
				+"Rebanho: " + bovino.getIdRebanho().getNome());
				
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
