package com.gadomanager.gadomanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAODatabase {

	private Connection conexao;
	
	private Connection getConexao() {
		try {
			if(conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {
			
		}
		conexao = DAOFactory.getConexao();
		return conexao;
	}
	
	public void close() {
		try {
			getConexao().close();
		} catch (SQLException e) {
			
		} finally {
			conexao = null;
		}
	}
	
	private void adicionarAtributos(PreparedStatement stmt, Object[] atributos) throws SQLException {
		int indice = 1;
		for(Object atributo: atributos) {
			if(atributo instanceof String) {
				stmt.setString(indice, (String) atributo);				
			} else if(atributo instanceof Integer) {
				stmt.setInt(indice, (Integer) atributo);
			}
			else if(atributo instanceof Long) {
				stmt.setLong(indice, (long) atributo);
			}

			indice++;
		}
	}
	
	public int insert(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			adicionarAtributos(stmt, atributos);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet resultado = stmt.getGeneratedKeys();
				if(resultado.next()) {
					return resultado.getInt(1);
				}
			}
			return -1;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int update(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			adicionarAtributos(stmt, atributos);
			return stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int delete(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
   			adicionarAtributos(stmt, atributos);
			return stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ResultSet select(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			ResultSet query = stmt.executeQuery();
			adicionarAtributos(stmt, atributos);
			return query;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ResultSet selectLazy(String sql) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			ResultSet query = stmt.executeQuery();
			return query;
		} catch(SQLException e ) {
			throw new RuntimeException(e);
		}
	}
	
}
