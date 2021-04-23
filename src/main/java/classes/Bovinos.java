package classes;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bovinos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(mappedBy = "idBovino")
	private Long idBovino;
	
	@Column(name = "Nome")
	private String nome;
	
	@ManyToOne(targetEntity = Rebanhos.class)
	private Long idRebanho;
	
	private Long idRaca;
	
	private String categoria;
	
	private char sexo;
	
	private Date dataNascimento;
	
	private Double pesoNascimento;
	
	private Date dataMorte;
	
	@ManyToOne(optional = true, targetEntity = Bovinos.class)
	private Long idBovino_mae;
	
	@ManyToOne(optional = true, targetEntity = Bovinos.class)
	private Long idBovino_pai;
	

	 public Bovinos() {
		// TODO Auto-generated constructor stub
	}


	public Bovinos(String nome, String categoria, char sexo, Date dataNascimento, Double pesoNascimento, Date dataMorte,
			Long idBovino_mae, Long idBovino_pai) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.pesoNascimento = pesoNascimento;
		this.dataMorte = dataMorte;
		this.idBovino_mae = idBovino_mae;
		this.idBovino_pai = idBovino_pai;
	}


	public Long getIdBovino() {
		return idBovino;
	}


	public void setIdBovino(Long idBovino) {
		this.idBovino = idBovino;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getIdRebanho() {
		return idRebanho;
	}


	public void setIdRebanho(Long idRebanho) {
		this.idRebanho = idRebanho;
	}


	public Long getIdRaca() {
		return idRaca;
	}


	public void setIdRaca(Long idRaca) {
		this.idRaca = idRaca;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Double getPesoNascimento() {
		return pesoNascimento;
	}


	public void setPesoNascimento(Double pesoNascimento) {
		this.pesoNascimento = pesoNascimento;
	}


	public Date getDataMorte() {
		return dataMorte;
	}


	public void setDataMorte(Date dataMorte) {
		this.dataMorte = dataMorte;
	}


	public Long getIdBovino_mae() {
		return idBovino_mae;
	}


	public void setIdBovino_mae(Long idBovino_mae) {
		this.idBovino_mae = idBovino_mae;
	}


	public Long getIdBovino_pai() {
		return idBovino_pai;
	}


	public void setIdBovino_pai(Long idBovino_pai) {
		this.idBovino_pai = idBovino_pai;
	}
	 
	
}


