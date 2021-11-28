package com.gadomanager.gadomanager.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Parametros")
public class Parametros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParametro;

	@OneToOne
	@JoinColumn(name = "idEmpresa_Pessoa")
	private Empresas_Pessoas idEmpresaPessoa;

	@Column(nullable = true)
	private int alertaDiasSemPesar;

	@Column(nullable = true)
	private int alertaEngordaDiario;

	private Double taxaEngordaDiario;
	
	public Parametros() {
	
	}
	
	public Parametros(Empresas_Pessoas idEmpresa_Pessoa, int alertaDiasSemPesar, int alertaEngordaDiario,
			Double taxaEngordaDiario) {
		
		this.idEmpresaPessoa = idEmpresa_Pessoa;
		this.alertaDiasSemPesar = alertaDiasSemPesar;
		this.alertaEngordaDiario = alertaEngordaDiario;
		this.taxaEngordaDiario = taxaEngordaDiario;
	}



	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public Empresas_Pessoas getIdEmpresa_Pessoa() {
		return idEmpresaPessoa;
	}

	public void setIdEmpresa_Pessoa(Empresas_Pessoas idEmpresa_Pessoa) {
		this.idEmpresaPessoa = idEmpresa_Pessoa;
	}

	public int getAlertaDiasSemPesar() {
		return alertaDiasSemPesar;
	}

	public void setAlertaDiasSemPesar(int alertaDiasSemPesar) {
		this.alertaDiasSemPesar = alertaDiasSemPesar;
	}

	public int getAlertaEngordaDiario() {
		return alertaEngordaDiario;
	}

	public void setAlertaEngordaDiario(int alertaEngordaDiario) {
		this.alertaEngordaDiario = alertaEngordaDiario;
	}

	public Double getTaxaEngordaDiario() {
		return taxaEngordaDiario;
	}

	public void setTaxaEngordaDiario(Double taxaEngordaDiario) {
		this.taxaEngordaDiario = taxaEngordaDiario;
	}
	
	

}