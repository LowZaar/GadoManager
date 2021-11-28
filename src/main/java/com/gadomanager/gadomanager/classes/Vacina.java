package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vacinas")
public class Vacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacina;
	
	private String descricao;
	
	public Vacina() {
		
	}

	public Vacina(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
