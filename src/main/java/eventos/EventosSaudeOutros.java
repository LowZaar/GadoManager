package eventos;

import java.util.Date;

public class EventosSaudeOutros  extends EventosSaude{

	private Long idEventosSaudeOutros;
	
	private Long idEventoSaude;
	
	private String Observacoes;
	
	
	public EventosSaudeOutros() {
		// TODO Auto-generated constructor stub
	}


	public EventosSaudeOutros(Date data, Long idTipoEvento, Long idVeterinario, Long idEventoSaude,
			String observacoes) {
		super(data, idTipoEvento, idVeterinario);
		this.idEventoSaude = idEventoSaude;
		Observacoes = observacoes;
	}


	public Long getIdEventosSaudeOutros() {
		return idEventosSaudeOutros;
	}


	public void setIdEventosSaudeOutros(Long idEventosSaudeOutros) {
		this.idEventosSaudeOutros = idEventosSaudeOutros;
	}


	public String getObservacoes() {
		return Observacoes;
	}


	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
	}
	
	

	
	
	
}
