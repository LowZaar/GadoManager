package com.gadomanager.gadomanager.eventos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gadomanager.gadomanager.classes.Bovinos;


@Entity
public class EventosSaudeBovinos{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventosSaudeBovinos;
	
	@ManyToOne
	@JoinColumn(name = "idEventoSaude")
	private EventosSaude idEventoSaude;
	
	@ManyToOne
	@JoinColumn(name = "idBovino")
	private Bovinos idBovino;
	
	private Date data;
	
	private String observacoes;

	
	public EventosSaudeBovinos() {
	
	}




	public EventosSaudeBovinos(EventosSaude idEventoSaude, Bovinos idBovino, Date data, String observacoes) {
		super();
		this.idEventoSaude = idEventoSaude;
		this.idBovino = idBovino;
		this.data = data;
		this.observacoes = observacoes;
	}




	public Long getIdEventosSaudeBovinos() {
		return idEventosSaudeBovinos;
	}

	public void setIdEventosSaudeBovinos(Long idEventosSaudeBovinos) {
		this.idEventosSaudeBovinos = idEventosSaudeBovinos;
	}


	public EventosSaude getIdEventoSaude() {
		return idEventoSaude;
	}



	public void setIdEventoSaude(EventosSaude idEventoSaude) {
		this.idEventoSaude = idEventoSaude;
	}



	public Bovinos getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Bovinos idBovino) {
		this.idBovino = idBovino;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	
	
	
	
}
