package controllers;

import java.net.URL;

import org.controlsfx.control.Notifications;

import classes.Usuarios;
import controllers.eventos.cadastroEventoSaudeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class mainMenuController {

	private Usuarios userLogin;

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
		cadastroBovino.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroBovino.show();

	}

	public void abrirRacas() throws Exception {

		URL fxmlRaca = getClass().getResource("/fxml/CadastroDeRaca.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlRaca);

		Parent racaP = loader.load();

		Stage cadastroRaca = new Stage();
		Scene racaScene = new Scene(racaP);

		cadastroRaca.setScene(racaScene);
		cadastroRaca.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
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

		cadastroRebanho.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroRebanho.show();

	}

	public void abrirBCS() throws Exception {

		URL fxmlBCS = getClass().getResource("/fxml/CadastroDeBCS.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlBCS);

		Parent bcsP = loader.load();

		Stage cadastroBCS = new Stage();
		Scene bcsScene = new Scene(bcsP);

		cadastroBCS.setScene(bcsScene);
		cadastroBCSController cadastroBCSController = loader.getController();
		cadastroBCSController.setUser(userLogin);
		cadastroBCSController.populateCombo();
		cadastroBCS.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroBCS.show();

	}

	public void abrirPesagens() throws Exception {

		URL fxmlPesagem = getClass().getResource("/fxml/CadastroDePesagem.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlPesagem);

		Parent pesagemP = loader.load();

		Stage cadastroPesagem = new Stage();
		Scene pesagemScene = new Scene(pesagemP);

		cadastroPesagem.setScene(pesagemScene);
		cadastroPesagemController cadastroPesagemController = loader.getController();
		cadastroPesagemController.setUser(userLogin);
		cadastroPesagemController.populateCombo();
		cadastroPesagem.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
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
		cadastroEventoSaude.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroEventoSaude.show();
	}
	
	public void abrirMedicacao() throws Exception {

		URL fxmlMedicacao = getClass().getResource("/fxml/Medicamento.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlMedicacao);

		Parent medicacaoP = loader.load();

		Stage cadastroMedicacao = new Stage();
		Scene medicacaoScene = new Scene(medicacaoP);

		cadastroMedicacao.setScene(medicacaoScene);
		cadastroMedicacao.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroMedicacao.show();

	}

	public void abrirVacina() throws Exception {

		URL fxmlVacina = getClass().getResource("/fxml/Vacina.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlVacina);

		Parent vacinaP = loader.load();

		Stage cadastroVacina = new Stage();
		Scene vacinaScene = new Scene(vacinaP);

		cadastroVacina.setScene(vacinaScene);
		cadastroVacina.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroVacina.show();

	}

	public void abrirRacao() throws Exception {

		URL fxmlRacao = getClass().getResource("/fxml/Racoes.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlRacao);

		Parent racaoP = loader.load();

		Stage cadastroRacao = new Stage();
		Scene racaoScene = new Scene(racaoP);

		cadastroRacao.setScene(racaoScene);
		cadastroRacao.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroRacao.show();

	}

	public void abrirAlimentacao() throws Exception {

		URL fxmlAlimentacao = getClass().getResource("/fxml/Alimentacao.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlAlimentacao);

		Parent alimentacaoP = loader.load();

		Stage cadastroAlimentacao = new Stage();
		Scene alimentacaoScene = new Scene(alimentacaoP);

		cadastroAlimentacao.setScene(alimentacaoScene);
		cadastroAlimentacaoController cadastroAlimentacaoController = loader.getController();
		cadastroAlimentacaoController.setUser(userLogin);
		cadastroAlimentacaoController.populateCombos();
		cadastroAlimentacao.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		cadastroAlimentacao.show();

	}

	public void abrirUsuario() throws Exception {

		if (userLogin.isUsuarioMestre()) {

			URL fxmlUsuario = getClass().getResource("/fxml/CadastroDeUsuarios.fxml");

			FXMLLoader loader = new FXMLLoader(fxmlUsuario);

			Parent usuarioP = loader.load();

			Stage cadastroUsuario = new Stage();
			Scene usuarioScene = new Scene(usuarioP);

			cadastroUsuarioController cadastroUsuarioController = loader.getController();
			cadastroUsuarioController.setUser(userLogin);
			cadastroUsuarioController.setEdit(false);
			cadastroUsuario.setScene(usuarioScene);
			cadastroUsuario.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
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
		cadastroVeterinario.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
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
			paramsStage.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
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
		
		Parent consultasP = loader.load();
		
		Stage consultaStage = new Stage();
		Scene consultaScene = new Scene(consultasP);
		
		consultaController consultaController = loader.getController();
		consultaController.setUser(userLogin);
		consultaStage.setScene(consultaScene);
		consultaStage.getIcons().add(new Image(getClass().getResourceAsStream("/taskIcon/taskIcon.png")));
		consultaStage.show();
	}
}
