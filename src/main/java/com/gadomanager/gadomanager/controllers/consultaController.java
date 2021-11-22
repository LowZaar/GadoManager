package com.gadomanager.gadomanager.controllers;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.tableview2.TableView2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Alimentos;
import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.classes.Veterinario;
import com.gadomanager.gadomanager.controllers.filtros.confirmExcluirController;
import com.gadomanager.gadomanager.controllers.filtros.filtroBovinoController;
import com.gadomanager.gadomanager.controllers.filtros.filtroUsuarioController;
import com.gadomanager.gadomanager.controllers.filtros.filtroVeterinarioController;
import com.gadomanager.gadomanager.repos.AlimentoRepository;
import com.gadomanager.gadomanager.repos.UsuarioRepository;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Component
public class consultaController {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private AlimentoRepository repoAlimento;
	
	@Autowired
	private UsuarioRepository repoUser;
	
	@FXML
	private Button btnFiltro;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnFechar;

	@FXML
	public TableView2<Object> tableConsulta;

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
	@FXML
	public void initialize() {
		tableConsulta.setPlaceholder(new Label("Escolha uma opção de consulta"));
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
			if (!(bovinoPai == null)) {
				return new ReadOnlyStringWrapper(bovinoPai.getNome());
			} else {
				return new ReadOnlyStringWrapper(" ");
			}
		});
		tableConsulta.getColumns().add(bovinoPaiCol);

		TableColumn<Object, String> bovinoMaeCol = new TableColumn<>("Bovino Mãe");
		bovinoMaeCol.setCellValueFactory(info -> {
			Bovinos bovinoMae = ((Bovinos) info.getValue()).getIdBovino_mae();
			if (!(bovinoMae == null)) {
				return new ReadOnlyStringWrapper(bovinoMae.getNome());
			} else {
				return new ReadOnlyStringWrapper(" ");
			}
		});
		tableConsulta.getColumns().add(bovinoMaeCol);

		TableColumn<Object, Date> morteCol = new TableColumn<>("Data Morte");
		morteCol.setCellValueFactory(new PropertyValueFactory<>("dataMorte"));
		tableConsulta.getColumns().add(morteCol);

	}

	private ObservableList<Object> getBovinos() {
		ObservableList<Object> list = FXCollections.observableArrayList();

		DAOHibernate<Bovinos> daoB = new DAOHibernate<Bovinos>(Bovinos.class);
		List<Bovinos> query = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa", user.getIdEmpresasPessoa());
		list.addAll(query);

		return list;
	}

	@FXML
	public void bovinoClick() {

		setCurrentPerspective("Bovinos");
		menuConsulta.setText(currentPerspective);

		btnEditar.setDisable(false);
		btnExcluir.setDisable(false);
		setPerspectiveList(getBovinos());
		consultarBovino();

	}

	@FXML
	public void consultarUsuarios() {
		setCurrentPerspective("Usuarios");
		tableConsulta.getColumns().clear();
		tableConsulta.getItems().clear();

//		setPerspectiveList(getUsuarios());

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
		
		
		List<Usuarios> query = repoUser.findByIdEmpresasPessoa(user.getIdEmpresasPessoa());
		
		list.addAll(query);

		return list;
	}

	@FXML
	public void usuarioClick() {

		setCurrentPerspective("Usuarios");

		menuConsulta.setText(currentPerspective);

		btnEditar.setDisable(false);
		btnExcluir.setDisable(false);
		
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
	public void veterinariosClick() {
		setCurrentPerspective("Veterinarios");
		menuConsulta.setText(currentPerspective);

		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		
		setPerspectiveList(getVeterinarios());
		consultarVeterinarios();
	}
	
	
	private void consultarAlimentos() {

		tableConsulta.getColumns().clear();
		tableConsulta.getItems().clear();
		tableConsulta.setItems(getPerspectiveList());

		// Colunas
		TableColumn<Object, String> rebanhoCol = new TableColumn<>("Rebanho");
		rebanhoCol.setCellValueFactory(
				info -> new SimpleStringProperty(((Alimentos) info.getValue()).getIdRebanho().getNome()));
		tableConsulta.getColumns().add(rebanhoCol);

		TableColumn<Object, String> racaoCol = new TableColumn<>("Ração");
		racaoCol.setCellValueFactory(
				info -> new SimpleStringProperty(((Alimentos) info.getValue()).getIdracao().getDescricao()));
		tableConsulta.getColumns().add(racaoCol);

		TableColumn<Object, String> DataIniCol = new TableColumn<>("Data Início");
		DataIniCol.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
		tableConsulta.getColumns().add(DataIniCol);

		TableColumn<Object, String> DataTerminoCol = new TableColumn<>("Data Início");
		DataTerminoCol.setCellValueFactory(new PropertyValueFactory<>("dataTermino"));
		tableConsulta.getColumns().add(DataTerminoCol);

	}

	private ObservableList<Object> getAlimentos() {
		ObservableList<Object> list = FXCollections.observableArrayList();

		List<Alimentos> query = Streamable.of(repoAlimento.findAll()).toList();
		
		list.addAll(query);

		return list;
	}

	@FXML
	public void alimentosClick() {
		setCurrentPerspective("Alimentação");
		menuConsulta.setText(currentPerspective);

		btnEditar.setDisable(true);
		btnExcluir.setDisable(false);
		
		setPerspectiveList(getAlimentos());
		consultarAlimentos();
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

			if (getPerspectiveList() == null) {
				setPerspectiveList(getBovinos());
				consultarBovino();
			} else {
				consultarBovino();
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
				consultarUsuarios();
			} else {
				consultarUsuarios();
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
				consultarVeterinarios();
			} else {
				consultarVeterinarios();
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
					user.getIdEmpresasPessoa());
			cadastroBovinoController.setBovino(bovinoEdit);
			cadastroBovinoController.setEdit(true);
			cadastroBovinoController.populateFields(bovino);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.setScene(editScene);
			editStage.showAndWait();
			setPerspectiveList(getBovinos());
			consultarBovino();
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
			setPerspectiveList(getUsuarios());
			consultarUsuarios();

		} else if (perspectiva == "Veterinarios") {
			Veterinario vet = (Veterinario) tableConsulta.getItems().get(index);

			fxmledit = getClass().getResource("/fxml/CadastroDeVeterinarios.fxml");
			loader.setLocation(fxmledit);
			Parent edtitP = loader.load();
			Scene editScene = new Scene(edtitP);
			cadastroVeterinarioController cadastroVeterinarioController = loader.getController();
			cadastroVeterinarioController.populateFields(vet);
			cadastroVeterinarioController.setEdit(true);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.setScene(editScene);
			editStage.showAndWait();
			setPerspectiveList(getVeterinarios());
			consultarVeterinarios();

		}
	}

	@FXML
	public void excluir() throws Exception {
		int index = tableConsulta.getSelectionModel().getSelectedIndex();
		String perspectiva = getCurrentPerspective();

		URL fxmldialog = getClass().getResource("/fxml/ExcluirDialog.fxml");
		FXMLLoader loader = new FXMLLoader(fxmldialog);
		
		loader.setControllerFactory(context::getBean);
		Parent dialog = loader.load();
		Scene dialogScene = new Scene(dialog);
		confirmExcluirController confirmExcluirController = loader.getController();
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.setScene(dialogScene);

		if (perspectiva == "Bovinos") {
			Bovinos bovino = (Bovinos) tableConsulta.getItems().get(index);
			DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
			Bovinos bovinoDel = daoB.getAllById(bovino.getIdBovino());

			confirmExcluirController.setClass(bovinoDel);
			dialogStage.showAndWait();
			Boolean excluir = confirmExcluirController.returnDelete();
			if (excluir) {
				daoB.beginTransaction().delete(bovinoDel).commitTransaction().closeAll();
				setPerspectiveList(getBovinos());
				consultarBovino();
			}

		}
		if (perspectiva == "Usuarios") {
			Usuarios user = (Usuarios) tableConsulta.getItems().get(index);
			DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);
			Usuarios userDel = daoUser.getAllById(user.getIdUsuario());

			confirmExcluirController.setClass(userDel);
			dialogStage.showAndWait();
			Boolean excluir = confirmExcluirController.returnDelete();
			if (excluir) {
				daoUser.beginTransaction().delete(userDel).commitTransaction().closeAll();
				setPerspectiveList(getUsuarios());
				consultarUsuarios();
			}
		}
		if (perspectiva == "Veterinarios") {


			// Excluir veterinarios??
//			Veterinario vet = (Veterinario) tableConsulta.getItems().get(index);
//			DAOHibernate<Veterinario> daoVet = new DAOHibernate<Veterinario>(Veterinario.class);
//			Veterinario vetDel = daoVet.getAllById(vet.getIdVeterinario());
//			daoVet.beginTransaction().delete(vetDel).commitTransaction().closeAll();
//			
//			setPerspectiveList(getVeterinarios());
//			consultarVeterinarios();
		}
		if (perspectiva == "Alimentação") {
			Alimentos alimento = (Alimentos) tableConsulta.getItems().get(index);
			System.out.println(alimento);
			
			
			confirmExcluirController.setClass(alimento);
			dialogStage.showAndWait();
			Boolean excluir = confirmExcluirController.returnDelete();
			if (excluir) {
				
				repoAlimento.deleteById(alimento.getIdAlimento());
				setPerspectiveList(getAlimentos());
				consultarAlimentos();
			}
		}
	}

	@FXML
	public void fechar() {

		Stage window = (Stage) btnFechar.getScene().getWindow();
		window.close();
	}
}
