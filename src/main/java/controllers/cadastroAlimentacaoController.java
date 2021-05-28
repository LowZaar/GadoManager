package controllers;

import java.util.List;

import classes.Racoes;
import classes.Rebanhos;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import utils.DAOHibernate;

public class cadastroAlimentacaoController {
	
	@FXML
	private DatePicker dateInicio;
	
	@FXML
	private DatePicker dateFinal;
	
	@FXML
	private ComboBox<String> comboRebanho;
	
	@FXML
	private ComboBox<String> comboRacao;
	
	@FXML
	private TextArea txtObservacoes;
	
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
	
	public void populateCombos() {
		
		// Combo Rebanho
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRE = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRE) {
			comboRebanho.getItems().add(rebanhos.getNome());
		}
		
		// Combo Racao
		DAOHibernate<Racoes> daoRA = new DAOHibernate<>(Racoes.class);
		List<Racoes> queryRA = daoRA.getAll();
		daoRA.closeAll();
		for (Racoes racoes : queryRA) {
			comboRacao.getItems().add(racoes.getDescricao());
		}
	}
	
	public void salvar() {
		
	}
	
	public void cancelar() {
		
	}
}
