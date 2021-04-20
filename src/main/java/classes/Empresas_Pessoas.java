package classes;

import java.util.Date;

public class Empresas_Pessoas {
	
	private Long idEmpresa_Pessoa;
	
	private String nome;
	
	private String tipoPessoa;
	
	private String cpf;
	
	private String rg;
	
	private Date dataNascimento;
	
	private String cnpj;
	
	private String ie;
	
	private String im;
	
	private String endereco;
	
	private Long idCidade;
	
	private Long idEstado;
	
	private String cep;
	
	private String telefone;
		
	private String email;
	
	
	public Empresas_Pessoas() {
	
	}


	public Empresas_Pessoas(String nome, String tipoPessoa, String cpf, String rg, Date dataNascimento, String cnpj,
			String ie, String im, String endereco, Long idCidade, Long idEstado, String cep, String telefone,
			String email) {
		super();
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.rg = rg;
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


	public Long getIdCidade() {
		return idCidade;
	}


	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}


	public Long getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
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
