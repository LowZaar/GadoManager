package eventos;

import java.util.Date;

public class EventoSaudeBovinos extends EventosSaude{

	private Long idEventosSaudeBovinos;
	
	private Long idRebanho;
	
	private Long idBovino;
	
	public EventoSaudeBovinos() {
	
	}

	public EventoSaudeBovinos(Date data, Long idTipoEvento, Long idVeterinario, Long idRebanho, Long idBovino) {
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
