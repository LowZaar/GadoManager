package com.gadomanager.gadomanager.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gadomanager.gadomanager.classes.Vacina;

@Repository
public interface VacinaRepository extends CrudRepository<Vacina, String>{
	
//	//selects
//	
//	Optional<Vacina> findById(int id);
//	
//	//@Query("SELECT v FROM vacinas v WHERE v.descricao like ?1 and v.nome =?2")
//	Optional<Vacina> findBydescricao(String desc, String nome);
//
//	//update
//	
//	@Query("update vacina v set v.descricao=?2 where v.id=?1")
//	void updateDescricaoById(int id, String desc);
//	
//	//delete
//	
//	void deleteById(int id);
}
