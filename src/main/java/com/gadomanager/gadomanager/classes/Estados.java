package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estados")
public class Estados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstado;
	
	private String Sigla;
	
	public Estados() {
	
	}

	public Estados(String sigla) {
		super();
		Sigla = sigla;
	}

	public String getSigla() {
		return Sigla;
	}

	public void setSigla(String sigla) {
		Sigla = sigla;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	
	
}
