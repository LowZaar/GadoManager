package eventos;

public class TiposEvento {

	private Long idTipoEvento;
	
	private String descricao;
	
	private char tag;
	
	public TiposEvento() {
		
	}

	public TiposEvento(String descricao, char tag) {
		super();
		this.descricao = descricao;
		this.tag = tag;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getTag() {
		return tag;
	}

	public void setTag(char tag) {
		this.tag = tag;
	}
	
	
}
