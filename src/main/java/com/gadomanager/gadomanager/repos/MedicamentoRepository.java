package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Medicamentos;

public interface MedicamentoRepository extends CrudRepository<Medicamentos, String>{
	

	@Query("SELECT m FROM Medicamentos m WHERE m.nome LIKE %?1%"
            + "OR m.principioAtivo LIKE %?1%"
			+ "OR m.idMedicamento LIKE %?1%")
    public List<Medicamentos> search(String filter);

}
	