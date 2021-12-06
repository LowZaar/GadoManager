package com.gadomanager.gadomanager.controllers.eventos;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.classes.Veterinario;
import com.gadomanager.gadomanager.eventos.EventosSaude;
import com.gadomanager.gadomanager.eventos.EventosSaudeBovinos;
import com.gadomanager.gadomanager.eventos.EventosSaudeMedicacao;
import com.gadomanager.gadomanager.eventos.EventosSaudeOutros;
import com.gadomanager.gadomanager.eventos.EventosSaudeVacina;
import com.gadomanager.gadomanager.eventos.TiposEvento;
import com.gadomanager.gadomanager.repos.VeterinarioRepository;
import com.gadomanager.gadomanager.repos.eventos.EventoBovinoRepository;
import com.gadomanager.gadomanager.repos.eventos.EventoMedicamentoRepository;
import com.gadomanager.gadomanager.repos.eventos.EventoOutrosRepository;
import com.gadomanager.gadomanager.repos.eventos.EventoSaudeRepository;
import com.gadomanager.gadomanager.repos.eventos.EventoVacinaRepository;
import com.gadomanager.gadomanager.repos.eventos.TiposEventoRepository;

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

@Component
public class cadastroEventoSaudeController {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private TiposEventoRepository tipoEventoRepo;
	
	@Autowired
	private VeterinarioRepository vetRepo;
	
	@Autowired
	private EventoSaudeRepository eventoRepo;
	
	@Autowired
	private EventoBovinoRepository eventoBovRepo;
	
	@Autowired
	private EventoMedicamentoRepository eventoMedRepo;
	
	@Autowired
	private EventoVacinaRepository eventoVacRepo;
	
	@Autowired
	private EventoOutrosRepository eventoOutrosRepo;
	
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
				
		List<Veterinario> listaVet = Streamable.of(vetRepo.findAll()).toList();

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
			loader.setControllerFactory(context::getBean);
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
			loader.setControllerFactory(context::getBean);
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
			loader.setControllerFactory(context::getBean);
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

		TiposEvento tipoEvento = tipoEventoRepo.findByTag(tagEvento);
		

		Veterinario vet = vetRepo.findByNome(veterinarioNome);
				
		EventosSaude eventosSaude = new EventosSaude();
		eventosSaude.setData(data);
		eventosSaude.setIdTipoEvento(tipoEvento);
		eventosSaude.setIdVeterinario(vet);
		
		eventoRepo.save(eventosSaude);
		
		String evento = getEvento();
		if (evento == "Bovinos" && !(eventoBov.getIdBovino() == null)) {
			
			eventoBov.setObservacoes(txtObservacoes.getText());
			eventoBov.setIdEventoSaude(eventosSaude);
			eventoBov.setData(data);
			
			eventoBovRepo.save(eventoBov);
			
			Notifications.create().title("Evento Saúde").text("Evento Bovino cadastrado com sucesso!").showConfirm();
			

		} else if (evento == "Medicação" && !(eventoMed.getIdMedicamento() == null)) {

			eventoMed.setIdEventoSaude(eventosSaude);
			
			eventoMedRepo.save(eventoMed);
			
			Notifications.create().title("Evento Saúde").text("Evento Medicação cadastrado com sucesso!").showConfirm();

		} else if (evento == "Vacina" && !(eventoVac.getIdVacina() == null)) {

			eventoVac.setIdEventoSaude(eventosSaude);
			
			eventoVacRepo.save(eventoVac);
			
			Notifications.create().title("Evento Saúde").text("Evento Vacinação cadastrado com sucesso!").showConfirm();

		} else {

			eventoOutro.setIdEventoSaude(eventosSaude);
			
			eventoOutrosRepo.save(eventoOutro);
			
			Notifications.create().title("Evento Saúde").text("Evento Outro cadastrado com sucesso!").showConfirm();
		}
		
		
	}

	@FXML
	public void cancelar() {

		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
}
