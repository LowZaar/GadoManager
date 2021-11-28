package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Racas")
public class Racas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRaca;
	
	private String nomeRaca;
	
	public Racas() {
		
	}

	
	
	public Racas(String nomeRaca) {
		super();
		this.nomeRaca = nomeRaca;
	}



	public Long getIdRaca() {
		return idRaca;
	}
	
	public void setIdRaca(Long idRaca) {
		this.idRaca = idRaca;
	}
	
	public String getNomeRaca() {
		return nomeRaca;
	}
	
	public void setNomeRaca(String nomeRaca) {
		this.nomeRaca = nomeRaca;
	}
		
	
}
