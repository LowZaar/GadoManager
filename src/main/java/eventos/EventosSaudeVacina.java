package eventos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import classes.Vacina;

@Entity
public class EventosSaudeVacina extends EventosSaude{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "IdEventoSaudeVacina", nullable = false)
	private Long idEventoSaudeVacina;
	
	@ManyToOne (targetEntity = EventosSaude.class)
	@Column (name = "IdEventoSaude", nullable = false)
	private EventosSaude idEvento_Saude;
	
	@ManyToOne(targetEntity = Vacina.class)
	@Column (name = "IdVacina", nullable = false)
	private Vacina idVacina;
	
	@Column (name = "Lote", nullable = true)
	private String Lote;
	
	@Column (name = "TipoAplicacao", nullable = true)
	private String tipoAplicacao;
	
	@Column (name = "Observacoes", nullable = true)
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
