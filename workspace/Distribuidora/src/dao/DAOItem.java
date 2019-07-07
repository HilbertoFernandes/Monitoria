package dao;

import modelo.Item;
import javax.persistence.Query;
import javax.persistence.NoResultException;

public class DAOItem extends DAO<Item> {
	public DAOItem() {
		super();
	}

	@Override
	public Item read(Object chave) {
		try {
			String id = (String) chave;
			Query q = manager.createQuery("select i from Item i where i.id='" + id + "'");
			return (Item) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
}