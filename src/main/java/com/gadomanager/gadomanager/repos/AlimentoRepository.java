package com.gadomanager.gadomanager.repos;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gadomanager.gadomanager.classes.Alimentos;

@Repository
public interface AlimentoRepository extends CrudRepository<Alimentos, Long> {
	
	
	@Query("SELECT a FROM Alimentos a WHERE a.idRebanho.nome LIKE %?1%"
            + "OR a.idracao.descricao LIKE %?1%"
			+ "OR a.observacoes LIKE %?1%")
    public List<Alimentos> search(String filter);	
	
}
