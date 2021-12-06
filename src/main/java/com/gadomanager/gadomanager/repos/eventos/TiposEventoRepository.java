package com.gadomanager.gadomanager.repos.eventos;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.eventos.TiposEvento;

public interface TiposEventoRepository extends CrudRepository<TiposEvento, Long> {
	
	public TiposEvento findByTag(char tag);
}
