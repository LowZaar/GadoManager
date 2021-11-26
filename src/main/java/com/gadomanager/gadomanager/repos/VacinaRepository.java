package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Vacina;

public interface VacinaRepository extends CrudRepository<Vacina, String>{
	
	@Query("SELECT v from Vacina v where v.descricao like %?1%")
    public List<Vacina> search(String filter);
	
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
