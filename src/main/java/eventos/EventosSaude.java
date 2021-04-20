package eventos;

import java.util.Date;

public class EventosSaude {
	
	private Long idEvento_Saude;
	
	private Date data;
	
	private Long idTipoEvento;
	
	private Long idVeterinario;
	
	public EventosSaude() {
	
	}

	public EventosSaude(Date data, Long idTipoEvento, Long idVeterinario) {
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
