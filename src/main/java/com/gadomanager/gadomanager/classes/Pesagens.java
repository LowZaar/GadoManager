package com.gadomanager.gadomanager.classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pesagens")
public class Pesagens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPesagem;
	
	private Date dataPesagem;
	
	private Double peso;
	
	private String Obervacoes;
	
	@ManyToOne
	@JoinColumn(name = "idBovino")
	private Bovinos idBovino;
	
	public Pesagens() {
		
	}
	
	public Pesagens(Date dataPesagem, Double peso, String obervacoes, Bovinos idBovino) {
		
		this.dataPesagem = dataPesagem;
		this.peso = peso;
		this.Obervacoes = obervacoes;
		this.idBovino = idBovino;
	}



	public Long getIdPesagem() {
		return idPesagem;
	}

	public void setIdPesagem(Long idPesagem) {
		this.idPesagem = idPesagem;
	}

	public Date getDataPesagem() {
		return dataPesagem;
	}

	public void setDataPesagem(Date dataPesagem) {
		this.dataPesagem = dataPesagem;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getObervacoes() {
		return Obervacoes;
	}

	public void setObervacoes(String obervacoes) {
		Obervacoes = obervacoes;
	}

	public Bovinos getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Bovinos idBovino) {
		this.idBovino = idBovino;
	}

	
}
