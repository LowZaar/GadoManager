package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
	@JoinColumn(name = "idEmpresa_Pessoa")
	private Empresas_Pessoas idEmpresas_Pessoa;
	
	public Usuarios() {
		
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
		return idEmpresas_Pessoa;
	}

	public void setIdEmpresas_Pessoa(Empresas_Pessoas idEmpresas_Pessoa) {
		this.idEmpresas_Pessoa = idEmpresas_Pessoa;
	}
	
	
	
}
