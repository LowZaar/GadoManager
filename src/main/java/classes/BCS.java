package classes;

import java.sql.Date;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
>>>>>>> 6c3da58715db03a6b8b637a1fa58551b5a90a6c2
public class BCS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBCS;
	
	@OneToOne
	private Bovinos idBovino;
	
	private Date data;
	
	private Double IndiceBCS;
	
	public BCS() {
	
	}

	public Long getIdBCS() {
		return idBCS;
	}

	public void setIdBCS(Long idBCS) {
		this.idBCS = idBCS;
	}

	public Bovinos getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(Bovinos idBovino) {
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
