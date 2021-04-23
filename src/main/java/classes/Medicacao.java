package classes;

<<<<<<< HEAD
import javax.persistence.OneToMany;

public class Medicacao {
	
	@OneToMany (targetEntity = "idMedicamento")
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 6c3da58715db03a6b8b637a1fa58551b5a90a6c2
	private Long idMedicamento;
	
	private String nome;
	
	private String principioAtivo;
	
	public Medicacao() {
		
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
	
	
	
}