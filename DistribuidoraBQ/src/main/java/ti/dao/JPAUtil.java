package ti.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lojavirtual");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
