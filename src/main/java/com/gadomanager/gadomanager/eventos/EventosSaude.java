package com.gadomanager.gadomanager.eventos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gadomanager.gadomanager.classes.Veterinario;


@Entity
public class EventosSaude {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento_Saude;
	
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "idTipoEvento")
	private TiposEvento idTipoEvento;
	
	@ManyToOne
	@JoinColumn(name = "idVeterinario")
	private Veterinario idVeterinario;
	
	public EventosSaude() {
	
	}

	public EventosSaude(Date data, TiposEvento idTipoEvento, Veterinario idVeterinario) {
	
		this.data = data;
		this.idTipoEvento = idTipoEvento;
		this.idVeterinario = idVeterinario;
	}

	public Long getIdEvento_Saude() {
		return idEvento_Saude;
	}

	public void setIdEvento_Saude(Long idEvento_Saude) {
		this.idEvento_Saude = idEvento_Saude;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TiposEvento getIdTipoEvento() {
		return idTipoEvento;
	}

	public void setIdTipoEvento(TiposEvento idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public Veterinario getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(Veterinario idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	

	
	

}
