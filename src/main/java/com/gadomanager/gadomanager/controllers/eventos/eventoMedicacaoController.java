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

import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.eventos.EventosSaudeMedicacao;
import com.gadomanager.gadomanager.utils.DAOHibernate;

public class eventoMedicacaoController {

	@FXML
	private TableView2<Medicamentos> tableMedicamento;

	@FXML
	private TextField txtLote;

	@FXML
	private TextField txtTipoAplicacao;

	@FXML
	private TextArea txtAObservacoes;

	@FXML
	private Button btnFechar;

	@FXML
	private TextField txtDiasTratamento;

	@FXML
	private TextField txtPosologia;

	private cadastroEventoSaudeController cadastroEventoSaudeController;

	public void setCadastroEventoSaudeController(cadastroEventoSaudeController cadastroEventoSaudeController) {
		this.cadastroEventoSaudeController = cadastroEventoSaudeController;
	}


	public void populateTable() {

		ObservableList<Medicamentos> list = FXCollections.observableArrayList();

		DAOHibernate<Medicamentos> daoMed = new DAOHibernate<>(Medicamentos.class);
		List<Medicamentos> query = daoMed.getAll();
		list.addAll(query);

		tableMedicamento.setItems(list);

		// Colunas
		TableColumn<Medicamentos, Long> idMedicamentoCol = new TableColumn<>("ID");
		idMedicamentoCol.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
		tableMedicamento.getColumns().add(idMedicamentoCol);

		TableColumn<Medicamentos, String> nomeMedicamentoCol = new TableColumn<>("Nome");
		nomeMedicamentoCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableMedicamento.getColumns().add(nomeMedicamentoCol);

		TableColumn<Medicamentos, String> principioAtivocol = new TableColumn<>("Principio Ativo");
		principioAtivocol.setCellValueFactory(new PropertyValueFactory<>("principioAtivo"));
		tableMedicamento.getColumns().add(principioAtivocol);

	}

	public Medicamentos getMedicamento() {
		
		int index = tableMedicamento.getSelectionModel().getSelectedIndex();

		Medicamentos medicacao = tableMedicamento.getItems().get(index);
		
		return medicacao;
	}
	
	@FXML
	public void fechar() {

		EventosSaudeMedicacao eventoMedicacao = new EventosSaudeMedicacao();
		if (!txtDiasTratamento.getText().isEmpty()) {
			eventoMedicacao.setDiasTratamento(Integer.parseInt(txtDiasTratamento.getText()));
		}
		if (!txtLote.getText().isEmpty()) {			
			eventoMedicacao.setLote(txtLote.getText());
		}
		try {
			if (!(getMedicamento() == null)) {			
				eventoMedicacao.setIdMedicamento(getMedicamento());
			}
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		if (!txtPosologia.getText().isEmpty()) {			
			eventoMedicacao.setPosologia(txtPosologia.getText());
		}
		if (!txtTipoAplicacao.getText().isEmpty()) {			
			eventoMedicacao.setTipoAplicacao(txtTipoAplicacao.getText());
		}
		if (!txtAObservacoes.getText().isEmpty()) {			
			eventoMedicacao.setObservacoes(txtAObservacoes.getText());
		}

		cadastroEventoSaudeController.setEventoMed(eventoMedicacao);
		
		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();
	}
}
