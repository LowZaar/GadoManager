package classes;

import javax.persistence.OneToMany;

public class Veterinario {
	
	@OneToMany (mappedBy = "idVeterinario")
	private Long idVeterinario;
	
	private String nome;
	
	private String crmv;
	
	private String cpf;
	
	private String rg;
	
	public Veterinario() {
		// TODO Auto-generated constructor stub
	}

	public Veterinario(String nome, String crmv, String cpf, String rg) {
		super();
		this.nome = nome;
		this.crmv = crmv;
		this.cpf = cpf;
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
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
	
	
}
