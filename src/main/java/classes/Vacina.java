package classes;

public class Vacina {
	
	private Long idVacina;
	
	private String descricao;
	
	
	public Vacina() {
	
	}


	public Vacina(String descricao) {
		super();
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
