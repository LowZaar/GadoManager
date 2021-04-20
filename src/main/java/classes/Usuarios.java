package classes;

public class Usuarios {
	
	private Long idUsuario;
	
	private String nome;
	
	private String email;
	
	private String usuario;
	
	private String senha;
	
	private boolean usuarioMestre;
	
	private Long idEmpresas_Pessoa;
	
	public Usuarios() {
	
	}

	public Usuarios(String nome, String email, String usuario, String senha, boolean usuarioMestre,
			Long idEmpresas_Pessoa) {
		super();
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.usuarioMestre = usuarioMestre;
		this.idEmpresas_Pessoa = idEmpresas_Pessoa;
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

	public Long getIdEmpresas_Pessoa() {
		return idEmpresas_Pessoa;
	}

	public void setIdEmpresas_Pessoa(Long idEmpresas_Pessoa) {
		this.idEmpresas_Pessoa = idEmpresas_Pessoa;
	}
	
	
	
}
