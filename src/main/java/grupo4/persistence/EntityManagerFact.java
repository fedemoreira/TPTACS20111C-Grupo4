package grupo4.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase usada para usar singleton de EntityManager, para la persistencia por JPA
 */
public final class EntityManagerFact {
	private static final EntityManagerFactory emfInstance =	Persistence.createEntityManagerFactory("transactions-optional");

	private EntityManagerFact() {
		/* Constructor por defecto */
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
