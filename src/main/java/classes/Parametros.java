package classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Parametros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParametro;

	@OneToOne
	@JoinColumn(name = "idEmpresa_Pessoa")
	private Empresas_Pessoas idEmpresa_Pessoa;

	@Column(nullable = true)
	private int alertaDiasSemPesar;

	@Column(nullable = true)
	private int alertaEngordaDiario;

	private Double taxaEngordaDiario;
	
	public Parametros() {
	
	}

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public Empresas_Pessoas getIdEmpresa_Pessoa() {
		return idEmpresa_Pessoa;
	}

	public void setIdEmpresa_Pessoa(Empresas_Pessoas idEmpresa_Pessoa) {
		this.idEmpresa_Pessoa = idEmpresa_Pessoa;
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