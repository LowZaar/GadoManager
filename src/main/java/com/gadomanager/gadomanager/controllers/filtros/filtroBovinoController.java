package com.gadomanager.gadomanager.controllers.filtros;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.classes.Rebanhos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.controllers.consultaController;
import com.gadomanager.gadomanager.utils.DAODatabase;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
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
	
	private consultaController consultaController;

	private Usuarios user;

		
	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void setConsultaController(consultaController consultaController) {
		this.consultaController = consultaController;
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
	public void filtrar() throws Exception {

		String sql = "SELECT * from bovinos ";

		sql += "WHERE 1=1 ";
		if (!txtNome.getText().isEmpty()) {
			String nome = txtNome.getText();
			sql += "AND bovinos.nome LIKE '%" + nome + "%' ";

		}
		if (!txtIDAssociacao.getText().isEmpty()) {
			Long associacao = Long.parseLong(txtIDAssociacao.getText());
			sql += "AND bovinos.idAssociacao = " + associacao + " ";
		}
		if (!txtIdBrinco.getText().isEmpty()) {
			Long brinco = Long.parseLong(txtIdBrinco.getText());
			sql += "AND bovinos.idBrinco = " + brinco + " ";
		}
		if (!(comboSexo.getValue() == null)) {
			String sexo = comboSexo.getValue();
			sql += "AND bovinos.sexo = '" + sexo + "' ";

		}
		if (!(comboRebanho.getValue() == null)) {

			DAOHibernate<Rebanhos> daoRe = new DAOHibernate<>(Rebanhos.class);
			Rebanhos rebanhoquery = daoRe.getFirst("selectRebanhobyNomeEmpresa", "nome", comboRebanho.getValue(),
					"empresa", user.getIdEmpresas_Pessoa());
			daoRe.closeAll();
			int rebanhoId = rebanhoquery.getIdRebanho().intValue();

			sql += "AND bovinos.idRebanho = " + rebanhoId + " ";
		}
		if (!(comboRaca.getValue() == null)) {

			DAOHibernate<Racas> daoRa = new DAOHibernate<>(Racas.class);
			Racas racaQuery = daoRa.getFirst("selectRacasbyNome", "nome", comboRaca.getValue());
			daoRa.closeAll();
			int racaId = racaQuery.getIdRaca().intValue();

			sql += "AND bovinos.idRaca = " + racaId + " ";
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

				Long bovinoPai = queryResult.getLong("idBovino_pai");
				
				Long bovinoMae = queryResult.getLong("idBovino_mae");

				DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
				
				Bovinos bovinoPaiObj = daoB.getAllById(bovinoPai);
				Bovinos bovinoMaeObj = daoB.getAllById(bovinoMae);
				
				
				bovino.setIdBovino_pai(bovinoPaiObj);
				bovino.setIdBovino_mae(bovinoMaeObj);

				result.add(bovino);
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
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
