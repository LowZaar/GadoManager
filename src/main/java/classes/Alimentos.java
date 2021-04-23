package classes;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import javax.persistence.Id;

public class Alimentos {

	@Id
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Alimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 6c3da58715db03a6b8b637a1fa58551b5a90a6c2
	private Long idAlimento;
	
	@OneToMany
	private List<Rebanhos> idRebanho;
	
	private Date dataInicio;
	
	private Date dataTermino;
	
	@ManyToOne
	private Racoes idracao;
	
	private String observacoes;
	
	public Alimentos() {
	
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public List<Rebanhos> getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(List<Rebanhos> idRebanho) {
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
