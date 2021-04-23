package classes;

import java.util.Date;

import javax.persistence.Id;

public class Alimentos {

	@Id
	private Long idAlimento;
	
	private Long idRebanho;
	
	private Date dataInicio;
	
	private Date dataTermino;
	
	private Long idracao;
	
	private String observacoes;
	
	public Alimentos() {
		// TODO Auto-generated constructor stub
	}

	public Alimentos(Long idRebanho, Date dataInicio, Date dataTermino, Long idracao, String observacoes) {
		super();
		this.idRebanho = idRebanho;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.idracao = idracao;
		this.observacoes = observacoes;
	}

	public Long getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Long idRebanho) {
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

	public Long getIdracao() {
		return idracao;
	}

	public void setIdracao(Long idracao) {
		this.idracao = idracao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
}
