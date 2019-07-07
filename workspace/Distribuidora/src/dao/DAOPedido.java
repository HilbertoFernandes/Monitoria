package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Item;
import modelo.Pedido;

public class DAOPedido extends DAO<Pedido> {
	public DAOPedido() {
		super();
	}

	@Override
	public Pedido read(Object chave) {
		try {
			String id = (String) chave;
			Query q = manager.createQuery("select p from Pedido p where p.id='" + id + "'");
			return (Pedido) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}