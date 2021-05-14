package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import classes.Cidades;
import classes.Empresas_Pessoas;
import classes.Estados;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.DAOHibernate;

public class cadastroEmpresaController {

	@FXML
	private Label LabelCPF;

	@FXML
	private Label LabelCNPJ;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private TextField txtCPF;

	@FXML
	private DatePicker dateDataNascimento;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtCEP;

	@FXML
	private ComboBox<String> comboEstado;

	@FXML
	private TextField txtIE;

	@FXML
	private TextField txtIM;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtRG;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtTP;
	
	@FXML
	private ComboBox<String> comboCidade;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private RadioButton radioTipoFisica;

	@FXML
	private RadioButton radioTipoJuridica;

	@FXML
	private PasswordField passSenha;

	@FXML
	public void Salvar() throws Exception {

		String nomeEmp = txtNome.getText();
		String cnpjEmp = txtCNPJ.getText();
		String cpfEmp = txtCPF.getText();
		Date dataEmp = localDateToDate(dateDataNascimento.getValue());
		String enderecoEmp = txtEndereco.getText();
		String cepEmp = txtCEP.getText();
		String estadoEmp = comboEstado.getValue();
		String ieEmp = txtIE.getText();
		String imEmp = txtIM.getText();
		String emailEmp = txtEmail.getText();
		String senhaEmp = passSenha.getText();
		String rgEmp = txtRG.getText();
		String telefoneEmp = txtTelefone.getText();
		int cidadeEmp = comboCidade.getValue().indexOf(comboCidade.getValue());

		DAOHibernate<Empresas_Pessoas> daoEmp = new DAOHibernate<>();

		if (radioTipoFisica.isSelected()) {

			System.out.println("User PF");

			Empresas_Pessoas empPF = new Empresas_Pessoas();

			Long estadoId = (long) comboEstado.getItems().indexOf(estadoEmp);

			Cidades cidadeObj = findCidade(cidadeEmp);

			empPF = empPF.createPF(nomeEmp, cpfEmp, rgEmp, dataEmp, enderecoEmp, cidadeObj, estadoId, cepEmp,
					telefoneEmp, emailEmp);

			daoEmp.beginTransaction().save(empPF).commitTransaction().closeAll();

			createUser(nomeEmp, emailEmp, senhaEmp, empPF);

			URL fxmlFile = getClass().getResource("/fxml/Login.fxml");

			GridPane loginGrid = FXMLLoader.load(fxmlFile);

			Stage window = (Stage) btnSalvar.getScene().getWindow();

			window.setScene(new Scene(loginGrid));

		} else if (radioTipoJuridica.isSelected()) {

			System.out.println("User PJ");

			Empresas_Pessoas empPJ = new Empresas_Pessoas();

			Long estadoId = (long) comboEstado.getItems().indexOf(estadoEmp);

			Cidades cidadeObj = findCidade(cidadeEmp);
			
			empPJ = empPJ.createPJ(nomeEmp, dataEmp, cnpjEmp, ieEmp, imEmp, enderecoEmp, cidadeObj, estadoId, cepEmp,
					telefoneEmp, emailEmp);

			daoEmp.beginTransaction().save(empPJ).commitTransaction().closeAll();

			createUser(nomeEmp, emailEmp, senhaEmp, empPJ);

			URL fxmlFile = getClass().getResource("/fxml/Login.fxml");

			GridPane loginGrid = FXMLLoader.load(fxmlFile);

			Stage window = (Stage) btnSalvar.getScene().getWindow();

			window.setScene(new Scene(loginGrid));

		}

	}

	@FXML
	public void Cancelar() throws Exception {

		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");

		GridPane loginGrid = FXMLLoader.load(fxmlFile);

		Stage window = (Stage) btnSalvar.getScene().getWindow();

		window.setScene(new Scene(loginGrid));
	}

	public Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}

	public void createUser(String nomeEmpresa, String email, String senha, Empresas_Pessoas empresa) {

		DAOHibernate<Usuarios> daoUser = new DAOHibernate<>();

		String nome = nomeEmpresa.replaceAll(" ", "").toLowerCase();

		Usuarios user1 = new Usuarios(nomeEmpresa, email, nome, senha, true, empresa);

		daoUser.beginTransaction().save(user1).closeAll();

		Alert alertuser = new Alert(AlertType.CONFIRMATION);

		alertuser.setContentText("Usuario mestre criado para empresa " + nomeEmpresa + " \n" + "Login de usuario : "
				+ user1.getNome() + " \n" + "Senha do usuario : " + user1.getSenha());
	}

	public void radioCheck() {
		if (radioTipoFisica.isSelected()) {
			
			radioTipoJuridica.setSelected(false);
			txtCNPJ.setVisible(false);
			LabelCNPJ.setVisible(false);
			
		}else if (radioTipoJuridica.isSelected()) {
			radioTipoFisica.setSelected(false);
			txtCPF.setVisible(false);
			LabelCPF.setVisible(false);
		}else if(!radioTipoFisica.isSelected() && !radioTipoJuridica.isSelected()){
			
			txtCPF.setVisible(true);
			LabelCPF.setVisible(true);
			
			txtCNPJ.setVisible(true);
			LabelCNPJ.setVisible(true);
		}
		
	}
	
	
	public void estadoCombo() {

		DAOHibernate<Estados> daoE = new DAOHibernate<>();

		List<Estados> list = daoE.getAllByNamedQuery("selectEstados");

		System.out.println(list);
		
		comboEstado.getItems().add("Selecione...");
		for (Estados estados : list) {
			comboEstado.getItems().add(estados.getSigla());

		}
		
		daoE.closeAll();
	}

	public Cidades findCidade(int cidadeIndex) {
		
		DAOHibernate<Cidades> daoCity = new DAOHibernate<>();

		Cidades cidadeObj = daoCity.getAllById(cidadeIndex);
		
		daoCity.closeAll();
		return cidadeObj;
	}

	
	public void populateCidade() {
		
		comboEstado.getItems().add("Selecione...");
		
		System.out.println("populating cidades");
		System.out.println();
		
		DAOHibernate<Cidades> daoCidadeEstado = new DAOHibernate<>();
		
		int idEstado = comboEstado.getSelectionModel().getSelectedIndex();
			
		System.out.println(idEstado);
		
		DAOHibernate<Estados> daoE = new DAOHibernate<>();
		
		Estados estadoObj = daoE.getAllById(idEstado);
		
		List<Cidades> list = daoCidadeEstado.getAllByNamedQuery("selectCidadebyEstado", idEstado);
		
		System.out.println(list);
		
		
		for (Cidades cidades : list) {
			comboCidade.getItems().add(cidades.getNome());
		}
		
	}

}
