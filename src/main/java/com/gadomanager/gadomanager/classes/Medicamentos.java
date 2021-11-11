package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Medicamentos")
public class Medicamentos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedicamento;
	
	private String nome;
	
	private String principioAtivo;
	
	public Medicamentos() {
		
	}
	
	public Medicamentos(String nome, String principioAtivo) {
		super();
		this.nome = nome;
		this.principioAtivo = principioAtivo;
	}



	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
	
	
	
}