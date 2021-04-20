package classes;

public class Rebanhos {
	
	private Long idRebanho;
	
	private String nome;
	
	private String descricao;
	
	private Long idEmpresaPessoa;
	
	public Rebanhos() {
		// TODO Auto-generated constructor stub
	}

	public Rebanhos(String nome, String descricao, Long idEmpresaPessoa) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.idEmpresaPessoa = idEmpresaPessoa;
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

	public Long getIdEmpresaPessoa() {
		return idEmpresaPessoa;
	}

	public void setIdEmpresaPessoa(Long idEmpresaPessoa) {
		this.idEmpresaPessoa = idEmpresaPessoa;
	}
	
	
}
