package classes;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BCS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBCS;
	
	private Long idBovino;
	
	private Date data;
	
	private Double IndiceBCS;
	
	public BCS() {
		// TODO Auto-generated constructor stub
	}

	public BCS(Long idBovino, Date data, Double indiceBCS) {
		super();
		this.idBovino = idBovino;
		this.data = data;
		IndiceBCS = indiceBCS;
	}

	public Long getIdBCS() {
		return idBCS;
	}

	public void setIdBCS(Long idBCS) {
		this.idBCS = idBCS;
	}

	public Long getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Long idBovino) {
		this.idBovino = idBovino;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getIndiceBCS() {
		return IndiceBCS;
	}

	public void setIndiceBCS(Double indiceBCS) {
		IndiceBCS = indiceBCS;
	}
	
	
	
}
