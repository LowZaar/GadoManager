package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Racoes;

public interface RacoesRepository extends CrudRepository<Racoes, String>{
	
	@Query("SELECT r from Racoes r where r.descricao like %?1%"
			+ "or r.Observacao like %?1%")
    public List<Racoes> search(String filter);
	
}
