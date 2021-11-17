package com.gadomanager.gadomanager.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Pesagens;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.repos.PesagemRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class cadastroPesagemController {

	@Autowired
	private PesagemRepository repo;
	
	@FXML
	private TextArea txtAObservacoes;

	@FXML
	private DatePicker dateDataPesagem;

	@FXML
	private TextField txtPeso;

	@FXML
	private ComboBox<String> comboBovino;
	
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

	public void populateCombo() {

		DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);

		List<Bovinos> query = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());

		comboBovino.getItems().add("Selecione...");
		for (Bovinos bovinos : query) {
			comboBovino.getItems().add(bovinos.getNome());
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

	public Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}
	
	@FXML
	private void salvar() {
		String Observacoes = txtAObservacoes.getText();
		Date dataPesagem = localDateToDate(dateDataPesagem.getValue());
		Double peso = Double.parseDouble(txtPeso.getText());
		Bovinos bovino = findBovino(comboBovino.getValue());
		
		Pesagens pesagem = new Pesagens(dataPesagem,peso,Observacoes,bovino);
		repo.save(pesagem);
		
		Notifications.create().title("Pesagens").title("Nova Pesagem criada com sucesso!").showConfirm();
		
	}
	
	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
