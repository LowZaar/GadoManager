package com.gadomanager.gadomanager.eventos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventosSaudeOutros{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventosSaudeOutros;
	
	@ManyToOne
	@JoinColumn(name = "idEventoSaude")
	private EventosSaude idEventoSaude;
	
	private String Observacoes;
	
	
	public EventosSaudeOutros() {
		// TODO Auto-generated constructor stub
	}


	public EventosSaudeOutros(Date data, Long idTipoEvento, Long idVeterinario, EventosSaude idEventoSaude,
			String observacoes) {
		this.setIdEventoSaude(idEventoSaude);
		Observacoes = observacoes;
	}


	public Long getIdEventosSaudeOutros() {
		return idEventosSaudeOutros;
	}


	public void setIdEventosSaudeOutros(Long idEventosSaudeOutros) {
		this.idEventosSaudeOutros = idEventosSaudeOutros;
	}


	public EventosSaude getIdEventoSaude() {
		return idEventoSaude;
	}


	public void setIdEventoSaude(EventosSaude idEventoSaude) {
		this.idEventoSaude = idEventoSaude;
	}


	public String getObservacoes() {
		return Observacoes;
	}


	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
	}
	
	

	
	
	
}
