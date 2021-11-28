package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;

import com.gadomanager.gadomanager.classes.Racoes;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class filtroRacoesController {

	@FXML
	private TextArea txtObservacao;

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
		String obs = txtObservacao.getText();
		String desc = txtDescricao.getText();

		String sql = "SELECT * from racoes ";

		sql += "WHERE 1=1 ";
		if (!obs.isEmpty()) {
			sql += "AND racoes.Observacao LIKE '%" + obs + "%' ";
		}
		if (!desc.isEmpty()) {
			sql += "AND racoes.descricao = " + desc + " ";
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
				Racoes rac = new Racoes();

				String obsQ = queryResult.getString("Observacao");
				rac.setObservacao(obsQ);

				String descQ = queryResult.getString("descricao");
				rac.setDescricao(descQ);

				result.add(rac);

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