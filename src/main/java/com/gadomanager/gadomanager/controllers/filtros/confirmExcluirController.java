package com.gadomanager.gadomanager.controllers.filtros;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Usuarios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
