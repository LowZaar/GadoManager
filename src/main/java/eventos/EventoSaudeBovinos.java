package eventos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import classes.Bovinos;
import classes.Rebanhos;
import classes.Veterinario;

@Entity
public class EventoSaudeBovinos extends EventosSaude{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEventosSaudeBovinos")
	private Long idEventosSaudeBovinos;
	
	@Column(name = "IdRebanho")
	@ManyToOne (targetEntity = Rebanhos.class)
	private Rebanhos idRebanho;
	
	@ManyToOne (targetEntity = Bovinos.class)
	@Column (name = "IdBovino")
	private Bovinos idBovino;
	
	public EventoSaudeBovinos() {
	
	}

	public EventoSaudeBovinos(Date data, TiposEvento idTipoEvento, Veterinario idVeterinario, Rebanhos idRebanho, Bovinos idBovino) {
		super(data, idTipoEvento, idVeterinario);
		this.idRebanho = idRebanho;
		this.idBovino = idBovino;
	}

	
	public Long getIdEventosSaudeBovinos() {
		return idEventosSaudeBovinos;
	}

	public void setIdEventosSaudeBovinos(Long idEventosSaudeBovinos) {
		this.idEventosSaudeBovinos = idEventosSaudeBovinos;
	}

	public Long getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Long idRebanho) {
		this.idRebanho = idRebanho;
	}

	public Long getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Long idBovino) {
		this.idBovino = idBovino;
	}

	
	
	
}
