package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;

	public DAO() {
	}

	public static void open() {
		if (manager == null) {
			factory = Persistence.createEntityManagerFactory("distribuidora");
			manager = factory.createEntityManager();
		}
	}

	public static void close() {
		if (manager != null) {
			manager.close();
			factory.close();
		}
	}

	public void create(T obj) {
		manager.persist(obj);
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		return manager.merge(obj);
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Query query = manager.createQuery("select x from " + type.getSimpleName() + " x");
		return (List<T>) query.getResultList();
	}

	// ----------------------- TRANSA��O ----------------------
	public static void begin() {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}

	public static void commit() {
		if (manager.getTransaction().isActive()) {
			manager.getTransaction().commit();
			manager.clear(); // ---- esvaziar o cache de objetos
		}
	}

	public static void rollback() {
		if (manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}

}
