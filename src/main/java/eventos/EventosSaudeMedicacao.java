package eventos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import classes.Medicacao;

@Entity
public class EventosSaudeMedicacao extends EventosSaude{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEventoSaudeMedicacao;
	
	@Column(name = "IdEventoSaude", nullable = false)
	@ManyToOne (targetEntity = EventosSaude.class)
	private EventosSaude idEventoSaude;

	@ManyToOne (targetEntity = Medicacao.class)
	@Column (name = "IdMedicamento" , nullable = false)
	private Medicacao idMedicamento;
	
	@Column(name = "Lote", nullable = true)
	private String lote;
	
	@Column(name = "TipoDeAplicacao", nullable = true)
	private String tipoAplicacao;
	
	@Column(name = "DiasDeTratamento", nullable = true)
	private int diasTratamento;
	
	@Column(name = "Posologia", nullable = true)
	private String posologia;
	
	@Column(name = "Observacoes", nullable = true)
	private String observacoes;
	
	public EventosSaudeMedicacao() {
	
	}

	public EventosSaudeMedicacao(Long idEventoSaude, Long idMedicamento, String lote, String tipoAplicacao,
			int diasTratamento, String posologia, String observacoes) {
		super();
		this.idEventoSaude = idEventoSaude;
		this.idMedicamento = idMedicamento;
		this.lote = lote;
		this.tipoAplicacao = tipoAplicacao;
		this.diasTratamento = diasTratamento;
		this.posologia = posologia;
		this.observacoes = observacoes;
	}

	public Long getIdEventoSaude() {
		return idEventoSaude;
	}

	public void setIdEventoSaude(Long idEventoSaude) {
		this.idEventoSaude = idEventoSaude;
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(String tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}

	public int getDiasTratamento() {
		return diasTratamento;
	}

	public void setDiasTratamento(int diasTratamento) {
		this.diasTratamento = diasTratamento;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	
}
