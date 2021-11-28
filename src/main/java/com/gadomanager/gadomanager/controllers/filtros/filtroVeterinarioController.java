package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;

import com.gadomanager.gadomanager.classes.Veterinario;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

		String sql = "SELECT * from veterinarios ";

		sql += "WHERE 1=1 ";
		if (!nome.isEmpty()) {
			sql += "AND veterinarios.nome LIKE '%" + nome + "%' ";
		}
		if (!rg.isEmpty()) {
			sql += "AND veterinarios.rg = " + rg + " ";
		}
		if (!cpf.isEmpty()) {
			sql += "AND veterinarios.cpf = " + cpf + " ";
		}
		if (!crmv.isEmpty()) {
			sql += "AND veterinarios.crmv = " + crmv + " ";
		}

		System.out.println(sql);
		
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

			Stage window = (Stage) btnFiltrar.getScene().getWindow();
			window.close();
			consultaController.setPerspectiveList(result);
			
		} else {
			Stage window = (Stage) btnFiltrar.getScene().getWindow();
			window.close();
		}
	}

	@FXML
	public void cancelar() {
		consultaController.setPerspectiveList(null);
		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
	}

}