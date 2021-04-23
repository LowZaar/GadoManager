package eventos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import classes.Veterinario;

@Entity
public class EventosSaude {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany (mappedBy = "idEventoSaude")
	private Long idEvento_Saude;
	
	@Column(name = "Data")
	private Date data;
	
	@Column(name = "TipoEvento")
	@ManyToOne(targetEntity = TiposEvento.class)
	private TiposEvento idTipoEvento;
	
	@Column(name = "IdVeterinario")
	@ManyToOne (targetEntity = Veterinario.class)
	private Veterinario idVeterinario;
	
	public EventosSaude() {
	
	}

	public EventosSaude(Date data, TiposEvento idTipoEvento, Veterinario idVeterinario) {
		super();
		this.data = data;
		this.idTipoEvento = idTipoEvento;
		this.idVeterinario = idVeterinario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdTipoEvento() {
		return idTipoEvento;
	}

	public void setIdTipoEvento(Long idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public Long getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	
	
	

}
