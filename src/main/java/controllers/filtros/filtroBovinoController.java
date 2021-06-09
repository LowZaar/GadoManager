package controllers.filtros;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import classes.Bovinos;
import classes.Racas;
import classes.Rebanhos;
import classes.Usuarios;

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

	public void populateCombos() {

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
	public ObservableList<Object> filtrar() throws Exception {

		boolean validSQL = false;
		String sql = "select nome," + "idAssociacao," + "idBrinco," + "sexo," + "idRebanho," + "idRaca," + "categoria,"
				+ "dataNascimento, " + "dataMorte from bovinos ";

		if (!txtNome.getText().isEmpty()) {
			validSQL = true;
			String nome = txtNome.getText();
			sql += "WHERE bovinos.nome LIKE '%" + nome + "%' AND ";

		}
		if (!txtIDAssociacao.getText().isEmpty()) {
			validSQL = true;
			Long associacao = Long.parseLong(txtIDAssociacao.getText());
			sql += "WHERE bovinos.idAssociacao = " + associacao + " AND ";
		}
		if (!txtIdBrinco.getText().isEmpty()) {
			validSQL = true;
			Long brinco = Long.parseLong(txtIdBrinco.getText());
			sql += "WHERE bovinos.idBrinco = " + brinco + " AND ";
		}
		if (!(comboSexo.getValue() == null)) {
			validSQL = true;
			String sexo = comboSexo.getValue();
			sql += "WHERE bovinos.sexo = '" + sexo + "' AND ";

		}
		if (!(comboRebanho.getValue() == null)) {
			validSQL = true;

			DAOHibernate<Rebanhos> daoRe = new DAOHibernate<>(Rebanhos.class);
			Rebanhos rebanhoquery = daoRe.getFirst("selectRebanhobyNomeEmpresa", "nome", comboRebanho.getValue(),
					"empresa", user.getIdEmpresas_Pessoa());
			daoRe.closeAll();
			int rebanhoId = rebanhoquery.getIdRebanho().intValue();

			sql += "WHERE bovinos.idRebanho = " + rebanhoId + " AND ";
		}
		if (!(comboRaca.getValue() == null)) {
			validSQL = true;

			DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
			Racas racaQuery = daoRa.getFirst("selectRacasbyNome", "nome", comboRaca.getValue());
			daoRa.closeAll();
			int racaId = racaQuery.getIdRaca().intValue();

			sql += "WHERE bovinos.idRaca = " + racaId + " AND ";
		}
		if (validSQL) {
			sql += "1=1";
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

				Bovinos bovino = new Bovinos();

				String Nome = queryResult.getString("nome");
				bovino.setNome(Nome);

				int Associacao = queryResult.getInt("idAssociacao");
				bovino.setIdAssociacao(Long.valueOf(Associacao));

				int Brinco = queryResult.getInt("idBrinco");
				bovino.setIdBrinco(Long.valueOf(Brinco));

				String sexo = queryResult.getString("sexo");
				bovino.setSexo(sexo.charAt(0));

				Long rebanho = Long.valueOf(queryResult.getInt("idRebanho"));
				DAOHibernate<Rebanhos> daoRe = new DAOHibernate<>(Rebanhos.class);
				Rebanhos rebanhoNome = daoRe.getFirst("selectRebanhobyId", "id", rebanho);
				daoRe.closeAll();
				bovino.setIdRebanho(rebanhoNome);

				Long raca = Long.valueOf(queryResult.getInt("idRaca"));
				DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
				Racas racaNome = daoRa.getFirst("selectRacabyId", "id", raca);
				daoRa.closeAll();
				bovino.setIdRaca(racaNome);

				// resto das infos nao filtradas
				
				Long idBovino = queryResult.getLong("idBovino");
				bovino.setIdBovino(idBovino);
				
				String categoria = queryResult.getString("Categoria");
				bovino.setCategoria(categoria);

				Date dataNascimento = queryResult.getDate("dataNascimento");
				bovino.setDataNascimento(dataNascimento);

				Date dataMorte = queryResult.getDate("dataMorte");
				bovino.setDataMorte(dataMorte);

				Bovinos bovinoPai = (Bovinos) queryResult.getObject("idBovino_pai");
				bovino.setIdBovino_pai(bovinoPai);

				Bovinos bovinoMae = (Bovinos) queryResult.getObject("idBovino_mae");
				bovino.setIdBovino_pai(bovinoMae);
				
				result.add(bovino);
			}
		} else {
			Stage window = (Stage) btnFiltrar.getScene().getWindow();
			window.close();
		}

		Stage window = (Stage) btnFiltrar.getScene().getWindow();
		window.close();
		return result;
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
