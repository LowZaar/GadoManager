package com.gadomanager.gadomanager.classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BCS")
public class BCS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBCS;
	
	@OneToOne
	@JoinColumn(name = "idBovino")
	private Bovinos idBovino;
	
	private Date data;
	
	private Double IndiceBCS;
	
	public BCS() {
	
	}
	
	public BCS(Bovinos idBovino, Date data, Double indiceBCS) {
		this.idBovino = idBovino;
		this.data = data;
		this.IndiceBCS = indiceBCS;
	}



	public Long getIdBCS() {
		return idBCS;
	}

	public void setIdBCS(Long idBCS) {
		this.idBCS = idBCS;
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

	public Double getIndiceBCS() {
		return IndiceBCS;
	}

	public void setIndiceBCS(Double indiceBCS) {
		IndiceBCS = indiceBCS;
	}
	
	
}
