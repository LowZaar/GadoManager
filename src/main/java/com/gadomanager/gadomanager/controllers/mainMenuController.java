package com.gadomanager.gadomanager.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.BCS;
import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Parametros;
import com.gadomanager.gadomanager.classes.Pesagens;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.controllers.eventos.cadastroEventoSaudeController;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@Component
public class mainMenuController {

	private Usuarios userLogin;

	
	@Autowired
	private ApplicationContext context;

	
	public Usuarios getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Usuarios userLogin) {
		this.userLogin = userLogin;
	}

	public void abrirBovino() throws Exception {

		URL fxmlBovino = getClass().getResource("/fxml/CadastroDeBovinos.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlBovino);

		Parent bovinoP = loader.load();
		Stage cadastroBovino = new Stage();
		Scene bovinoScene = new Scene(bovinoP);

		cadastroBovino.setScene(bovinoScene);
		cadastroBovinoController cadastroBovinoController = loader.getController();
		cadastroBovinoController.setUser(userLogin);
		cadastroBovinoController.setEdit(false);
		cadastroBovinoController.populateCombos();
		cadastroBovino.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroBovino.show();

	}

	public void abrirRacas() throws Exception {

		URL fxmlRaca = getClass().getResource("/fxml/CadastroDeRaca.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlRaca);
		loader.setControllerFactory(context::getBean);


		Parent racaP = loader.load();

		Stage cadastroRaca = new Stage();
		Scene racaScene = new Scene(racaP);

		cadastroRaca.setScene(racaScene);
		cadastroRacaController cadastroRacaController = loader.getController();
		cadastroRacaController.setEdit(false);
		cadastroRaca.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroRaca.show();

	}

	public void abrirRebanho() throws Exception {

		URL fxmlRebanho = getClass().getResource("/fxml/CadastroDeRebanho.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlRebanho);

		Parent rebanhoP = loader.load();

		Stage cadastroRebanho = new Stage();
		Scene rebanhoScene = new Scene(rebanhoP);

		cadastroRebanho.setScene(rebanhoScene);
		cadastroRebanhoController rebanhoController = loader.getController();
		rebanhoController.setUser(userLogin);

		cadastroRebanho.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroRebanho.show();

	}

	public void abrirBCS() throws Exception {

		URL fxmlBCS = getClass().getResource("/fxml/CadastroDeBCS.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlBCS);
		loader.setControllerFactory(context::getBean);
		
		Parent bcsP = loader.load();

		Stage cadastroBCS = new Stage();
		Scene bcsScene = new Scene(bcsP);

		cadastroBCS.setScene(bcsScene);
		cadastroBCSController cadastroBCSController = loader.getController();
		cadastroBCSController.setUser(userLogin);
		cadastroBCSController.populateCombo();
		cadastroBCS.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroBCS.show();

	}

	public void abrirPesagens() throws Exception {

		URL fxmlPesagem = getClass().getResource("/fxml/CadastroDePesagem.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlPesagem);
		loader.setControllerFactory(context::getBean);

		Parent pesagemP = loader.load();

		Stage cadastroPesagem = new Stage();
		Scene pesagemScene = new Scene(pesagemP);

		cadastroPesagem.setScene(pesagemScene);
		cadastroPesagemController cadastroPesagemController = loader.getController();
		cadastroPesagemController.setUser(userLogin);
		cadastroPesagemController.populateCombo();
		cadastroPesagem.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroPesagem.show();

	}

	public void abrirEventoSaude() throws Exception {
		URL fxmlEventoSaude = getClass().getResource("/fxml/CadastroEventoSaude.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlEventoSaude);

		Parent eventoSaude = loader.load();

		Stage cadastroEventoSaude = new Stage();
		Scene medicacaoScene = new Scene(eventoSaude);

		cadastroEventoSaude.setScene(medicacaoScene);
		cadastroEventoSaudeController cadastroEventoSaudeController = loader.getController();
		cadastroEventoSaudeController.setUser(userLogin);
		cadastroEventoSaudeController.populateCombos();
		cadastroEventoSaude.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroEventoSaude.show();
	}

	public void abrirMedicacao() throws Exception {

		URL fxmlMedicacao = getClass().getResource("/fxml/Medicamento.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlMedicacao);

		Parent medicacaoP = loader.load();

		Stage cadastroMedicacao = new Stage();
		Scene medicacaoScene = new Scene(medicacaoP);

		cadastroMedicacao.setScene(medicacaoScene);
		cadastroMedicacao.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroMedicacao.show();

	}

	public void abrirVacina() throws Exception {

		URL fxmlVacina = getClass().getResource("/fxml/Vacina.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlVacina);

		Parent vacinaP = loader.load();

		Stage cadastroVacina = new Stage();
		Scene vacinaScene = new Scene(vacinaP);

		cadastroVacina.setScene(vacinaScene);
		cadastroVacina.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroVacina.show();

	}

	public void abrirRacao() throws Exception {

		URL fxmlRacao = getClass().getResource("/fxml/Racoes.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlRacao);

		Parent racaoP = loader.load();

		Stage cadastroRacao = new Stage();
		Scene racaoScene = new Scene(racaoP);

		cadastroRacao.setScene(racaoScene);
		cadastroRacao.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroRacao.show();

	}

	public void abrirAlimentacao() throws Exception {

		URL fxmlAlimentacao = getClass().getResource("/fxml/Alimentacao.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlAlimentacao);
		loader.setControllerFactory(context::getBean);

		Parent alimentacaoP = loader.load();

		Stage cadastroAlimentacao = new Stage();
		Scene alimentacaoScene = new Scene(alimentacaoP);

		cadastroAlimentacao.setScene(alimentacaoScene);
		cadastroAlimentacaoController cadastroAlimentacaoController = loader.getController();
		cadastroAlimentacaoController.setUser(userLogin);
		cadastroAlimentacaoController.setEdit(false);
		cadastroAlimentacaoController.populateCombos();
		cadastroAlimentacao.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroAlimentacao.show();

	}

	public void abrirUsuario() throws Exception {

		if (userLogin.isUsuarioMestre()) {

			URL fxmlUsuario = getClass().getResource("/fxml/CadastroDeUsuarios.fxml");

			FXMLLoader loader = new FXMLLoader(fxmlUsuario);
			loader.setControllerFactory(context::getBean);
			
			Parent usuarioP = loader.load();

			Stage cadastroUsuario = new Stage();
			Scene usuarioScene = new Scene(usuarioP);

			cadastroUsuarioController cadastroUsuarioController = loader.getController();
			cadastroUsuarioController.setUser(userLogin);
			cadastroUsuarioController.setEdit(false);
			cadastroUsuario.setScene(usuarioScene);
			cadastroUsuario.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
			cadastroUsuario.show();

		} else {
			Notifications.create().title("Alerta").text(userLogin.getUsuario() + " não é um Usuario Mestre")
					.showWarning();
		}

	}

	public void abrirVeterinario() throws Exception {

		URL fxmlVeterinario = getClass().getResource("/fxml/CadastroDeVeterinario.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlVeterinario);

		Parent veterinarioP = loader.load();

		Stage cadastroVeterinario = new Stage();
		Scene veterinarioScene = new Scene(veterinarioP);
		cadastroVeterinarioController cadastroVeterinarioController = loader.getController();
		cadastroVeterinarioController.setEdit(false);
		cadastroVeterinario.setScene(veterinarioScene);
		cadastroVeterinario.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		cadastroVeterinario.show();

	}

	public void abrirParams() throws Exception {
		if (userLogin.isUsuarioMestre()) {

			URL fxmlParams = getClass().getResource("/fxml/Parametros.fxml");

			FXMLLoader loader = new FXMLLoader(fxmlParams);

			Parent paramsP = loader.load();

			Stage paramsStage = new Stage();
			Scene paramsScene = new Scene(paramsP);

			paramsStage.setScene(paramsScene);
			cadastroParamsController cadastroParamsController = loader.getController();
			cadastroParamsController.setUser(userLogin);
			cadastroParamsController.populateParams();
			paramsStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
			paramsStage.show();
		} else {

			Notifications.create().title("Alerta").text(userLogin.getUsuario() + " não é um Usuario Mestre")
					.showWarning();
		}
	}

	public void abrirConsultas() throws Exception {

		System.out.println("consultas");
		System.out.println(userLogin.toString());
		URL fxmlConsultas = getClass().getResource("/fxml/Consulta.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlConsultas);
		loader.setControllerFactory(context::getBean);
		
		
		Parent consultasP = loader.load();

		Stage consultaStage = new Stage();
		Scene consultaScene = new Scene(consultasP);

		consultaController consultaController = loader.getController();
		consultaController.setUser(userLogin);
		consultaStage.setScene(consultaScene);
		consultaStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/taskIcon.png")));
		consultaStage.show();
	}

	public void abrirRelatorioBovinos() {

		System.out.println("relatorio");

		DAOHibernate<Parametros> daoParams = new DAOHibernate<>(Parametros.class);

		Parametros params = daoParams.getFirst("selectParamsbyEmpresa", "empresa", userLogin.getIdEmpresas_Pessoa());

		File directory = new File(System.getProperty("user.home") + "/Desktop/GadoManager/");
		directory.mkdirs();
		if (directory.exists()) {

			Timestamp inst = Timestamp.from(Instant.now());
			String timeStamp = String.valueOf(inst.toLocalDateTime()).replace(" ", ".").replace(":", "_");

			File file = new File(
					System.getProperty("user.home") + "/Desktop/GadoManager/RelatorioBovino_" + timeStamp + ".xlsx");

			System.out.println(file.getAbsolutePath());
			try {

				Workbook wb = new HSSFWorkbook();

				CreationHelper createHelper = wb.getCreationHelper();

				Sheet sheet = wb.createSheet("Bovinos");

				Font headfont = wb.createFont();
				headfont.setBold(true);
				headfont.setFontHeightInPoints((short) 14);
				headfont.setColor(IndexedColors.BLUE.getIndex());

				CellStyle headStyle = wb.createCellStyle();
				headStyle.setFont(headfont);

				Row headRow = sheet.createRow(0);

				String[] headCols = { "Brinco", "Associação", "Nome", "Peso Atual", "Ultima Pesagem", "Sexo",
						"Nome Pai", "Nome Mãe", "BCS", "Avisos" };

				for (int i = 0; i < headCols.length; i++) {
					Cell headcell = headRow.createCell(i);
					headcell.setCellValue(headCols[i]);
					headcell.setCellStyle(headStyle);
					sheet.autoSizeColumn(i);
				}

				int rowNum = 1;

				DAOHibernate<Bovinos> daoB = new DAOHibernate<>(Bovinos.class);
				List<Bovinos> listB = daoB.getAllByNamedQuery("selectBovinobyEmpresa", "empresa",
						userLogin.getIdEmpresas_Pessoa());
				DAOHibernate<Pesagens> daoP = new DAOHibernate<>(Pesagens.class);

				CellStyle dataStyle = wb.createCellStyle();
				dataStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yy"));

				for (Bovinos bovinos : listB) {
					Pesagens ultimoPeso = daoP.getFirst("selectLastdateBovino", "idBovino", bovinos);
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(String.valueOf(bovinos.getIdBrinco()));

					row.createCell(1).setCellValue(String.valueOf(bovinos.getIdAssociacao()));

					row.createCell(2).setCellValue(bovinos.getNome());

					if (!(ultimoPeso == null)) {
						row.createCell(3).setCellValue(ultimoPeso.getPeso());
						Cell ultimoPesoCell = row.createCell(4);
						ultimoPesoCell.setCellValue(ultimoPeso.getDataPesagem());
						ultimoPesoCell.setCellStyle(dataStyle);

					} else {
						row.createCell(3).setCellValue(bovinos.getPesoNascimento());
						Cell ultimoPesoCell = row.createCell(4);
						ultimoPesoCell.setCellValue(bovinos.getDataNascimento());
						ultimoPesoCell.setCellStyle(dataStyle);
					}

					if (bovinos.getNome().equals("Roberto")) {
						row.createCell(5).setCellValue("Roberto O ++ BRABO");
					} else {
						row.createCell(5).setCellValue(String.valueOf(bovinos.getSexo()));
					}
					if (!(bovinos.getIdBovino_pai() == null)) {
						row.createCell(6).setCellValue(bovinos.getIdBovino_pai().getNome());
					} else {
						row.createCell(6).setCellValue(" ");
					}

					if (!(bovinos.getIdBovino_mae() == null)) {
						row.createCell(7).setCellValue(bovinos.getIdBovino_mae().getNome());
					} else {
						row.createCell(7).setCellValue(" ");
					}

					DAOHibernate<BCS> daoBCS = new DAOHibernate<>(BCS.class);
					BCS bcs = daoBCS.getFirst("selectBCSbyBovino", "bovino", bovinos);
					if (!(bcs == null)) {
						row.createCell(8).setCellValue(bcs.getIndiceBCS());
					} else {
						row.createCell(8).setCellValue("BCS Necessário");
					}

					Instant agora = Instant.now();
					Instant instParams = agora.minus(Duration.ofDays(params.getAlertaDiasSemPesar()));
					Date dateParams = Date.from(instParams);
					if (!(ultimoPeso == null)) {
						if (ultimoPeso.getDataPesagem().before(dateParams)) {
							String aviso = "Pesagem Necessária";
							row.createCell(9).setCellValue(aviso);
						}
					} else if (bovinos.getDataNascimento().before(dateParams)) {
						String aviso = "Pesagem Necessária";
						row.createCell(9).setCellValue(aviso);
					} else {
						row.createCell(9).setCellValue(" ");
					}

					for (int j = 0; j < headCols.length; j++) {
						sheet.autoSizeColumn(j);
					}

				}

				FileOutputStream fileoutput = new FileOutputStream(file);

				wb.write(fileoutput);
				fileoutput.close();

				wb.close();

				Desktop desktop = null;
				if (Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
					desktop.open(file);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
