package classes;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.Nullable;

@Entity
public class Bovinos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBovino;
	
	@Column(nullable = true)
	private Long idAssociacao;
	
	@Column(nullable = true)
	private Long idBrinco;
	
	private String nome;
	
	@ManyToOne
	private Rebanhos idRebanho;
	
	@ManyToOne
	private Racas idRaca;
	
	private String categoria;
	
	private char sexo;
	
	private Date dataNascimento;
	
	private Double pesoNascimento;
	
	@Column(nullable = true)
	private Date dataMorte;
	
	@ManyToOne
	private Bovinos idBovino_mae;
	
	@ManyToOne
	private Bovinos idBovino_pai;
	

	public Bovinos() {
	
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


	public Rebanhos getIdRebanho() {
		return idRebanho;
	}


	public void setIdRebanho(Rebanhos idRebanho) {
		this.idRebanho = idRebanho;
	}


	public Racas getIdRaca() {
		return idRaca;
	}


	public void setIdRaca(Racas idRaca) {
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


	public Bovinos getIdBovino_mae() {
		return idBovino_mae;
	}


	public void setIdBovino_mae(Bovinos idBovino_mae) {
		this.idBovino_mae = idBovino_mae;
	}


	public Bovinos getIdBovino_pai() {
		return idBovino_pai;
	}


	public void setIdBovino_pai(Bovinos idBovino_pai) {
		this.idBovino_pai = idBovino_pai;
	}
	
	
}


