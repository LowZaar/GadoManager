package eventos;

public class EventosSaudeMedicacao extends EventosSaude{

	private Long idEventoSaudeMedicacao;
	
	private Long idEventoSaude;
	
	private Long idMedicamento;
	
	private String lote;
	
	private String tipoAplicacao;
	
	private int diasTratamento;
	
	private String posologia;
	
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
