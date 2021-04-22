package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import classes.Bovinos;

public class MainClass {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GadoManager");
		EntityManager em = emf.createEntityManager();
		
		em.find(Bovinos.class, 1L);
		
	}

}
