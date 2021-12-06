package com.gadomanager.gadomanager.repos;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Pesagens;

public interface PesagemRepository extends CrudRepository<Pesagens, Long> {
	
	
	@Query("SELECT p from Pesagens p where p.idBovino = ?1 ORDER BY p.dataPesagem DESC")
	public Pesagens findLastDateBovino(Bovinos bovino);
	
}
