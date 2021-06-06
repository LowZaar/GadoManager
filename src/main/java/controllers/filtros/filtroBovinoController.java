package controllers.filtros;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Racas;
import classes.Rebanhos;
import classes.Usuarios;
import controllers.consultaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DAODatabase;
import utils.DAOHibernate;

public class filtroBovinoController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIDAssociacao;

    @FXML
    private TextField txtIdBrinco;

    @FXML
    private ComboBox<String> comboSexo;

    @FXML
    private ComboBox<String> comboRebanho;

    @FXML
    private ComboBox<String> comboRaca;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFiltrar;

    private Usuarios user;
    
    public Usuarios getUser() {
		return user;
	}



	public void setUser(Usuarios user) {
		this.user = user;
	}



	@FXML
    void initialize() {
    	
    	comboSexo.getItems().addAll("M", "F");
    	
    	DAOHibernate<Racas> daoR = new DAOHibernate<Racas>(Racas.class);
		List<Racas> queryR = daoR.getAll();
		daoR.closeAll();
		for (Racas racas : queryR) {
			comboRaca.getItems().add(racas.getNomeRaca());
		}
		
		DAOHibernate<Rebanhos> daoRE = new DAOHibernate<Rebanhos>(Rebanhos.class);
		List<Rebanhos> queryRe = daoRE.getAllByNamedQuery("selectRebanhobyEmpresa", "empresa",
				user.getIdEmpresas_Pessoa());
		daoRE.closeAll();
		for (Rebanhos rebanhos : queryRe) {
			comboRebanho.getItems().add(rebanhos.getNome());
		}
		
		
    }
	@FXML
	public void filtrar() throws Exception {
		
		String sql = "Select * from Bovinos ";
		List<Object> atributos = new ArrayList<>();
		
		if (!txtNome.getText().isEmpty()) {
			String nome = txtNome.getText();
			sql += "WHERE Bovinos.nome LIKE %?% AND"; 
			atributos.add(nome);
			
		}
		if (!txtIDAssociacao.getText().isEmpty()) {
			int associacao = Integer.parseInt(txtIDAssociacao.getText()); 
			sql += "WHERE Bovinos.idAssociacao = ? AND ";
			atributos.add(associacao);
		}
		if (!txtIdBrinco.getText().isEmpty()) {
			int brinco = Integer.parseInt(txtIdBrinco.getText());
			sql += "WHERE Bovinos.idBrinco = ? AND ";
			atributos.add(brinco);
		}
		if (!comboSexo.getValue().isEmpty()) {
			String sexo = comboSexo.getValue();
			sql += "WHERE Bovinos.sexo = ? AND ";
			atributos.add(sexo);
			
		}
		if (!comboRebanho.getValue().isEmpty()) {
			
			DAOHibernate<Rebanhos> daoRe = new DAOHibernate<>(Rebanhos.class);
			Rebanhos rebanhoquery = daoRe.getFirst("selectRebanhobyNomeEmpresa", "nome", comboRebanho.getValue(), 
					"empresa", user.getIdEmpresas_Pessoa());
			daoRe.closeAll();
			int rebanhoId = rebanhoquery.getIdRebanho().intValue();
			
			sql += "WHERE Bovinos.idRebanho = ? AND "; 
			atributos.add(rebanhoId);
		}
		if (!comboRaca.getValue().isEmpty()) {
			
			DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
			Racas racaQuery = daoRa.getFirst("selectRacasbyNome", "nome", comboRaca.getValue());
			daoRa.closeAll();
			int racaId = racaQuery.getIdRaca().intValue();
			
			sql += "WHERE Bovinos.idRaca = ? AND ";
			atributos.add(racaId);
		}
		sql += "WHERE 1+1";
		
		DAODatabase daoJDBC = new DAODatabase();
		ResultSet queryResult = daoJDBC.select(sql, atributos);
		ObservableList<Object> result = FXCollections.observableArrayList();
		while (queryResult.next()) {
			String Nome = queryResult.getString("nome");
			result.add(Nome);
		
			int Associacao = queryResult.getInt("idAssociacao");
			result.add(Associacao);
			
			int Brinco = queryResult.getInt("idBrinco");
			result.add(Brinco);
			
			String sexo = queryResult.getString("sexo");
			result.add(sexo);
			
			int rebanho = queryResult.getInt("idRebanho");
			DAOHibernate<Rebanhos> daoRe = new DAOHibernate<>(Rebanhos.class);
			Rebanhos rebanhoNome = daoRe.getFirst("selectRebanhobyId", "id", rebanho);
			daoRe.closeAll();
			result.add(rebanhoNome.getNome());
			
			int raca = queryResult.getInt("idRaca");
			DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
			Racas racaNome = daoRa.getFirst("selectRacabyId", "id", raca);
			daoRa.closeAll();
			result.add(racaNome.getNomeRaca());
		}
		
		
		consultaController consultaController = new consultaController();
		consultaController.setPerspectiveList(result);
		consultaController.consultarBovino();
		
		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
