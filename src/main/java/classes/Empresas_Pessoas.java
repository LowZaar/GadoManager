package classes;

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

	//Pessoa Fisica
	
	public Empresas_Pessoas createPF(String nome,String cpf, String rg, Date dataNascimento,
			String endereco, Cidades idCidade, Long idEstado, String cep, String telefone, String email){
		
		Empresas_Pessoas empPF = new Empresas_Pessoas(nome, "PF", cpf, rg, dataNascimento,
				endereco, idCidade, idEstado, cep, telefone, email);
		
		return empPF;
	}
	
	
	private Empresas_Pessoas(String nome, String tipoPessoa, String cpf, String rg, Date dataNascimento,
			String endereco, Cidades idCidade, Long idEstado, String cep, String telefone, String email) {
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.idCidade = idCidade;
		this.idEstado = idEstado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
	}
	
	// Pessoa Juridica;
	
	public Empresas_Pessoas createPJ(String nome, Date dataNascimento, String cnpj, String ie, String im,
			String endereco, Cidades idCidade, Long idEstado, String cep, String telefone, String email) {
	
		Empresas_Pessoas empPJ = new Empresas_Pessoas(nome, "PJ", dataNascimento, cnpj, 
				ie, im, endereco, idCidade, idEstado, cep, telefone, email);
		

		
		return empPJ;
	}
	
	
	

	@Override
	public String toString() {
		return "Empresas_Pessoas [nome=" + nome + ", tipoPessoa=" + tipoPessoa + ", cpf=" + cpf + ", rg=" + rg
				+ ", dataNascimento=" + dataNascimento + ", cnpj=" + cnpj + ", ie=" + ie + ", im=" + im + ", endereco="
				+ endereco + ", idCidade=" + idCidade + ", idEstado=" + idEstado + ", cep=" + cep + ", telefone="
				+ telefone + ", email=" + email + "]";
	}

	private Empresas_Pessoas(String nome, String tipoPessoa, Date dataNascimento, String cnpj, String ie, String im,
			String endereco, Cidades idCidade, Long idEstado, String cep, String telefone, String email) {
		super();
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.ie = ie;
		this.im = im;
		this.endereco = endereco;
		this.idCidade = idCidade;
		this.idEstado = idEstado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
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
