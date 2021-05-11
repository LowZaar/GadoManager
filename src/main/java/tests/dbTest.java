package tests;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import classes.Bovinos;
import classes.Cidades;
import classes.Empresas_Pessoas;
import classes.Estados;
import classes.Racas;
import classes.Rebanhos;

public class dbTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GadoManager");
		EntityManager em = emf.createEntityManager();
		
		
		String jpql = "select e from Estados e where e.Sigla = 'RS'";
		
		TypedQuery<Estados> query = em.createQuery(jpql, Estados.class);
		List<Estados> Estado = query.getResultList();
		
		Cidades cidade = new Cidades("Erechim", Estado.get(0));
		
//		Empresas_Pessoas emp1 = new Empresas_Pessoas("Nome", "PF", "cpf1", "RG1", new Date(),
//				"cnpj", "ie", "im", "Endereço 1", cidade, "CEPCEPCEP", "123495123", "email@user.com");
		
		Empresas_Pessoas emp1 = new Empresas_Pessoas();
		
		emp1.createPJ("Nome", new Date(), "cnpj", "ie", "im", "Endereço 1", cidade, cidade.getEstado().getIdEstado(), 
				"CEPCEPCEP", "123495123", "email@user.com");
		
		Rebanhos reb1 = new Rebanhos("Rebanho 1 ", "Rebanho teste", emp1);
		
		Racas raca1 = new Racas("Raça1Test");
		em.persist(raca1);
		
		Bovinos bov1 = new Bovinos(1L, 1L, "Vaca1", reb1,
				raca1,"Categoria teste",'M', new Date(), 200.00, emp1);
		
		em.getTransaction().begin();
		em.persist(cidade);
		em.persist(emp1);
		em.persist(reb1);
		em.persist(raca1);
		em.persist(bov1);
		
		em.getTransaction().commit();
		em.close();
	}

}
