package com.gadomanager.gadomanager.eventos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gadomanager.gadomanager.classes.Medicamentos;

@Entity
public class EventosSaudeMedicacao{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventoSaudeMedicacao;
	
	@ManyToOne
	@JoinColumn(name = "idEventoSaude")
	private EventosSaude idEventoSaude;
	
	@ManyToOne
	@JoinColumn(name = "idMedicamento")
	private Medicamentos idMedicamento;
	
	private String lote;
	
	private String tipoAplicacao;
	
	private int diasTratamento;
	
	private String posologia;
	
	private String observacoes;
	
	public EventosSaudeMedicacao() {
	
	}

	public EventosSaudeMedicacao(EventosSaude idEventoSaude, Medicamentos idMedicamento, String lote, String tipoAplicacao,
			int diasTratamento, String posologia, String observacoes) {
		this.idEventoSaude = idEventoSaude;
		this.idMedicamento = idMedicamento;
		this.lote = lote;
		this.tipoAplicacao = tipoAplicacao;
		this.diasTratamento = diasTratamento;
		this.posologia = posologia;
		this.observacoes = observacoes;
	}

	public EventosSaude getIdEventoSaude() {
		return idEventoSaude;
	}

	public void setIdEventoSaude(EventosSaude idEventoSaude) {
		this.idEventoSaude = idEventoSaude;
	}

	public Medicamentos getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Medicamentos idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(String tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}

	public int getDiasTratamento() {
		return diasTratamento;
	}

	public void setDiasTratamento(int diasTratamento) {
		this.diasTratamento = diasTratamento;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
}
