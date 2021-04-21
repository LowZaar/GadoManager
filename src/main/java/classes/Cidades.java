package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCidade;
	
	private String nome;
	
	@ManyToOne
	private Estados idEstado;
	
	public Cidades() {
	
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estados getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Estados idEstado) {
		this.idEstado = idEstado;
	}
	
	
}
