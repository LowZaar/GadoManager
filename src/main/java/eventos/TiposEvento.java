package eventos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TiposEvento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "IdTipoEvento", nullable = false)
	@OneToMany(mappedBy = "idTipoEvento")
	private Long idTipoEvento;
	
	@Column (name = "Descri��o", nullable = true)
	private String descricao;
	
	@Column (name = "Tag", nullable = true)
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
