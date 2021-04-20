package classes;

public class Estados {
	
	private Long idEstado;
	
	private String Sigla;
	
	public Estados() {
	
	}

	public Estados(String sigla) {
		super();
		Sigla = sigla;
	}

	public String getSigla() {
		return Sigla;
	}

	public void setSigla(String sigla) {
		Sigla = sigla;
	}

	
	
}
