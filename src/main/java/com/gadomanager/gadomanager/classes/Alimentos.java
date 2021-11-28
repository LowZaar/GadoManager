package com.gadomanager.gadomanager.classes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Alimentos")
public class Alimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlimento;
	
	@ManyToOne
	@JoinColumn(name = "idRebanho")
	private Rebanhos idRebanho;
	
	@Column(name = "dataInicio")
	private Date dataInicio;
	
	@Column(name = "dataTermino")
	private Date dataTermino;
	
	@ManyToOne
	@JoinColumn(name = "idRacao")
	private Racoes idracao;
	
	private String observacoes;
	
	public Alimentos() {
	
	}
	
	public Alimentos(Rebanhos idRebanho, Date dataInicio, Date dataTermino, Racoes idracao, String observacoes) {
		this.idRebanho = idRebanho;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.idracao = idracao;
		this.observacoes = observacoes;
	}



	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public Rebanhos getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Rebanhos idRebanho) {
		this.idRebanho = idRebanho;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Racoes getIdracao() {
		return idracao;
	}

	public void setIdracao(Racoes idracao) {
		this.idracao = idracao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	

}
