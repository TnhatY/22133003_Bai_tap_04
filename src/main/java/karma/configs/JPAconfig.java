package karma.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAconfig {
	public static EntityManager getEntityManager() {

		 EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

		 return factory.createEntityManager();

		 }
}
