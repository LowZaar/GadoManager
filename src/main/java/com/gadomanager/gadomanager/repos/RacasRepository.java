package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Racas;


public interface RacasRepository extends CrudRepository<Racas, Long> {
	
	
	@Query("SELECT r from Racas r where r.nomeRaca like %?1%")
    public List<Racas> search(String filter);
}
