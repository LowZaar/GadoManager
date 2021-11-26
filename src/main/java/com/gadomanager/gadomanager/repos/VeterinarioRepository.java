package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Racas;
import com.gadomanager.gadomanager.classes.Veterinario;

public interface VeterinarioRepository extends CrudRepository<Veterinario, String>{
	
	@Query("SELECT v from Veterinario v where v.nome like %?1%")
    public List<Veterinario> search(String filter);
	
//	//selects
//			
//	Optional<Veterinario> findById(long id);
//	
//	@Query("SELECT v FROM veterinarios v WHERE v.cpf=?1" )
//	Optional<Veterinario> findByCPF(int cpf);
//	
//	@Query("SELECT v FROM veterinarios v WHERE v.crmv=?1" )
//	Optional<Veterinario> findByCRMV(int crmv);
//	
//	@Query("SELECT v FROM veterinarios v WHERE v.rg=?1" )
//	Optional<Veterinario> findByRG(int rg);
//	
//	@Query("SELECT v FROM veterinarios v WHERE v.nome=?1" )
//	Optional<Veterinario> findByNome(String nome);
//	
//	//updates
//	
//	@Query("UPDATE veterinarios v set v.nome=?1 WHERE v.idVeterinario=?2")
//	void UpdateNome(String nome, int id);
//	
//	@Query("UPDATE veterinarios v set v.cpf=?1 WHERE v.idVeterinario=?2")
//	void UpdateCPF(int cpf, int id);
//	
//	@Query("UPDATE veterinarios v set v.crmv=?1 WHERE v.idVeterinario=?2")
//	void UpdateCRMV(int crmv, int id);
//	
//	@Query("UPDATE veterinarios v set v.rg=?1 WHERE v.idVeterinario=?2")
//	void UpdateRG(int rg, int id);
//	
//	//delete
//	
//	void deleteById(int id);
//	
}
