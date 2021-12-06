package com.gadomanager.gadomanager.repos.estadosCidades;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Cidades;
import com.gadomanager.gadomanager.classes.Estados;

public interface CidadesRepository extends CrudRepository<Cidades, Long> {

	public Cidades findByNome(String nome);
	
	public List<Cidades> findByEstado(Estados estado);
	
}
