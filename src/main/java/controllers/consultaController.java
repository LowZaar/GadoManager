package controllers;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.tableview2.TableView2;

import classes.Bovinos;
import classes.Usuarios;
import classes.Veterinario;
import controllers.filtros.filtroBovinoController;
import controllers.filtros.filtroUsuarioController;
import controllers.filtros.filtroVeterinarioController;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
	private MenuItem menuConsultaUsuarios;

	@FXML
	private MenuItem menuConsultaVeterinarios;

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

		TableColumn<Object, String> bovinoPaiCol = new TableColumn<>("Bovino Pai");
		bovinoPaiCol.setCellValueFactory(info -> {
			Bovinos bovinoPai = ((Bovinos) info.getValue()).getIdBovino_pai();
			String resultado;
			if (!(bovinoPai == null)) {
				resultado = bovinoPai.getNome();
			} else {
				resultado = "";
			}
			return new ReadOnlyStringWrapper(resultado);
		});
		tableConsulta.getColumns().add(bovinoPaiCol);

		TableColumn<Object, String> bovinoMaeCol = new TableColumn<>("Bovino Mãe");
		bovinoPaiCol.setCellValueFactory(info -> {
			Bovinos bovinoMae = ((Bovinos) info.getValue()).getIdBovino_mae();
			String resultado;
			if (!(bovinoMae == null)) {
				resultado = bovinoMae.getNome();
			} else {
				resultado = "";
			}
			return new ReadOnlyStringWrapper(resultado);
		});
		tableConsulta.getColumns().add(bovinoMaeCol);

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
		menuConsulta.setText(currentPerspective);

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
	private void usuarioClick() {

		setCurrentPerspective("Usuarios");

		menuConsulta.setText(currentPerspective);

		setPerspectiveList(getUsuarios());
		consultarUsuarios();
	}

	private void consultarVeterinarios() {

		tableConsulta.getColumns().clear();
		tableConsulta.getItems().clear();
		tableConsulta.setItems(getPerspectiveList());

		// Colunas
		TableColumn<Object, String> nomeCol = new TableColumn<>("Nome");
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableConsulta.getColumns().add(nomeCol);

		TableColumn<Object, String> CPFCol = new TableColumn<>("CPF");
		CPFCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableConsulta.getColumns().add(CPFCol);

		TableColumn<Object, String> CRMVCol = new TableColumn<>("CRMV");
		CRMVCol.setCellValueFactory(new PropertyValueFactory<>("crmv"));
		tableConsulta.getColumns().add(CRMVCol);

		TableColumn<Object, String> RGCol = new TableColumn<>("RG");
		RGCol.setCellValueFactory(new PropertyValueFactory<>("rg"));
		tableConsulta.getColumns().add(RGCol);

	}

	private ObservableList<Object> getVeterinarios() {
		ObservableList<Object> list = FXCollections.observableArrayList();

		DAOHibernate<Veterinario> daoVet = new DAOHibernate<>(Veterinario.class);
		List<Veterinario> query = daoVet.getAll();
		list.addAll(query);

		return list;
	}

	@FXML
	private void veterinariosClick() {
		setCurrentPerspective("Veterinarios");
		menuConsulta.setText(currentPerspective);

		setPerspectiveList(getVeterinarios());
		consultarVeterinarios();
	}

	@FXML
	public void filtrar() throws Exception {

		URL fxmlFiltro;
		FXMLLoader loader = new FXMLLoader();
		Stage filtroStage = new Stage();
		String perspectiva = getCurrentPerspective();

		if (perspectiva == "Bovinos") {
			fxmlFiltro = getClass().getResource("/fxml/FiltroDeBovino.fxml");
			loader.setLocation(fxmlFiltro);
			Parent filtroP = loader.load();
			Scene filtroScene = new Scene(filtroP);
			filtroBovinoController filtroBovinoController = loader.getController();
			filtroBovinoController.setUser(user);
			filtroBovinoController.setConsultaController(this);
			filtroBovinoController.populateCombos();
			filtroStage.initModality(Modality.APPLICATION_MODAL);
			filtroStage.setScene(filtroScene);
			filtroStage.showAndWait();

			if (getPerspectiveList().isEmpty()) {
				getBovinos();
			}
		}
		if (perspectiva == "Usuarios") {
			fxmlFiltro = getClass().getResource("/fxml/FiltroDeUsuario.fxml");
			loader.setLocation(fxmlFiltro);
			Parent filtroP = loader.load();
			Scene filtroScene = new Scene(filtroP);
			filtroUsuarioController filtroUsuarioController = loader.getController();
			filtroUsuarioController.setUser(user);
			filtroUsuarioController.setConsultaController(this);
			filtroStage.initModality(Modality.APPLICATION_MODAL);
			filtroStage.setScene(filtroScene);
			filtroStage.showAndWait();
			
			if (getPerspectiveList().isEmpty()) {
				setPerspectiveList(getUsuarios());
			}

		}
		if (perspectiva == "Veterinarios") {
			fxmlFiltro = getClass().getResource("/fxml/FiltroDeVeterinario.fxml");
			loader.setLocation(fxmlFiltro);
			Parent filtroP = loader.load();
			Scene filtroScene = new Scene(filtroP);
			filtroVeterinarioController filtroVeterinarioController = loader.getController();
			filtroVeterinarioController.setConsultaController(this);
			filtroStage.initModality(Modality.APPLICATION_MODAL);
			filtroStage.setScene(filtroScene);
			filtroStage.showAndWait();
			
			if (getPerspectiveList().isEmpty()) {
				setPerspectiveList(getVeterinarios());
			}
		}
	}

	@FXML
	public void editar() throws Exception {
		int index = tableConsulta.getSelectionModel().getSelectedIndex();

		URL fxmledit;
		FXMLLoader loader = new FXMLLoader();
		Stage editStage = new Stage();
		String perspectiva = getCurrentPerspective();

		if (perspectiva == "Bovinos") {
			Bovinos bovino = (Bovinos) tableConsulta.getItems().get(index);

			fxmledit = getClass().getResource("/fxml/CadastroDeBovinos.fxml");
			loader.setLocation(fxmledit);
			Parent editP = loader.load();
			Scene editScene = new Scene(editP);
			cadastroBovinoController cadastroBovinoController = loader.getController();
			cadastroBovinoController.setUser(user);
			DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
			Bovinos bovinoEdit = daoB.getFirst("selectBovinobyNomeEmpresa", "nome", bovino.getNome(), "empresa",
					user.getIdEmpresas_Pessoa());
			cadastroBovinoController.setBovino(bovinoEdit);
			cadastroBovinoController.setEdit(true);
			cadastroBovinoController.populateFields(bovino);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.setScene(editScene);
			editStage.showAndWait();

			Boolean bovinoEditted = cadastroBovinoController.atualizar();
			if (bovinoEditted) {
				setPerspectiveList(getBovinos());
				consultarBovino();
			}
		} else if (perspectiva == "Usuarios") {
			Usuarios usuario = (Usuarios) tableConsulta.getItems().get(index);

			fxmledit = getClass().getResource("/fxml/CadastroDeUsuarios.fxml");
			loader.setLocation(fxmledit);
			Parent editP = loader.load();
			Scene editScene = new Scene(editP);
			cadastroUsuarioController cadastroUsuarioController = loader.getController();
			cadastroUsuarioController.setUser(user);
			cadastroUsuarioController.setEdit(true);
			cadastroUsuarioController.setUserEdit(usuario);
			cadastroUsuarioController.populateFields(usuario);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.setScene(editScene);
			editStage.showAndWait();

			boolean userEdit = cadastroUsuarioController.atualizar();
			if (userEdit) {
				setPerspectiveList(getUsuarios());
				consultarUsuarios();
			}
		} else if (perspectiva == "Veterinarios") {
			Veterinario vet = (Veterinario) tableConsulta.getItems().get(index);

			fxmledit = getClass().getResource("/fxml/CadastroDeVeterinarios.fxml");
			loader.setLocation(fxmledit);
			Parent edtitP = loader.load();
			Scene editScene = new Scene(edtitP);
			cadastroVeterinarioController cadastroVeterinarioController = loader.getController();
			cadastroVeterinarioController.populateFields(vet);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.setScene(editScene);
			editStage.showAndWait();

			boolean vetEdit = cadastroVeterinarioController.atualizar();
			if (vetEdit) {
				setPerspectiveList(getVeterinarios());
				consultarVeterinarios();
			}

		}
	}

	@FXML
	public void excluir() {
		int index = tableConsulta.getSelectionModel().getSelectedIndex();
		String perspectiva = getCurrentPerspective();

		if (perspectiva == "Bovinos") {
			Bovinos bovino = (Bovinos) tableConsulta.getItems().get(index);
			DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
			Bovinos bovinoDel = daoB.getAllById(bovino.getIdBovino());
			daoB.beginTransaction().delete(bovinoDel).commitTransaction().closeAll();

			setPerspectiveList(getBovinos());
			consultarBovino();
		}
		if (perspectiva == "Usuarios") {
			Usuarios user = (Usuarios) tableConsulta.getItems().get(index);
			DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);
			Usuarios userDel = daoUser.getAllById(user.getIdUsuario());
			daoUser.beginTransaction().delete(userDel).commitTransaction().closeAll();

			setPerspectiveList(getUsuarios());
			consultarUsuarios();
		}
		if (perspectiva == "Veterinarios") {

			btnExcluir.setDisable(true);

			
			// Excluir veterinarios??
//			Veterinario vet = (Veterinario) tableConsulta.getItems().get(index);
//			DAOHibernate<Veterinario> daoVet = new DAOHibernate<Veterinario>(Veterinario.class);
//			Veterinario vetDel = daoVet.getAllById(vet.getIdVeterinario());
//			daoVet.beginTransaction().delete(vetDel).commitTransaction().closeAll();
//			
//			setPerspectiveList(getVeterinarios());
//			consultarVeterinarios();
		}
	}

	@FXML
	public void fechar() {

		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();
	}
}
