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

import classes.Vacina;
import eventos.EventosSaudeVacina;

public class eventoVacinaController {

	@FXML
	private TableView2<Vacina> tableVacina;

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

	@FXML
	public void salvar() {
		int index = tableVacina.getSelectionModel().getSelectedIndex();
		
		
		EventosSaudeVacina eventosSaudeVacina = new EventosSaudeVacina();
		
		Vacina vacina = tableVacina.getItems().get(index);
		eventosSaudeVacina.setIdVacina(vacina);
		
		String lote = txtLote.getText();
		eventosSaudeVacina.setLote(lote);
		
		String observacoes = txtAObservacoes.getText();
		eventosSaudeVacina.setObservacoes(observacoes);
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
		
		cadastroEventoSaudeController.setEventoVac(eventosSaudeVacina);
	}

	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
