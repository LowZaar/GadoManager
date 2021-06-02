package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;

import classes.Alimentos;
import classes.Racoes;
import classes.Rebanhos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroAlimentacaoController {

	@FXML
	private DatePicker dateDataInicio;

	@FXML
	private DatePicker dateDataFinal;

	@FXML
	private ComboBox<String> comboRebanho;

	@FXML
	private ComboBox<String> comboRacao;

	@FXML
	private TextArea txtObservacoes;

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

		// Combo Rebanho
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRE = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa",
				user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRE) {
			comboRebanho.getItems().add(rebanhos.getNome());
		}

		// Combo Racao
		DAOHibernate<Racoes> daoRA = new DAOHibernate<>(Racoes.class);
		List<Racoes> queryRA = daoRA.getAll();
		daoRA.closeAll();
		for (Racoes racoes : queryRA) {
			comboRacao.getItems().add(racoes.getDescricao());
		}
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

	private Racoes findRacao(String racaoDescricao) {
		DAOHibernate<Racoes> daoRA = new DAOHibernate<>(Racoes.class);
		Racoes racao = daoRA.getFirst("selectRacaobyDescricao", "descricao", racaoDescricao);

		if (racao == null) {
			return null;
		} else {
			return racao;
		}
	}

	public Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}
	
	@FXML
	public void salvar() {
		
		LocalDate dataInicial = dateDataInicio.getValue();
		LocalDate dataFinal = dateDataFinal.getValue();
		
		Date date1 = localDateToDate(dataInicial);
		Date date2 = localDateToDate(dataFinal);
		Rebanhos rebanho = findRebanho(comboRebanho.getValue());
		Racoes racao = findRacao(comboRacao.getValue());
		String observacao = txtObservacoes.getText(); 
		
		Alimentos alimento = new Alimentos(rebanho, date1, date2, racao, observacao);
		
		DAOHibernate<Alimentos> daoA = new DAOHibernate<>(Alimentos.class);
		daoA.beginTransaction().save(alimento).commitTransaction().closeAll();
		
		Notifications.create().title("Alerta").text("Nova rotina de Alimentação adicionada com sucesso!")
		.showConfirm();
		
		dateDataInicio.setValue(null);
		dateDataFinal.setValue(null);
		comboRebanho.setValue(null);
		comboRacao.setValue(null);
		txtObservacoes.clear();
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
