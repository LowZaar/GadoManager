package com.gadomanager.gadomanager.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.BCS;
import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.repos.BCSRepository;
import com.gadomanager.gadomanager.repos.BovinoRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class cadastroBCSController {
	
	@Autowired
	private BCSRepository repo;
	
	@Autowired
	private BovinoRepository bovRepo;
	
	@FXML
	private TextField txtIndiceBCS;
	
	@FXML
	private DatePicker dateData;
	
	@FXML
	private ComboBox<String> comboBovino;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Usuarios user;
	
	
	public Usuarios getUser() {
		return user;
	}



	public void setUser(Usuarios user) {
		this.user = user;
	}

	private Date localDateToDate(LocalDate data) {
		ZoneId zoneidDefault = ZoneId.systemDefault();

		return Date.from(data.atStartOfDay(zoneidDefault).toInstant());
	}
	
	public void populateCombo() {
		
		List<Bovinos> query = bovRepo.findByIdEmpresaPessoas(user.getIdEmpresas_Pessoa());
		
		comboBovino.getItems().add("Selecione...");
		for (Bovinos bovinos : query) {
			comboBovino.getItems().add(bovinos.getNome());
		}
		
	}
	
	private Bovinos findBovino(String bovinoNome) {
		
		Bovinos bovino = bovRepo.findByIdEmpresaPessoasAndNome(
				user.getIdEmpresas_Pessoa(), bovinoNome);

		if (bovino == null) {
			return null;
		}else {
			return bovino;
		}
	}
	
	@FXML
	public void salvar() {
		Double indiceBCS = Double.parseDouble(txtIndiceBCS.getText()); 
		Date dataBCS = localDateToDate(dateData.getValue());
		Bovinos bovino = findBovino(comboBovino.getValue());
		
		BCS bcsObj = new BCS(bovino, dataBCS, indiceBCS);
		
		repo.save(bcsObj);
		
		Notifications.create().title("Alerta").text("Novo BCS adicionado com sucesso!")
		.showConfirm();
		
		txtIndiceBCS.clear();
		comboBovino.setValue(null);
		
	}
	
	@FXML
	public void cancelar() {
		
		Stage window = (Stage) btnCancelar.getScene().getWindow();
		window.close();
	}
	
}
