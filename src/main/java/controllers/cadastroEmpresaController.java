package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;

import classes.Cidades;
import classes.Empresas_Pessoas;
import classes.Estados;
import classes.Parametros;
import classes.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private Label LabelRG;
	
	@FXML
	private Label LabelIE;
	
	@FXML
	private Label LabelIM;
	
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

	public cadastroEmpresaController() {

	}

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
		String cidadeNome = comboCidade.getValue();
		

		DAOHibernate<Empresas_Pessoas> daoEmp = new DAOHibernate<>();

		if (radioTipoFisica.isSelected()) {

			System.out.println("User PF");

			Empresas_Pessoas empPF = new Empresas_Pessoas();

			Long estadoId = (long) comboEstado.getItems().indexOf(estadoEmp);

			Cidades cidadeObj = findCidade(cidadeNome);

			empPF = empPF.createPF(nomeEmp, cpfEmp, rgEmp, dataEmp, enderecoEmp, cidadeObj, estadoId, cepEmp,
					telefoneEmp, emailEmp);

			

			createUser(nomeEmp, emailEmp, senhaEmp, empPF);
			
			DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);
			Parametros paramObj = new Parametros(empPF,0,0,0.00);
			daoEmp.beginTransaction().save(empPF).commitTransaction().closeAll();
			daoParams.beginTransaction().save(paramObj).commitTransaction().closeAll();
			
		} else if (radioTipoJuridica.isSelected()) {

			System.out.println("User PJ");

			Empresas_Pessoas empPJ = new Empresas_Pessoas();

			Long estadoId = (long) comboEstado.getItems().indexOf(estadoEmp);

			Cidades cidadeObj = findCidade(cidadeNome);

			empPJ = empPJ.createPJ(nomeEmp, dataEmp, cnpjEmp, ieEmp, imEmp, enderecoEmp, cidadeObj, estadoId, cepEmp,
					telefoneEmp, emailEmp);
			System.out.println(empPJ);
			

			createUser(nomeEmp, emailEmp, senhaEmp, empPJ);
			
			DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);
			Parametros paramObj = new Parametros(empPJ,0,0,0.00);
			
			daoEmp.beginTransaction().save(empPJ).commitTransaction().closeAll();
			daoParams.beginTransaction().save(paramObj).commitTransaction().closeAll();
			
		}

		URL fxmlLogin = getClass().getResource("/fxml/Login.fxml");
		
		FXMLLoader loader = new FXMLLoader(fxmlLogin);
		
		Parent loginP = loader.load();
		
		Stage window = (Stage) btnSalvar.getScene().getWindow();
		Scene loginScene = new Scene(loginP);
		window.setScene(loginScene);
	}

	@FXML
	public void Cancelar() throws Exception {

		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");

		GridPane loginGrid = FXMLLoader.load(fxmlFile);

		Stage window = (Stage) btnSalvar.getScene().getWindow();

		window.setScene(new Scene(loginGrid));
	}

	private Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}

	public void createUser(String nomeEmpresa, String email, String senha, Empresas_Pessoas empresa) {

		DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);

		String nome = nomeEmpresa.replaceAll(" ", "").toLowerCase();

		Usuarios user = new Usuarios(nomeEmpresa, email, nome, senha, true, empresa);

		daoUser.beginTransaction().save(user).commitTransaction().closeAll();

		Notifications
				.create().title("Alerta de Login").text("Usuario mestre criado para empresa " + nomeEmpresa + " \n"
						+ "Login de usuario : " + user.getUsuario() + " \n" + "Senha do usuario : " + user.getSenha())
				.showConfirm();

	}

	public void radioCheck() {
		if (radioTipoFisica.isSelected()) {
			radioTipoJuridica.setSelected(false);
			
			txtCNPJ.setVisible(false);
			LabelCNPJ.setVisible(false);
			
			txtRG.setVisible(true);
			LabelRG.setVisible(true);
			
			txtIE.setVisible(false);
			LabelIE.setVisible(false);
			
			txtIM.setVisible(false);
			LabelIM.setVisible(false);
			
		} else if (radioTipoJuridica.isSelected()) {
			radioTipoFisica.setSelected(false);
			
			txtCPF.setVisible(false);
			LabelCPF.setVisible(false);
			
			txtRG.setVisible(true);
			LabelRG.setVisible(true);
			
		} else if (!radioTipoFisica.isSelected() && !radioTipoJuridica.isSelected()) {

			txtCPF.setVisible(true);
			LabelCPF.setVisible(true);
			
			txtRG.setVisible(true);
			LabelRG.setVisible(true);
			
			txtIE.setVisible(true);
			LabelIE.setVisible(true);
			
			txtIM.setVisible(true);
			LabelIM.setVisible(true);
			
			txtCNPJ.setVisible(true);
			LabelCNPJ.setVisible(true);
		}

	}

	@FXML
	public void estadoCombo() {
		List<Estados> list = prepareEstadoList();

		comboEstado.getItems().clear();

		comboEstado.getItems().add("Selecione...");
		for (Estados estados : list) {
			comboEstado.getItems().add(estados.getSigla());
		}

	}

	public Cidades findCidade(String cidadeNome) {

		DAOHibernate<Cidades> daoCity = new DAOHibernate<>(Cidades.class);

		daoCity.beginTransaction();
		Cidades cidadeObj = daoCity.getFirst("findCidadeid", "nome", cidadeNome);

		daoCity.closeAll();
		return cidadeObj;
	}

	public static List<Estados> prepareEstadoList() {
		DAOHibernate<Estados> daoE = new DAOHibernate<>();
		List<Estados> list = null;
		if (list == null) {
			list = daoE.getAllByNamedQuery("selectEstados");
		}

		daoE.closeAll();

		return list;
	}

	public void populateCidade() {
		comboCidade.getItems().add("Selecione...");

		System.out.println("populating cidades");

		DAOHibernate<Cidades> daoCidadeEstado = new DAOHibernate<>();

		Long idEstado = (long) comboEstado.getItems().indexOf(comboEstado.getValue());

		System.out.println(idEstado);

		List<Cidades> list = daoCidadeEstado.getAllByNamedQuery("selectCidadebyEstado", "idEstado", idEstado);
		for (Cidades cidades : list) {

			comboCidade.getItems().add(cidades.getNome());

		}
	}
	
}
