package classes;

public class Medicacao {
	
	private Long idMedicamento;
	
	private String nome;
	
	private String principioAtivo;
	
	 public Medicacao() {
	
	}

	public Medicacao(String nome, String principioAtivo) {
		super();
		this.nome = nome;
		this.principioAtivo = principioAtivo;
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
