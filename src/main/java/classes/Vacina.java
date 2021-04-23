package classes;

<<<<<<< HEAD
import javax.persistence.OneToMany;

public class Vacina {
	
	@OneToMany(mappedBy = "idVacina")
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 6c3da58715db03a6b8b637a1fa58551b5a90a6c2
	private Long idVacina;
	
	private String descricao;
	
	public Vacina() {
		
	}

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
