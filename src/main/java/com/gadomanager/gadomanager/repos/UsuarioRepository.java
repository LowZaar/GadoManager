package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Usuarios;

public interface UsuarioRepository extends CrudRepository<Usuarios, Long>{

	Usuarios findByNomeAndSenha(String nome, String senha);
	
	List<Usuarios> findByIdEmpresasPessoa(Empresas_Pessoas empresa);
	
	
}
