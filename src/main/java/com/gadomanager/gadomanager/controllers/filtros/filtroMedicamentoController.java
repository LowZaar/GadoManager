package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;

import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class filtroMedicamentoController {
	@FXML
	private TextArea txtAPrincipioAtivo;
	
	@FXML
	private TextField txtNome;

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
		String desc = txtAPrincipioAtivo.getText();
		
		String nome = txtNome.getText();
		
		String sql = "SELECT * from Medicamentos ";

		sql += "WHERE 1=1 ";

		if (!desc.isEmpty()) {
			sql += "AND medicamentos.principioAtivo LIKE '%" + desc + "%' ";
		
		}
		
		if (!nome.isEmpty()) {
			sql += "AND medicamentos.nome LIKE '%" + nome + "%' ";
		
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
				Medicamentos medc = new Medicamentos();

				String descQ = queryResult.getString("nome");
				medc.setNome(descQ);

				result.add(medc);

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
