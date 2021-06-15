package controllers.filtros;

import java.sql.ResultSet;

import classes.Veterinario;
import controllers.consultaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAODatabase;

public class filtroVeterinarioController {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtRG;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCRMV;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnFiltrar;

	private consultaController consultaController;
	
	public void setConsultaController(consultaController consultaController) {
		this.consultaController = consultaController;
	}
	
	@FXML
	public void filtrar() throws Exception {
		String nome = txtNome.getText();
		String rg = txtRG.getText();
		String cpf = txtCPF.getText();
		String crmv = txtCRMV.getText();

		boolean validSQL = false;
		String sql = "SELECT * from veterinarios ";

		if (!nome.isEmpty()) {
			validSQL = true;
			sql += "WHERE veterinarios.nome LIKE '%" + nome + "%' AND ";
		}
		if (!rg.isEmpty()) {
			validSQL = true;
			sql += "WHERE veterinarios.rg = " + rg + " AND";
		}
		if (!cpf.isEmpty()) {
			validSQL = true;
			sql += "WHERE veterinarios.cpf = " + cpf + " AND";
		}
		if (!crmv.isEmpty()) {
			validSQL = true;
			sql += "WHERE veterinarios.crmv = " + crmv + " AND";
		}
		if (validSQL) {
			sql += " 1=1";
		}

		DAODatabase daoJDBC = new DAODatabase();
		ResultSet queryResult = null;
		try {
			queryResult = daoJDBC.selectLazy(sql);
		} catch (Exception e) {

		}

		ObservableList<Object> result = FXCollections.observableArrayList();

		if (!(queryResult == null)) {

			while (queryResult.next()) {
				Veterinario vet = new Veterinario();

				String nomeQ = queryResult.getString("nome");
				vet.setNome(nomeQ);

				String rgQ = queryResult.getString("rg");
				vet.setRg(rgQ);

				String cpfQ = queryResult.getString("cpf");
				vet.setCpf(cpfQ);

				String crmvQ = queryResult.getString("crmv");
				vet.setCrmv(crmvQ);

				result.add(vet);

			}

		} else {
			Stage window = (Stage) btnFiltrar.getScene().getWindow();
			window.close();
		}

		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
		consultaController.setPerspectiveList(result);
	}

	@FXML
	public void cancelar() {
		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
	}

}