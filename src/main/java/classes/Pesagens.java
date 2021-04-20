package classes;

import java.util.Date;

public class Pesagens {
	
	private Long idPesagem;
	
	private Date dataPesagem;
	
	private Double peso;
	
	private String Obervacoes;
	
	private Long idBovino;
	
	public Pesagens() {
		
	}

	public Pesagens(Date dataPesagem, Double peso, String obervacoes, Long idBovino) {
		super();
		this.dataPesagem = dataPesagem;
		this.peso = peso;
		Obervacoes = obervacoes;
		this.idBovino = idBovino;
	}
	
	
}
