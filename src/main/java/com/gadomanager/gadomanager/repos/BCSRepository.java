package com.gadomanager.gadomanager.repos;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.BCS;
import com.gadomanager.gadomanager.classes.Bovinos;

public interface BCSRepository extends CrudRepository<BCS, Long>{

	
	public BCS findByidBovino(Bovinos bovino);
	
}
