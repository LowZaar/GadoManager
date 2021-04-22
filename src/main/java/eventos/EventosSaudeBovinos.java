package eventos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import classes.Bovinos;
import classes.Rebanhos;

@Entity
public class EventosSaudeBovinos{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventosSaudeBovinos;
	
	@ManyToOne
	private Rebanhos idRebanho;
	
	@ManyToOne
	private Bovinos idBovino;
	
	public EventosSaudeBovinos() {
	
	}

	public EventosSaudeBovinos(Date data, Long idTipoEvento, Long idVeterinario, Rebanhos idRebanho, Bovinos idBovino) {
		this.idRebanho = idRebanho;
		this.idBovino = idBovino;
	}

	
	public Long getIdEventosSaudeBovinos() {
		return idEventosSaudeBovinos;
	}

	public void setIdEventosSaudeBovinos(Long idEventosSaudeBovinos) {
		this.idEventosSaudeBovinos = idEventosSaudeBovinos;
	}

	public Rebanhos getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Rebanhos idRebanho) {
		this.idRebanho = idRebanho;
	}

	public Bovinos getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Bovinos idBovino) {
		this.idBovino = idBovino;
	}

	
	
	
}
