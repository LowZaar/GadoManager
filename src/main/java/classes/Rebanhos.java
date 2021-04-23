package classes;

<<<<<<< HEAD
import javax.persistence.OneToMany;

public class Rebanhos {
	
	@OneToMany(mappedBy = "idRebanho")
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rebanhos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 6c3da58715db03a6b8b637a1fa58551b5a90a6c2
	private Long idRebanho;
	
	private String nome;
	
	private String descricao;
	
	@ManyToOne
	private Empresas_Pessoas idEmpresaPessoa;
	
	public Rebanhos() {
		
	}

	public Long getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Long idRebanho) {
		this.idRebanho = idRebanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresas_Pessoas getIdEmpresaPessoa() {
		return idEmpresaPessoa;
	}

	public void setIdEmpresaPessoa(Empresas_Pessoas idEmpresaPessoa) {
		this.idEmpresaPessoa = idEmpresaPessoa;
	}
	
	
	
}
