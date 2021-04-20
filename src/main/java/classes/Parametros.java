package classes;

public class Parametros {

	private Long idParametro;

	private Long idEmpresa_Pessoa;

	private int alertaDiasSemPesar;

	private int alertaEngordaDiario;

	private Double taxaEngordaDiario;

	
	public Parametros() {
		// TODO Auto-generated constructor stub
	}


	public Parametros(Long idEmpresa_Pessoa, int alertaDiasSemPesar, int alertaEngordaDiario,
			Double taxaEngordaDiario) {
		super();
		this.idEmpresa_Pessoa = idEmpresa_Pessoa;
		this.alertaDiasSemPesar = alertaDiasSemPesar;
		this.alertaEngordaDiario = alertaEngordaDiario;
		this.taxaEngordaDiario = taxaEngordaDiario;
	}


	public Long getIdEmpresa_Pessoa() {
		return idEmpresa_Pessoa;
	}


	public void setIdEmpresa_Pessoa(Long idEmpresa_Pessoa) {
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
