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
@Table(name = "Empresas_Pessoas")
public class Empresas_Pessoas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpresa_Pessoa;
	
	private String nome;
	
	private String tipoPessoa;
	
	@Column(nullable = true)
	private String cpf;
	
	@Column(nullable = true)
	private String rg;
	
	private Date dataNascimento;
	
	@Column(nullable = true)
	private String cnpj;
	
	@Column(nullable = true)
	private String ie;
	
	@Column(nullable = true)
	private String im;
	
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name = "idCidade")
	private Cidades idCidade;

	private Long idEstado;
	
	private String cep;
	
	private String telefone;
		
	private String email;
	
	public Empresas_Pessoas() {
		
	}


	public Long getIdEmpresa_Pessoa() {
		return idEmpresa_Pessoa;
	}


	public void setIdEmpresa_Pessoa(Long idEmpresa_Pessoa) {
		this.idEmpresa_Pessoa = idEmpresa_Pessoa;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipoPessoa() {
		return tipoPessoa;
	}


	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getIe() {
		return ie;
	}


	public void setIe(String ie) {
		this.ie = ie;
	}


	public String getIm() {
		return im;
	}


	public void setIm(String im) {
		this.im = im;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Cidades getIdCidade() {
		return idCidade;
	}


	public void setIdCidade(Cidades idCidade) {
		this.idCidade = idCidade;
	}


	public Long getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(Cidades idEstado) {
		this.idEstado = idEstado.getEstado().getIdEstado();
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
}
