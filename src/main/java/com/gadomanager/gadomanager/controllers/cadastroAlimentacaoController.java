package com.gadomanager.gadomanager.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Alimentos;
import com.gadomanager.gadomanager.classes.Racoes;
import com.gadomanager.gadomanager.classes.Rebanhos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.repos.AlimentoRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

@Component
public class cadastroAlimentacaoController {

	@Autowired
	private AlimentoRepository aliRepo;

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
	
	private Boolean editMode;
	
	private Alimentos alimentoEdit;

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	public Alimentos getAlimentoEdit() {
		return alimentoEdit;
	}

	public void setAlimentoEdit(Alimentos alimentoEdit) {
		this.alimentoEdit = alimentoEdit;
	}

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
	
	public LocalDate DateToLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
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
		
		
		if (editMode) {
			Optional<Alimentos> aliopt = aliRepo.findById(alimentoEdit.getIdAlimento());
			Alimentos aliEdit = aliopt.get();
			if (aliEdit != null) {
				aliEdit.setDataInicio(date1);
				aliEdit.setDataTermino(date2);
				aliEdit.setIdRebanho(rebanho);
				aliEdit.setIdracao(racao);
				aliEdit.setObservacoes(observacao);
				
				aliRepo.save(aliEdit);
				
				Stage window = (Stage) btnCancelar.getScene().getWindow();
				window.close();
			}
			
		}else {
			Alimentos alimento = new Alimentos(rebanho, date1, date2, racao, observacao);	
			aliRepo.save(alimento);

			Notifications.create().title("Alerta").text("Nova rotina de Alimentação adicionada com sucesso!")
			.showConfirm();
		}
		
		
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

	public void setEdit(boolean EditMode) {
		if (EditMode) {
			this.editMode = true;
		} else {
			this.editMode = false;
		}
		
	}

	public void populateFields(Alimentos alimento) {
		populateCombos();
		
		txtObservacoes.setText(alimento.getObservacoes());
		comboRebanho.getSelectionModel().select(String.valueOf(alimento.getIdRebanho().getNome()));
		comboRacao.getSelectionModel().select(String.valueOf(alimento.getIdracao().getDescricao()));
		dateDataInicio.setValue(DateToLocalDate(alimento.getDataInicio()));
		dateDataFinal.setValue(DateToLocalDate(alimento.getDataTermino()));
		//TO LOCO
		
	}
	
	
	
	
}
