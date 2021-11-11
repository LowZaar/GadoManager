package com.gadomanager.gadomanager.eventos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TiposEvento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoEvento;
	
	private String descricao;
	
	private char tag;
	
	public TiposEvento() {
		
	}

	public TiposEvento(String descricao, char tag) {
		this.descricao = descricao;
		this.tag = tag;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getTag() {
		return tag;
	}

	public void setTag(char tag) {
		this.tag = tag;
	}
	
	
}
