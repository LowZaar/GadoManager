package com.gadomanager.gadomanager.controllers.eventos;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;

import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.classes.Veterinario;
import com.gadomanager.gadomanager.eventos.EventosSaude;
import com.gadomanager.gadomanager.eventos.EventosSaudeBovinos;
import com.gadomanager.gadomanager.eventos.EventosSaudeMedicacao;
import com.gadomanager.gadomanager.eventos.EventosSaudeOutros;
import com.gadomanager.gadomanager.eventos.EventosSaudeVacina;
import com.gadomanager.gadomanager.eventos.TiposEvento;
import com.gadomanager.gadomanager.utils.DAOHibernate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class cadastroEventoSaudeController {

	@FXML
	private ComboBox<String> comboEvento;

	@FXML
	private DatePicker dateData;

	@FXML
	private TextArea txtObservacoes;

	@FXML
	private ComboBox<String> comboVeterinario;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label labelStatus;

	private Usuarios user;

	private String evento;

	private EventosSaudeMedicacao eventoMed;

	private EventosSaudeVacina eventoVac;

	private EventosSaudeOutros eventoOutro;

	private EventosSaudeBovinos eventoBov;

	public EventosSaudeMedicacao getEventoMed() {
		return eventoMed;
	}

	public void setEventoMed(EventosSaudeMedicacao eventoMed) {
		this.eventoMed = eventoMed;
	}

	public EventosSaudeVacina getEventoVac() {
		return eventoVac;
	}

	public void setEventoVac(EventosSaudeVacina eventoVac) {
		this.eventoVac = eventoVac;
	}

	public EventosSaudeOutros getEventoOutro() {
		return eventoOutro;
	}

	public void setEventoOutro(EventosSaudeOutros eventoOutro) {
		this.eventoOutro = eventoOutro;
	}

	public EventosSaudeBovinos getEventoBov() {
		return eventoBov;
	}

	public void setEventoBov(EventosSaudeBovinos eventoBov) {
		this.eventoBov = eventoBov;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public void populateCombos() {

		comboEvento.getItems().addAll("Bovinos", "Medicação", "Vacina", "Outros");

		DAOHibernate<Veterinario> daoVet = new DAOHibernate<>(Veterinario.class);
		List<Veterinario> listaVet = daoVet.getAll();

		for (Veterinario veterinario : listaVet) {
			comboVeterinario.getItems().add(veterinario.getNome());
		}

	}

	@FXML
	public void showEvento() throws Exception {
		URL fxmlEvento;
		FXMLLoader loader = new FXMLLoader();
		Stage eventoStage = new Stage();

		System.out.println(comboEvento.getValue());

		if (comboEvento.getValue() == "Bovinos") {
			setEvento("Bovinos");
						
			fxmlEvento = getClass().getResource("/fxml/EventosSaudeBovinos.fxml");
			loader.setLocation(fxmlEvento);
			Parent eventoP = loader.load();
			Scene eventoScene = new Scene(eventoP);
			eventoBovinosController eventoBovinosController = loader.getController();
			eventoBovinosController.setUser(user);
			eventoBovinosController.populateRebanho();
			eventoBovinosController.setCadastroEventoSaudeController(this);
			eventoStage.initModality(Modality.APPLICATION_MODAL);
			eventoStage.setScene(eventoScene);
			eventoStage.showAndWait();
			
			if (!(this.eventoBov == null)) {
				System.out.println(eventoBov.getIdBovino().getNome());
				labelStatus.setText("Evento Bovino Criado");
				
			}
			
		} else if (comboEvento.getValue() == "Medicação") {
			setEvento("Medicação");


			fxmlEvento = getClass().getResource("/fxml/EventosSaudeMedicacao.fxml");
			loader.setLocation(fxmlEvento);
			Parent eventoP = loader.load();
			Scene eventoScene = new Scene(eventoP);
			eventoMedicacaoController eventoMedicacaoController = loader.getController();
			eventoMedicacaoController.populateTable();
			eventoMedicacaoController.setCadastroEventoSaudeController(this);
			eventoStage.initModality(Modality.APPLICATION_MODAL);
			eventoStage.setScene(eventoScene);
			eventoStage.showAndWait();
			
			if (!(this.eventoMed.getIdMedicamento() == null)) {
				labelStatus.setText("Evento Medicação Criado");
			}
			

		} else if (comboEvento.getValue() == "Vacina") {
			setEvento("Vacina");
			
			
			fxmlEvento = getClass().getResource("/fxml/EventosSaudeVacina.fxml");
			loader.setLocation(fxmlEvento);
			Parent eventoP = loader.load();
			Scene eventoScene = new Scene(eventoP);
			eventoVacinaController eventoVacinaController = loader.getController();
			eventoVacinaController.populateTable();
			eventoVacinaController.setCadastroEventoSaudeController(this);
			eventoStage.initModality(Modality.APPLICATION_MODAL);
			eventoStage.setScene(eventoScene);
			eventoStage.showAndWait();

			if(!(this.eventoVac.getIdVacina() == null)) {
				labelStatus.setText("Evento Vacina Criado");
			}

		} else {

			setEvento("Outros");
			labelStatus.setText("");
			EventosSaudeOutros evento = new EventosSaudeOutros();
			evento.setObservacoes(txtObservacoes.getText());

		}
	}

	@FXML
	public void salvar() {

		char tagEvento = comboEvento.getValue().charAt(0);
		String veterinarioNome = comboVeterinario.getValue();
		Date data = Date.from(dateData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

		DAOHibernate<TiposEvento> daoTE = new DAOHibernate<>(TiposEvento.class);
		TiposEvento tipoEvento = daoTE.getFirst("selectTipoEventobyTag", "tag", tagEvento);
		daoTE.closeAll();

		DAOHibernate<Veterinario> daovet = new DAOHibernate<>(Veterinario.class);
		Veterinario vet = daovet.getFirst("selectVeterinariobyNome", "nome", veterinarioNome);

		EventosSaude eventosSaude = new EventosSaude();
		eventosSaude.setData(data);
		eventosSaude.setIdTipoEvento(tipoEvento);
		eventosSaude.setIdVeterinario(vet);
		
		DAOHibernate<EventosSaude> daoEvento = new DAOHibernate<>(EventosSaude.class);
		daoEvento.beginTransaction().save(eventosSaude).commitTransaction().closeAll();
		
		String evento = getEvento();
		if (evento == "Bovinos" && !(eventoBov.getIdBovino() == null)) {
			
			eventoBov.setObservacoes(txtObservacoes.getText());
			eventoBov.setIdEventoSaude(eventosSaude);
			eventoBov.setData(data);
			DAOHibernate<EventosSaudeBovinos> daoSB = new DAOHibernate<>(EventosSaudeBovinos.class);
			daoSB.beginTransaction().save(eventoBov).commitTransaction().closeAll();
			Notifications.create().title("Evento Saúde").text("Evento Bovino cadastrado com sucesso!").showConfirm();
			

		} else if (evento == "Medicação" && !(eventoMed.getIdMedicamento() == null)) {

			eventoMed.setIdEventoSaude(eventosSaude);
			DAOHibernate<EventosSaudeMedicacao> daoSM = new DAOHibernate<>(EventosSaudeMedicacao.class);
			daoSM.beginTransaction().save(eventoMed).commitTransaction().closeAll();
			Notifications.create().title("Evento Saúde").text("Evento Medicação cadastrado com sucesso!").showConfirm();

		} else if (evento == "Vacina" && !(eventoVac.getIdVacina() == null)) {

			eventoVac.setIdEventoSaude(eventosSaude);
			DAOHibernate<EventosSaudeVacina> daoSV = new DAOHibernate<>(EventosSaudeVacina.class);
			daoSV.beginTransaction().save(eventoVac).commitTransaction().closeAll();
			Notifications.create().title("Evento Saúde").text("Evento Vacinação cadastrado com sucesso!").showConfirm();

		} else {

			eventoOutro.setIdEventoSaude(eventosSaude);
			DAOHibernate<EventosSaudeOutros> daoOutro = new DAOHibernate<>(EventosSaudeOutros.class);
			daoOutro.beginTransaction().save(eventoOutro).commitTransaction().closeAll();
			Notifications.create().title("Evento Saúde").text("Evento Outro cadastrado com sucesso!").showConfirm();
		}
		
		
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
