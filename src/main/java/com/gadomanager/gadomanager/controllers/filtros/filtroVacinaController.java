package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;

import com.gadomanager.gadomanager.classes.Vacina;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class filtroVacinaController {

	
	@FXML
	private TextArea txtDescricao;

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
		String desc = txtDescricao.getText();
		
		String sql = "SELECT * from vacinas ";

		sql += "WHERE 1=1 ";

		if (!desc.isEmpty()) {
			sql += "AND vacinas.descricao LIKE '%" + desc + "%' ";
		
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
				Vacina vac = new Vacina();

				String descQ = queryResult.getString("descricao");
				vac.setDescricao(descQ);

				result.add(vac);

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