package com.gadomanager.gadomanager.repos;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Parametros;

public interface ParametrosRepository extends CrudRepository<Parametros, Long>{

	
	public Parametros findByIdEmpresaPessoa(Empresas_Pessoas empresa);
	
}
