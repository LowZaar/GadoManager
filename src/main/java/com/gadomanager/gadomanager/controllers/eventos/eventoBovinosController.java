package com.gadomanager.gadomanager.controllers.eventos;

import java.util.List;

import org.controlsfx.control.tableview2.TableView2;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Rebanhos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.eventos.EventosSaudeBovinos;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class eventoBovinosController {

	@FXML
	private TableView2<Bovinos> tableBovinos;

	@FXML
	private Button btnFechar;

	@FXML
	private ComboBox<String> comboRebanhos;

	private cadastroEventoSaudeController cadastroEventoSaudeController;

	private Rebanhos rebanhoAtual;

	public Rebanhos getRebanhoAtual() {
		return rebanhoAtual;
	}

	public void setRebanhoAtual(Rebanhos rebanhoAtual) {
		this.rebanhoAtual = rebanhoAtual;
	}

	public void setCadastroEventoSaudeController(cadastroEventoSaudeController cadastroEventoSaudeController) {
		this.cadastroEventoSaudeController = cadastroEventoSaudeController;
	}

	private Usuarios user;

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void populateRebanho() {

		tableBovinos.setPlaceholder(new Label("Selecione um Rebanho"));

		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRe = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa",
				user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRe) {
			comboRebanhos.getItems().add(rebanhos.getNome());
		}

	}

	@FXML
	public void populateBovino() {

		tableBovinos.getColumns().clear();

		String nomeRebanho = comboRebanhos.getValue();

		ObservableList<Bovinos> list = FXCollections.observableArrayList();

		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		Rebanhos rebanho = daoRE.getFirst("selectRebanhobyNomeEmpresa", "nome", nomeRebanho, "empresa",
				user.getIdEmpresas_Pessoa());

		daoRE.closeAll();
		setRebanhoAtual(rebanho);

		System.out.println(rebanho.toString());

		DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
		List<Bovinos> bovinos = daoB.getAllByNamedQuery("selectBovinobyRebanho", "rebanho", rebanho);
		daoB.closeAll();
		list.addAll(bovinos);

		System.out.println(bovinos.toString());
		System.out.println("test");

		tableBovinos.setItems(list);

		// Colunas
		TableColumn<Bovinos, Long> idBrincoCol = new TableColumn<>("Brinco");
		idBrincoCol.setCellValueFactory(new PropertyValueFactory<>("idBrinco"));
		tableBovinos.getColumns().add(idBrincoCol);

		TableColumn<Bovinos, String> nomeBovinoCol = new TableColumn<>("Nome");
		nomeBovinoCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableBovinos.getColumns().add(nomeBovinoCol);

		TableColumn<Bovinos, Long> idAssociacao = new TableColumn<>("Associação");
		idAssociacao.setCellValueFactory(new PropertyValueFactory<>("idAssociacao"));
		tableBovinos.getColumns().add(idAssociacao);

		TableColumn<Bovinos, String> bovinoPaiCol = new TableColumn<>("Bovino Pai");
		bovinoPaiCol.setCellValueFactory(info -> {
			Bovinos bovinoPai = ((Bovinos) info.getValue()).getIdBovino_pai();
			String resultado;
			if (!(bovinoPai == null)) {
				resultado = bovinoPai.getNome();
			} else {
				resultado = "";
			}
			return new ReadOnlyStringWrapper(resultado);
		});
		tableBovinos.getColumns().add(bovinoPaiCol);

		TableColumn<Bovinos, String> bovinoMaeCol = new TableColumn<>("Bovino Mãe");
		bovinoMaeCol.setCellValueFactory(info -> {
			Bovinos bovinoMae = ((Bovinos) info.getValue()).getIdBovino_mae();
			String resultado;
			if (!(bovinoMae == null)) {
				resultado = bovinoMae.getNome();
			} else {
				resultado = "";
			}
			return new ReadOnlyStringWrapper(resultado);
		});
		tableBovinos.getColumns().add(bovinoMaeCol);
		tableBovinos.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

	}

	public Bovinos getBovino() {

		int index = tableBovinos.getSelectionModel().getSelectedIndex();

		Bovinos bovino = tableBovinos.getItems().get(index);
		bovino.setIdRebanho(rebanhoAtual);
		if (!bovino.equals(null)) {
			return bovino;
		} else {
			return null;
		}
	}

	@FXML
	public void fechar() {
		EventosSaudeBovinos evento = new EventosSaudeBovinos();
		try {
			Bovinos bovino = getBovino();

			if (!(bovino == null)) {
				evento.setIdBovino(bovino);
				cadastroEventoSaudeController.setEventoBov(evento);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}

		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();

	}
}
