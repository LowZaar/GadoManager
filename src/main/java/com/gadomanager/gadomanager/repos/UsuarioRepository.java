package com.gadomanager.gadomanager.repos;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gadomanager.gadomanager.classes.Empresas_Pessoas;
import com.gadomanager.gadomanager.classes.Usuarios;

public interface UsuarioRepository extends CrudRepository<Usuarios, Long>{

	Usuarios findByUsuarioAndSenha(String usuario, String senha);
	
	@Query("SELECT u from Usuarios u where u.idEmpresasPessoa = :empresa")
	List<Usuarios> findByIdEmpresasPessoa(@Param("empresa") Empresas_Pessoas idEmpresas_Pessoa);
	
	
}
