package eventos;

import java.util.Date;

public class EventosSaudeVacina extends EventosSaude{
	
	private Long idEventoSaudeVacina;
	
	private Long idEvento_Saude;
	
	private Long idVacina;
	
	private String Lote;
	
	private String tipoAplicacao;
	
	private String observacoes;
	
	
	public EventosSaudeVacina() {
		// TODO Auto-generated constructor stub
	}


	public EventosSaudeVacina(Date data, Long idTipoEvento, Long idVeterinario, Long idEvento_Saude, Long idVacina,
			String lote, String tipoAplicacao, String observacoes) {
		super(data, idTipoEvento, idVeterinario);
		this.idEvento_Saude = idEvento_Saude;
		this.idVacina = idVacina;
		Lote = lote;
		this.tipoAplicacao = tipoAplicacao;
		this.observacoes = observacoes;
	}


	public Long getIdEventoSaudeVacina() {
		return idEventoSaudeVacina;
	}


	public void setIdEventoSaudeVacina(Long idEventoSaudeVacina) {
		this.idEventoSaudeVacina = idEventoSaudeVacina;
	}


	public Long getIdVacina() {
		return idVacina;
	}


	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}


	public String getLote() {
		return Lote;
	}


	public void setLote(String lote) {
		Lote = lote;
	}


	public String getTipoAplicacao() {
		return tipoAplicacao;
	}


	public void setTipoAplicacao(String tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
