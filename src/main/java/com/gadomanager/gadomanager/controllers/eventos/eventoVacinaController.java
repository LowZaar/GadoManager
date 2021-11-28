package com.gadomanager.gadomanager.controllers.eventos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

import org.controlsfx.control.tableview2.TableView2;

import com.gadomanager.gadomanager.classes.Vacina;
import com.gadomanager.gadomanager.eventos.EventosSaudeVacina;
import com.gadomanager.gadomanager.utils.DAOHibernate;

public class eventoVacinaController {

	@FXML
	private TableView2<Vacina> tableVacina;

	@FXML
	private TextField txtLote;

	@FXML
	private TextField txtTipoAplicacao;

	@FXML
	private TextArea txtAObservacoes;

	@FXML
	private Button btnFechar;

	private cadastroEventoSaudeController cadastroEventoSaudeController;

	public void setCadastroEventoSaudeController(cadastroEventoSaudeController cadastroEventoSaudeController) {
		this.cadastroEventoSaudeController = cadastroEventoSaudeController;
	}

	public void populateTable() {

		ObservableList<Vacina> list = FXCollections.observableArrayList();

		DAOHibernate<Vacina> daoVac = new DAOHibernate<>(Vacina.class);
		List<Vacina> query = daoVac.getAll();
		list.addAll(query);

		tableVacina.setItems(list);

		// Colunas
		TableColumn<Vacina, Long> idVacinaCol = new TableColumn<>("ID");
		idVacinaCol.setCellValueFactory(new PropertyValueFactory<>("idVacina"));
		tableVacina.getColumns().add(idVacinaCol);

		TableColumn<Vacina, String> descricaoVacinaCol = new TableColumn<>("Descrição");
		descricaoVacinaCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tableVacina.getColumns().add(descricaoVacinaCol);

	}

	public Vacina getVacina() {
		
		int index = tableVacina.getSelectionModel().getSelectedIndex();

		Vacina vacina = tableVacina.getItems().get(index);

		if (!vacina.equals(null)) {
			return vacina;
		} else {
			return null;
		}

	}

	@FXML
	public void fechar() {
		
		EventosSaudeVacina eventosSaudeVacina = new EventosSaudeVacina();
		
		try {
			Vacina vacina = getVacina();
			eventosSaudeVacina.setIdVacina(vacina);
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		

		String lote = txtLote.getText();
		if (!lote.isEmpty()) {			
			eventosSaudeVacina.setLote(lote);
		}

		String observacoes = txtAObservacoes.getText();
		if (!observacoes.isEmpty()) {
			
			eventosSaudeVacina.setObservacoes(observacoes);
		}

		cadastroEventoSaudeController.setEventoVac(eventosSaudeVacina);

		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();
	}
}
