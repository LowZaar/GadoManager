package controllers;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.tableview2.TableView2;

import classes.Bovinos;
import classes.Usuarios;
import controllers.filtros.filtroBovinoController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class consultaController {

	@FXML
	private Button btnFiltro;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnFechar;

	@FXML
	private TableView2<Object> tableConsulta;

	@FXML
	private SplitMenuButton menuConsulta;

	@FXML
	private MenuItem menuConsultaBovino;

	@FXML
	private TextField txtFiltro;

	@FXML
	private MenuItem menuConsultaUsuarios;

	private String currentPerspective;

	private ObservableList<Object> perspectiveList;

	private Usuarios user;

	public ObservableList<Object> getPerspectiveList() {
		return perspectiveList;
	}

	public void setPerspectiveList(ObservableList<Object> perspectiveList) {
		this.perspectiveList = perspectiveList;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public String getCurrentPerspective() {
		return currentPerspective;
	}

	public void setCurrentPerspective(String currentPerspective) {
		this.currentPerspective = currentPerspective;
	}

	public void consultarBovino() {

		tableConsulta.getColumns().clear();
		tableConsulta.getItems().clear();
		tableConsulta.setItems(getPerspectiveList());

		// Colunas
		TableColumn<Object, Long> idBrinco = new TableColumn<>("Brinco");
		idBrinco.setCellValueFactory(new PropertyValueFactory<>("idBrinco"));
		tableConsulta.getColumns().add(idBrinco);

		TableColumn<Object, Long> idAssociacao = new TableColumn<>("Associação");
		idAssociacao.setCellValueFactory(new PropertyValueFactory<>("idAssociacao"));
		tableConsulta.getColumns().add(idAssociacao);

		TableColumn<Object, String> nomeCol = new TableColumn<>("Nome");
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableConsulta.getColumns().add(nomeCol);

		TableColumn<Object, String> racaCol = new TableColumn<>("Raça");
		racaCol.setCellValueFactory(
				info -> new SimpleStringProperty(((Bovinos) info.getValue()).getIdRaca().getNomeRaca()));
		tableConsulta.getColumns().add(racaCol);

		TableColumn<Object, Character> sexoCol = new TableColumn<>("Sexo");
		sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		tableConsulta.getColumns().add(sexoCol);

		TableColumn<Object, String> rebanhoCol = new TableColumn<>("Rebanho");
		rebanhoCol.setCellValueFactory(
				info -> new SimpleStringProperty(((Bovinos) info.getValue()).getIdRebanho().getNome()));
		tableConsulta.getColumns().add(rebanhoCol);

		TableColumn<Object, String> categoriaCol = new TableColumn<>("Categoria");
		categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		tableConsulta.getColumns().add(categoriaCol);

		TableColumn<Object, Date> nascimentoCol = new TableColumn<>("Data Nascimento");
		nascimentoCol.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		tableConsulta.getColumns().add(nascimentoCol);

		TableColumn<Object, Date> morteCol = new TableColumn<>("Data Morte");
		morteCol.setCellValueFactory(new PropertyValueFactory<>("dataMorte"));
		tableConsulta.getColumns().add(morteCol);

	}

	private ObservableList<Object> getBovinos() {
		ObservableList<Object> list = FXCollections.observableArrayList();

		DAOHibernate<Bovinos> daoB = new DAOHibernate<Bovinos>(Bovinos.class);
		List<Bovinos> query = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresas_Pessoa());
		list.addAll(query);

		return list;
	}

	@FXML
	public void bovinoClick() {
		setCurrentPerspective("Bovinos");

		setPerspectiveList(getBovinos());
		consultarBovino();
	}

	@FXML
	public void consultarUsuarios() {

		setCurrentPerspective("Usuarios");
		tableConsulta.getColumns().clear();
		tableConsulta.getItems().clear();

		setPerspectiveList(getUsuarios());

		tableConsulta.setItems(getPerspectiveList());

		// Colunas
		TableColumn<Object, String> nomeCol = new TableColumn<>("Nome");
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableConsulta.getColumns().add(nomeCol);

		TableColumn<Object, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableConsulta.getColumns().add(emailCol);

		TableColumn<Object, String> usuarioCol = new TableColumn<>("Usuario");
		usuarioCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		tableConsulta.getColumns().add(usuarioCol);

		TableColumn<Object, String> senhaCol = new TableColumn<>("Senha");
		senhaCol.setCellValueFactory(new PropertyValueFactory<>("senha"));
		tableConsulta.getColumns().add(senhaCol);

		TableColumn<Object, String> usuarioMestreCol = new TableColumn<>("Usuario Mestre");
		usuarioMestreCol.setCellValueFactory(info -> {
			boolean userMestre = ((Usuarios) info.getValue()).isUsuarioMestre();
			String resultado;
			if (userMestre) {
				resultado = "Sim";
			} else {
				resultado = "Não";
			}
			return new ReadOnlyStringWrapper(resultado);
		});
		tableConsulta.getColumns().add(usuarioMestreCol);
	}

	private ObservableList<Object> getUsuarios() {
		ObservableList<Object> list = FXCollections.observableArrayList();

		DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);
		List<Usuarios> query = daoUser.getAllByNamedQuery("selectUserbyEmpresa", "empresa",
				user.getIdEmpresas_Pessoa());
		list.addAll(query);

		return list;
	}

	@FXML
	public void filtrar() throws Exception {

		URL fxmlEdit;
		FXMLLoader loader = new FXMLLoader();
		Stage editStage = new Stage();
		String perspectiva = getCurrentPerspective();

		if (perspectiva == "Bovinos") {
			fxmlEdit = getClass().getResource("/fxml/FiltroDeBovino.fxml");
			loader.setLocation(fxmlEdit);
			Parent editP = loader.load();
			Scene editScene = new Scene(editP);
			editStage.setScene(editScene);
			filtroBovinoController filtroBovinoController = loader.getController();
			filtroBovinoController.setUser(user);
			editStage.show();
		}
//		if (perspectiva == "Usuarios") {
//			fxmlEdit = getClass().getResource("/fxml/FiltroDeUsuario.fxml");
//			loader.setLocation(fxmlEdit);
//			Parent editP = loader.load();
//			Scene editScene = new Scene(editP);
//			editStage.setScene(editScene);
//			//TODO filtro de usuarios
//		}
	}
}
