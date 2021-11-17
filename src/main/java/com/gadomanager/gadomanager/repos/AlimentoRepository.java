package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gadomanager.gadomanager.classes.Alimentos;

@Repository
public interface AlimentoRepository extends CrudRepository<Alimentos, Long> {
	
	
}
