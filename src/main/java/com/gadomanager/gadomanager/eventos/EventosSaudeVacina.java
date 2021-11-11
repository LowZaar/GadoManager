package com.gadomanager.gadomanager.eventos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gadomanager.gadomanager.classes.Vacina;

@Entity
public class EventosSaudeVacina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventoSaudeVacina;
	
	@ManyToOne
	@JoinColumn(name = "idEventoSaude")
	private	EventosSaude idEventoSaude;
	
	@ManyToOne
	@JoinColumn(name = "idVacina")
	private Vacina idVacina;
	
	private String Lote;
	
	private String tipoAplicacao;
	
	private String observacoes;
	
	
	public EventosSaudeVacina() {
	
		
	}


	public EventosSaudeVacina(Date data, Long idTipoEvento, Long idVeterinario, EventosSaude idEvento_Saude, Vacina idVacina,
			String lote, String tipoAplicacao, String observacoes) {

		this.setIdEventoSaude(idEvento_Saude);
		this.idVacina = idVacina;
		Lote = lote;
		this.tipoAplicacao = tipoAplicacao;
		this.observacoes = observacoes;
	}


	public Long getIdEventoSaudeVacina() {
		return idEventoSaudeVacina;
	}


	public void setIdEventoSaudeVacina(Long idEventoSaudeVacina) {
		this.idEventoSaudeVacina = idEventoSaudeVacina;
	}


	public EventosSaude getIdEvento_Saude() {
		return idEventoSaude;
	}


	public void setIdEventoSaude(EventosSaude idEvento_Saude) {
		this.idEventoSaude = idEvento_Saude;
	}


	public Vacina getIdVacina() {
		return idVacina;
	}


	public void setIdVacina(Vacina idVacina) {
		this.idVacina = idVacina;
	}


	public String getLote() {
		return Lote;
	}


	public void setLote(String lote) {
		Lote = lote;
	}


	public String getTipoAplicacao() {
		return tipoAplicacao;
	}


	public void setTipoAplicacao(String tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
