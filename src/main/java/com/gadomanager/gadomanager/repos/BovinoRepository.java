package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gadomanager.gadomanager.classes.Bovinos;
import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Rebanhos;

public interface BovinoRepository extends CrudRepository<Bovinos, Long>{

//	@Query("SELECT b from Bovinos b WHERE b.idEmpresaPessoas = ?2 "
//			+ " AND b.nome LIKE %?1%"
//			+ " OR b.categoria LIKE %?1%"
//			+ " OR b.idRaca.nomeRaca like %?1%"
//			+ " OR b.idRebanho.nome LIKE %?1%"
//			+ " OR b.idBovino_mae IS NULL OR b.idBovino_mae.nome LIKE %?1%"
//			+ " OR b.idBovino_pai IS NULL OR b.idBovino_pai.nome LIKE %?1%")

	@Query("SELECT b from Bovinos b WHERE b.idEmpresaPessoas = ?2"
			+ " AND b.nome LIKE %?1%"
			+ " OR b.categoria LIKE %?1%"
			+ " OR b.idRaca.nomeRaca LIKE %?1%"
			+ " OR b.idRebanho.nome LIKE %?1%")
	public List<Bovinos> search(String filter, Empresas_Pessoas empresa);	
	
	@Query("SELECT b from Bovinos b where b.idEmpresaPessoas = :empresa")
	List<Bovinos> findByIdEmpresaPessoas(@Param("empresa") Empresas_Pessoas idEmpresas_Pessoa);
	
	
	List<Bovinos> findByIdRebanho(Rebanhos rebanho);
	
	Bovinos findByIdEmpresaPessoasAndNome(Empresas_Pessoas empresa , String nome);
	
}
