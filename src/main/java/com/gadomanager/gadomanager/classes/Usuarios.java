package com.gadomanager.gadomanager.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	private String nome;
	
	private String email;
	
	private String usuario;
	
	private String senha;
	
	private boolean usuarioMestre;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresas_Pessoa")
	private Empresas_Pessoas idEmpresasPessoa;
	
	public Usuarios() {
		
	}

	
	public Usuarios(String nome, String email, String usuario, String senha, boolean usuarioMestre,
			Empresas_Pessoas idEmpresas_Pessoa) {
		super();
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.usuarioMestre = usuarioMestre;
		this.idEmpresasPessoa = idEmpresas_Pessoa;
	}




	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isUsuarioMestre() {
		return usuarioMestre;
	}

	public void setUsuarioMestre(boolean usuarioMestre) {
		this.usuarioMestre = usuarioMestre;
	}

	public Empresas_Pessoas getIdEmpresas_Pessoa() {
		return idEmpresasPessoa;
	}

	public void setIdEmpresas_Pessoa(Empresas_Pessoas idEmpresas_Pessoa) {
		this.idEmpresasPessoa = idEmpresas_Pessoa;
	}
	
	
	
}
