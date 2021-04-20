package classes;

public class Racoes {

	private Long idRacao;
	
	private String descricao;
	
	private String Observacao;
	
	public Racoes() {
		// TODO Auto-generated constructor stub
	}

	public Racoes(String descricao, String observacao) {
		super();
		this.descricao = descricao;
		Observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	
	
}
