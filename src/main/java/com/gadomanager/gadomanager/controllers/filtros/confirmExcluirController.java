package com.gadomanager.gadomanager.controllers.filtros;

import org.springframework.stereotype.Component;

import com.gadomanager.gadomanager.classes.Alimentos;
import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Medicamentos;
import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.classes.Usuarios;
import com.gadomanager.gadomanager.classes.Vacina;
import com.gadomanager.gadomanager.classes.Veterinario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Component
public class confirmExcluirController {

    @FXML
    private Label labelClass;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnCancelar;
    
    private Boolean delete;
    
    public void setClass(Object classobj) {
    	if (classobj instanceof Bovinos) {
    		String texto = "Nome: " + ((Bovinos) classobj).getNome() + "\n";
    		texto += "Brinco: " + ((Bovinos) classobj).getIdBrinco() + "\n";
			labelClass.setText(texto);
		}else if (classobj instanceof Usuarios) {
			String texto = "Nome: " + ((Usuarios) classobj).getNome() + "\n";
			texto += "Usuario: " + ((Usuarios) classobj).getUsuario() + "\n";
			if (((Usuarios) classobj).isUsuarioMestre()) {
				texto += "É um Usuário Mestre";
			}
			labelClass.setText(texto);			
		}else if (classobj instanceof Alimentos) {
			String texto = "Alimentação  iniciada em " + ((Alimentos) classobj).getDataInicio() + "\n";
			texto += "Ração utilizada: " + ((Alimentos) classobj).getIdracao().getDescricao() + "\n";
			texto += "Para o Rebanho " + ((Alimentos) classobj).getIdRebanho().getNome() + "\n";
			labelClass.setText(texto);
		}else if (classobj instanceof Vacina) {
    		String texto = "Descrição: " + ((Vacina) classobj).getDescricao() + "\n";
    		labelClass.setText(texto);
    	}else if (classobj instanceof Veterinario) {
    		String texto = "Nome: " + ((Veterinario) classobj).getNome() + "\n";
    		texto += "CPF: " + ((Veterinario) classobj).getCpf() + "\n";
    		texto += "CRMV: " + ((Veterinario) classobj).getCrmv() + "\n";
    		labelClass.setText(texto);
    	}else if (classobj instanceof Racas) {
    		String texto = "Nome: " + ((Racas) classobj).getNomeRaca() + "\n";
    		labelClass.setText(texto);
    	}else if (classobj instanceof Medicamentos) {
    		String texto = "Nome: " + ((Medicamentos) classobj).getNome() + "\n";
    		labelClass.setText(texto);
    	}else if (classobj instanceof Racas) {
    		String texto = "Nome: " + ((Racas) classobj).getNomeRaca() + "\n";
    		labelClass.setText(texto);
    	}
    }

    public boolean returnDelete() {
    	return this.delete;
    }
    
    @FXML
    private void initialize() {
    	delete = false;
    	
    	btnDeletar.setOnAction(e ->{
    		this.delete = true;
    		Stage window = (Stage) btnDeletar.getScene().getWindow();
    		window.close();
    	});
    	
    	btnCancelar.setOnAction(e ->{
    		this.delete = false;
    		Stage window = (Stage) btnDeletar.getScene().getWindow();
    		window.close();
    	});
    	
    }
    
}
