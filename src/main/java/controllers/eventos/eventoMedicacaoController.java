package controllers.eventos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DAOHibernate;

import java.util.List;

import org.controlsfx.control.tableview2.TableView2;

import classes.Medicamentos;

import eventos.EventosSaudeMedicacao;

public class eventoMedicacaoController {

	@FXML
	private TableView2<Medicamentos> tableMedicamento;

	@FXML
	private TextField txtLote;

	@FXML
	private TextField txtTipoAplicação;

	@FXML
	private TextArea txtAObservacoes;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

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

	public void salvar() {

		int index = tableMedicamento.getSelectionModel().getSelectedIndex();

		Medicamentos medicacao = tableMedicamento.getItems().get(index);

		EventosSaudeMedicacao eventoMedicacao = new EventosSaudeMedicacao();
		if (!txtDiasTratamento.getText().isEmpty()) {
			eventoMedicacao.setDiasTratamento(Integer.parseInt(txtDiasTratamento.getText()));
		}
		eventoMedicacao.setIdMedicamento(medicacao);
		eventoMedicacao.setLote(txtLote.getText());
		eventoMedicacao.setPosologia(txtPosologia.getText());
		eventoMedicacao.setTipoAplicacao(txtTipoAplicação.getText());
		eventoMedicacao.setObservacoes(txtAObservacoes.getText());

		cadastroEventoSaudeController.setEventoMed(eventoMedicacao);
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
