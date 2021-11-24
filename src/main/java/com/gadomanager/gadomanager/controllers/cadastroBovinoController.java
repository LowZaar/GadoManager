package com.gadomanager.gadomanager.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.classes.Rebanhos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

	@FXML
	private Button btnAtualizar;

	private Usuarios user;

	private Bovinos bovino;

	private Boolean editMode;

	public Bovinos getBovino() {
		return bovino;
	}

	public void setBovino(Bovinos bovino) {
		this.bovino = bovino;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
	}

	public void populateCombos() {
		// Combo Sexo
		comboSexo.getItems().addAll("M", "F");
		char masc = 'M';
		char fem = 'F';
		// Combo Bovino Pai e Mãe
		DAOHibernate<Bovinos> daoB = new DAOHibernate<Bovinos>(Bovinos.class);
		List<Bovinos> queryB = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		daoB.closeAll();
		queryB.toString();
		comboBovinoPai.getItems().add("Selecione...");
		comboBovinoMae.getItems().add("Selecione...");
		for (Bovinos bovinos : queryB) {
			Character sexo = bovinos.getSexo();
			if (sexo.equals(masc)) {
				comboBovinoPai.getItems().add(bovinos.getNome());
			} else if (sexo.equals(fem)) {
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
		return Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public LocalDate DateToLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
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
		Bovinos bovino = daoB.getFirst("selectBovinobyNomeEmpresa", "nome", bovinoNome, "empresa",
				user.getIdEmpresas_Pessoa());
		if (bovino == null) {
			return null;
		} else {
			return bovino;
		}
	}

	@FXML
	public void salvar() {

		if (editMode) {

			bovino = this.getBovino();

			Date dataNow = new Date();

			DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);

			Bovinos bovinoEdit = daoB.getAllById(bovino.getIdBovino());

			String categoria = txtCategoria.getText();
			bovinoEdit.setCategoria(categoria);

			String nome = txtNome.getText();
			bovinoEdit.setNome(nome);

			if (!(dateDataMorte.getValue() == null)) {
				Date dataMorte = localDateToDate(dateDataMorte.getValue());
				if (dataMorte.before(dataNow)) {
					bovinoEdit.setDataMorte(dataMorte);
				}
			}
			Long associacao = Long.parseLong(txtAssociacao.getText());
			bovinoEdit.setIdAssociacao(associacao);

			String sex = comboSexo.getValue();
			bovinoEdit.setSexo(sex.charAt(0));

			Racas raca = findRaca(comboRaca.getValue());
			bovinoEdit.setIdRaca(raca);

			if (dateDataNascimento.getValue() == null) {
				Notifications.create().title("Alerta!").text("Data de nascimento vazia!").showError();
			} else {
				Date dataNascimento = localDateToDate(dateDataNascimento.getValue());
				bovinoEdit.setDataNascimento(dataNascimento);
			}

			Long brinco = Long.parseLong(txtBrinco.getText());
			bovinoEdit.setIdBrinco(brinco);

			Rebanhos rebanho = findRebanho(comboRebanho.getValue());
			bovinoEdit.setIdRebanho(rebanho);

			Double pesoNascimento = Double.parseDouble(txtPesoNascimento.getText());
			bovinoEdit.setPesoNascimento(pesoNascimento);

			Bovinos bovinoPai = findBovino(comboBovinoPai.getValue());
			bovinoEdit.setIdBovino_pai(bovinoPai);

			Bovinos bovinoMae = findBovino(comboBovinoMae.getValue());
			bovinoEdit.setIdBovino_mae(bovinoMae);

			bovinoEdit.setIdEmpresaPessoas(user.getIdEmpresas_Pessoa());

			daoB.beginTransaction().update(bovinoEdit).commitTransaction().closeAll();

			Stage window = (Stage) btnCancelar.getScene().getWindow();
			window.close();

		} else {

			Date dataNow = new Date();

			Bovinos bovino = new Bovinos();

			String categoria = txtCategoria.getText();
			bovino.setCategoria(categoria);

			String nome = txtNome.getText();
			bovino.setNome(nome);

			if (!(dateDataMorte.getValue() == null)) {
				Date dataMorte = localDateToDate(dateDataMorte.getValue());
				if (dataMorte.before(dataNow)) {
					bovino.setDataMorte(dataMorte);
				}
			}
			Long associacao = Long.parseLong(txtAssociacao.getText());
			bovino.setIdAssociacao(associacao);

			String sex = comboSexo.getValue();
			bovino.setSexo(sex.charAt(0));

			Racas raca = findRaca(comboRaca.getValue());
			bovino.setIdRaca(raca);

			if (dateDataNascimento.getValue() == null) {
				Notifications.create().title("Alerta!").text("Data de nascimento vazia!").showError();
				return;
			} else {
				Date dataNascimento = localDateToDate(dateDataNascimento.getValue());
				bovino.setDataNascimento(dataNascimento);
			}

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

			bovino.setIdEmpresaPessoas(user.getIdEmpresas_Pessoa());

			DAOHibernate<Bovinos> daoBovino = new DAOHibernate<>(Bovinos.class);
			daoBovino.beginTransaction().save(bovino).commitTransaction().closeAll();

			txtCategoria.clear();
			txtNome.clear();
			txtAssociacao.clear();
			txtBrinco.clear();
			txtPesoNascimento.clear();
			dateDataNascimento.setValue(null);
			dateDataMorte.setValue(null);
			comboRaca.getItems().clear();
			comboRebanho.getItems().clear();
			comboSexo.getItems().clear();
			comboBovinoPai.getItems().clear();
			comboBovinoMae.getItems().clear();

			populateCombos();

			Notifications.create().title("Alerta")
					.text("Bovino Criado com sucesso!" + "\n" + "Nome: " + bovino.getNome() + "\n" + "Sexo: "
							+ bovino.getSexo() + "\n" + "Brinco: #" + bovino.getIdBrinco() + "\n" + "Rebanho: "
							+ bovino.getIdRebanho().getNome())
					.showConfirm();
		}

	}

	public void populateFields(Bovinos bovino) {
		populateCombos();

		txtCategoria.setText(bovino.getCategoria());
		txtNome.setText(bovino.getNome());
		if (!(bovino.getDataMorte() == null)) {
			dateDataMorte.setValue(DateToLocalDate(bovino.getDataMorte()));
		}
		txtAssociacao.setText(String.valueOf(bovino.getIdAssociacao()));
		comboSexo.getSelectionModel().select(String.valueOf(bovino.getSexo()));
		comboRaca.getSelectionModel().select(bovino.getIdRaca().getNomeRaca());
		dateDataNascimento.setValue(DateToLocalDate(bovino.getDataNascimento()));
		txtBrinco.setText(String.valueOf(bovino.getIdBrinco()));
		comboRebanho.getSelectionModel().select(bovino.getIdRebanho().getNome());
		txtPesoNascimento.setText(String.valueOf(bovino.getPesoNascimento()));
		if (!(bovino.getIdBovino_pai() == null)) {

			comboBovinoPai.getSelectionModel().select(bovino.getIdBovino_pai().getNome());
		}
		if (!(bovino.getIdBovino_mae() == null)) {

			comboBovinoPai.getSelectionModel().select(bovino.getIdBovino_mae().getNome());
		}

	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
