package com.gadomanager.gadomanager.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Cidades;
import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Estados;
import com.gadomanager.gadomanager.classes.Parametros;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.utils.DAOHibernate;
import com.gadomanager.gadomanager.utils.TextFieldFormatter;

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

@Component
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

	@Autowired
	private ApplicationContext context;
	
	@FXML
	public void Salvar() throws Exception {

		String nomeEmp = txtNome.getText();
		String cpfcnpjEmp = txtCNPJ.getText();
		Date dataEmp = localDateToDate(dateDataNascimento.getValue());
		String enderecoEmp = txtEndereco.getText();
		String cepEmp = txtCEP.getText();
//		String estadoEmp = comboEstado.getValue();
		String ieEmp = txtIE.getText();
		String imEmp = txtIM.getText();
		String emailEmp = txtEmail.getText();
		String senhaEmp = passSenha.getText();
		String rgEmp = txtRG.getText();
		String telefoneEmp = txtTelefone.getText();
		String cidadeNome = comboCidade.getValue();

//		Long estadoId = (long) comboEstado.getItems().indexOf(estadoEmp);
		Cidades cidadeObj = findCidade(cidadeNome);

		DAOHibernate<Empresas_Pessoas> daoEmp = new DAOHibernate<>(Empresas_Pessoas.class);

		Empresas_Pessoas empresa = new Empresas_Pessoas();

		empresa.setNome(nomeEmp);

		if (radioTipoFisica.isSelected()) {
			System.out.println("User PF");

			empresa.setCpf(cpfcnpjEmp);
			empresa.setRg(rgEmp);

		} else if (radioTipoJuridica.isSelected()) {
			System.out.println("User PJ");

			empresa.setCnpj(cpfcnpjEmp);
			empresa.setIe(ieEmp);
			empresa.setIm(imEmp);
		}

		empresa.setDataNascimento(dataEmp);
		empresa.setEndereco(enderecoEmp);
		empresa.setEmail(emailEmp);
		empresa.setCep(cepEmp);
		empresa.setIdCidade(cidadeObj);
		empresa.setIdEstado(cidadeObj);
		empresa.setTelefone(telefoneEmp);

		daoEmp.beginTransaction().save(empresa).commitTransaction().closeAll();

		DAOHibernate<Usuarios> daoUser = new DAOHibernate<>(Usuarios.class);

		String nome = empresa.getNome().replaceAll(" ", "").toLowerCase();

		Usuarios user = new Usuarios(empresa.getNome(), empresa.getEmail(), nome, senhaEmp, true, empresa);

		daoUser.beginTransaction().save(user).commitTransaction().closeAll();

		DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);

		Parametros paramObj = new Parametros(empresa, 0, 0, 0.00);

		daoParams.beginTransaction().save(paramObj).commitTransaction().closeAll();

		URL fxmlLogin = getClass().getResource("/fxml/Login.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlLogin);
		
		loader.setControllerFactory(context::getBean);
		
		Parent loginP = loader.load();

		Stage window = (Stage) btnSalvar.getScene().getWindow();
		Scene loginScene = new Scene(loginP);
		loginController loginController = loader.getController();

		window.setScene(loginScene);
		loginController.notifyCadastro(user, empresa);

	}

	@FXML
	public void Cancelar() throws Exception {

		URL fxmlFile = getClass().getResource("/fxml/Login.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile);
		fxmlLoader.setControllerFactory(context::getBean);
		
		GridPane loginGrid = fxmlLoader.load();

		Stage window = (Stage) btnSalvar.getScene().getWindow();

		window.setScene(new Scene(loginGrid));
	}

	private Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}

	@FXML
	public void pfChecked() {
		txtCNPJ.clear();

		radioTipoJuridica.setSelected(false);

		txtRG.setDisable(false);

		txtIE.setDisable(true);

		txtIM.setDisable(true);

	}

	@FXML
	public void pjChecked() {

		txtCNPJ.clear();

		txtIE.setDisable(false);

		txtIM.setDisable(false);

		radioTipoFisica.setSelected(false);

		txtRG.setDisable(true);

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
		list = daoE.getAllByNamedQuery("selectEstados");
		daoE.closeAll();

		return list;
	}

	
	public void populateCidade() {
		comboCidade.getItems().clear();
		comboCidade.getItems().add("Selecione...");

		System.out.println("populating cidades");

		DAOHibernate<Cidades> daoCidadeEstado = new DAOHibernate<>();

		Long idEstado = Long.valueOf(comboEstado.getSelectionModel().getSelectedIndex());
		
		System.out.println(idEstado);
		
		List<Cidades> list = daoCidadeEstado.getAllByNamedQuery("selectCidadebyEstado", "idEstado", idEstado);
		for (Cidades cidades : list) {

			comboCidade.getItems().add(cidades.getNome());

		}
	}

	@FXML
	private void formatCEP() {

		TextFieldFormatter CEPmask = new TextFieldFormatter();
		CEPmask.setMask("#####-###");
		CEPmask.setCaracteresValidos("0123456789");
		CEPmask.setTf(txtCEP);
		CEPmask.formatter();

	}

	@FXML
	private void formatCPFCPNJ() {

		if (radioTipoFisica.isSelected()) {
			TextFieldFormatter CPFmask = new TextFieldFormatter();
			CPFmask.setMask("###.###.###-##");
			CPFmask.setCaracteresValidos("0123456789");
			CPFmask.setTf(txtCNPJ);
			CPFmask.formatter();

		} else if (radioTipoJuridica.isSelected()) {

			TextFieldFormatter CNPJmask = new TextFieldFormatter();
			CNPJmask.setMask("##.###.###/####-##");
			CNPJmask.setCaracteresValidos("0123456789");
			CNPJmask.setTf(txtCNPJ);
			CNPJmask.formatter();
		}

	}

	@FXML
	private void formatPhone() {

		TextFieldFormatter TelefoneMask = new TextFieldFormatter();
		TelefoneMask.setMask("(##)####-####");
		TelefoneMask.setCaracteresValidos("0123456789");
		TelefoneMask.setTf(txtTelefone);
		TelefoneMask.formatter();
	}

	@FXML
	private void formatRG() {

		TextFieldFormatter RGmask = new TextFieldFormatter();
		RGmask.setMask("###########");
		RGmask.setCaracteresValidos("0123456789");
		RGmask.setTf(txtRG);
		RGmask.formatter();
	}

}
