package com.gadomanager.gadomanager.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rebanhos")
public class Rebanhos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRebanho")
	private Long idRebanho;
	
	private String nome;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa_Pessoa")
	private Empresas_Pessoas idEmpresaPessoa;
	
	public Rebanhos() {
		
	}

	
	public Rebanhos(String nome, String descricao, Empresas_Pessoas idEmpresaPessoa) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.idEmpresaPessoa = idEmpresaPessoa;
	}



	public Long getIdRebanho() {
		return idRebanho;
	}

	public void setIdRebanho(Long idRebanho) {
		this.idRebanho = idRebanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresas_Pessoas getIdEmpresaPessoa() {
		return idEmpresaPessoa;
	}

	public void setIdEmpresaPessoa(Empresas_Pessoas idEmpresaPessoa) {
		this.idEmpresaPessoa = idEmpresaPessoa;
	}
	
	
	
}
