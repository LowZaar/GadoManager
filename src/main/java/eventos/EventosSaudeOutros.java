package eventos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import classes.Veterinario;

@Entity
public class EventosSaudeOutros  extends EventosSaude{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventosSaudeOutros;
	
	@ManyToOne(targetEntity = EventosSaude.class)
	@Column(name = "IdEventoSaude", nullable = false)
	private EventosSaude idEventoSaude;
	
	@Column (nullable = true)
	private String Observacoes;
	
	
	public EventosSaudeOutros() {
		// TODO Auto-generated constructor stub
	}


	public EventosSaudeOutros(Date data, TiposEvento idTipoEvento, Veterinario idVeterinario, EventosSaude idEventoSaude,
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
