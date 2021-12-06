package com.gadomanager.gadomanager.repos.eventos;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.eventos.EventosSaudeMedicacao;

public interface EventoMedicamentoRepository extends CrudRepository<EventosSaudeMedicacao, Long>{

}
