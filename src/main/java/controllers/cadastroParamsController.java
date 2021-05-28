package controllers;

import classes.Parametros;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.DAOHibernate;

public class cadastroParamsController {

	@FXML
	private TextField txtAlertaPesagem;
	
	@FXML
	private TextField txtAlertaEngorda;
	
	@FXML
	private TextField txtTaxaEngorda;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Usuarios user;
	
	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void populateParams() {
		DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);
		
		Parametros params = daoParams.getFirst("selectParamsbyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		
		txtAlertaPesagem.setText(String.valueOf(params.getAlertaDiasSemPesar()));
		txtAlertaEngorda.setText(String.valueOf(params.getAlertaEngordaDiario()));
		txtTaxaEngorda.setText(String.valueOf(params.getTaxaEngordaDiario()));
	}
	
	public void salvar() {
		
	}
	
	public void cancelar() {
		
	}
}
