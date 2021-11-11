package com.gadomanager.gadomanager.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



public class DAOHibernate <E> {

	
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe; 
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("GadoManager");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public DAOHibernate() {
		this(null);
	}

	public DAOHibernate(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAOHibernate<E> beginTransaction() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAOHibernate<E> commitTransaction() {
		em.getTransaction().commit();
		return this;
	} 
	
	public DAOHibernate<E> rollbackTransaction() {
		em.getTransaction().rollback();
		return this;
	} 
	
	public DAOHibernate<E> save(E entity) {
		em.persist(entity);
		return this;
	}

	public DAOHibernate<E> update(E entity) {
		em.merge(entity);
		return this;
	}

//	public DAOHibernate<E> saveAtom(E entity) {
//		return this.beginTransaction().save(entity).closeTransaction();
//	}
	
	public DAOHibernate<E> delete(E entity) {
		em.remove(entity);
		return this;
	}

	
	public E getAllById(Object id) {
		return em.find(classe, id);
	}
	
	public List<E> getAll() {
		return this.getAll(0, 0);
	}
	
	public List<E> getAll(int limit, int offset) {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "SELECT e FROM " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		if (limit > 0) {
			query.setMaxResults(limit);
		}
		if (offset > 0) {
			query.setFirstResult(offset);
		}
		return query.getResultList();
	}
	
	public List<E> getAllByNamedQuery(String queryName, Object... params) {
		TypedQuery<E> query = em.createNamedQuery(queryName, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		return query.getResultList();
	}
	
	
	public E getFirst(String queryName, Object... params) {
		List<E> lista = getAllByNamedQuery(queryName, params);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	public void closeAll() {
		em.close();
	}

}
