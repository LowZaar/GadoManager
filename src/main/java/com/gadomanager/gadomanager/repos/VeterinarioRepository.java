package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Veterinario;

public interface VeterinarioRepository extends CrudRepository<Veterinario, Long> {

	@Query("SELECT v from Veterinario v where v.nome like %?1%" + "OR v.idVeterinario LIKE %?1%" + "OR v.crmv LIKE %?1%"
			+ "OR v.cpf LIKE %?1%" + "OR v.rg LIKE %?1%")
	public List<Veterinario> search(String filter);

	
	public Veterinario findByNome(String nome);
}
