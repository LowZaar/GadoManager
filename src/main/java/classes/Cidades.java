package classes;

public class Cidades {
	
	private Long idCidade;
	
	private String nome;
	
	private Long idEstado;
	
	public Cidades() {
	
	}

	public Cidades(String nome, Long idEstado) {
		super();
		this.nome = nome;
		this.idEstado = idEstado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
	
}
