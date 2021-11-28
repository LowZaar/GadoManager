package com.gadomanager.gadomanager.classes;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Bovinos")
public class Bovinos {
	
	public Long getIdAssociacao() {
		return idAssociacao;
	}

	public void setIdAssociacao(Long idAssociacao) {
		this.idAssociacao = idAssociacao;
	}

	public Long getIdBrinco() {
		return idBrinco;
	}

	public void setIdBrinco(Long idBrinco) {
		this.idBrinco = idBrinco;
	}

	public Empresas_Pessoas getIdEmpresaPessoas() {
		return idEmpresaPessoas;
	}

	public void setIdEmpresaPessoas(Empresas_Pessoas idEmpresaPessoas) {
		this.idEmpresaPessoas = idEmpresaPessoas;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBovino;
	
	@Column(nullable = true)
	private Long idAssociacao;
	
	@Column(nullable = true)
	private Long idBrinco;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "idRebanho", nullable = true)
	private Rebanhos idRebanho;
	
	@ManyToOne
	@JoinColumn(name = "idRaca")
	private Racas idRaca;
	
	private String categoria;
	
	private char sexo;
	
	private Date dataNascimento;
	
	private Double pesoNascimento;
	
	@Column(nullable = true)
	private Date dataMorte;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa_Pessoa")
	private Empresas_Pessoas idEmpresaPessoas;
	
	@ManyToOne
	@JoinColumn(name = "idBovino_mae")
	private Bovinos idBovino_mae;
	
	@ManyToOne
	@JoinColumn(name = "idBovino_pai")
	private Bovinos idBovino_pai;
	

	public Bovinos() {
	
	}
	
	public Bovinos(Long idAssociacao, Long idBrinco, String nome, Rebanhos idRebanho, Racas idRaca, String categoria,
			char sexo, Date dataNascimento, Double pesoNascimento, Date dataMorte, Empresas_Pessoas idEmpresaPessoas,
			Bovinos idBovino_mae, Bovinos idBovino_pai) {
		this.idAssociacao = idAssociacao;
		this.idBrinco = idBrinco;
		this.nome = nome;
		this.idRebanho = idRebanho;
		this.idRaca = idRaca;
		this.categoria = categoria;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.pesoNascimento = pesoNascimento;
		this.dataMorte = dataMorte;
		this.idEmpresaPessoas = idEmpresaPessoas;
		this.idBovino_mae = idBovino_mae;
		this.idBovino_pai = idBovino_pai;
	}


	public Bovinos(Long idAssociacao, Long idBrinco, String nome, Rebanhos idRebanho, Racas idRaca, String categoria,
			char sexo, Date dataNascimento, Double pesoNascimento, Empresas_Pessoas idEmpresaPessoas) {
		super();
		this.idAssociacao = idAssociacao;
		this.idBrinco = idBrinco;
		this.nome = nome;
		this.idRebanho = idRebanho;
		this.idRaca = idRaca;
		this.categoria = categoria;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.pesoNascimento = pesoNascimento;
		this.idEmpresaPessoas = idEmpresaPessoas;
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


