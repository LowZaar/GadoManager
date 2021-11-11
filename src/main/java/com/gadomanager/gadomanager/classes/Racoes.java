package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Racoes")
public class Racoes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRacao;
	
	private String descricao;
	
	private String Observacao;
	
	
	public Racoes() {
	}

	public Racoes(String descricao, String observacao) {
		super();
		this.descricao = descricao;
		Observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	
	
}
