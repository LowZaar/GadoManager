package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Rebanhos;

public interface RebanhoRepository extends CrudRepository<Rebanhos, Long>{

	
	List<Rebanhos> findByIdEmpresaPessoa(Empresas_Pessoas empresa);
	
	Rebanhos findByNomeAndIdEmpresaPessoa(String nome, Empresas_Pessoas empresa);
	
}
