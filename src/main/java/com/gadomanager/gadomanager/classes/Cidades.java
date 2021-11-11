package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cidades")
public class Cidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCidade;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "idEstado")
	private Estados estado;
	
	public Cidades() {
	
	}
	

	public Cidades(String nome, Estados estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}



	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setIdEstado(Estados idEstado) {
		this.estado = idEstado;
	}
	
	
}
