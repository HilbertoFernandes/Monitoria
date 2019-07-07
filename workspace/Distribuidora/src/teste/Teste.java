package teste;

import dao.DAO;
import dao.DAOItem;
import dao.DAOPedido;
import dao.DAOPessoa;
import dao.DAOProduto;
import modelo.Item;
import modelo.Pedido;
import modelo.Pessoa;
import modelo.Produto;

public class Teste {

	private static DAOPessoa daopessoa = new DAOPessoa();
	private static DAOProduto daoproduto = new DAOProduto();
	private static DAOPedido daopedido = new DAOPedido();
	private static DAOItem daoitem = new DAOItem();

	public static void main(String[] args) {
		System.out.println("Teste...");
		DAO.open();
		DAO.begin();

		//CADASTRAR PESSOA
		Pessoa pessoa1 = new Pessoa("Bill Gates", "1234-5678", "Av das Riquesas", "inteligente@gmail.com");
		Pessoa pessoa2 = new Pessoa("Steve Jobs", "1123-5813", "Rua Cidade dos pés juntos", "criativo@gmail.com");
		Pessoa pessoa3 = new Pessoa("Fausto Ayres", "9999-9999", "IFPB", "professor@gmail.com");
		daopessoa.create(pessoa1);
		daopessoa.create(pessoa2);
		daopessoa.create(pessoa3);

		//CADASTRAR PRODUTOS
		Produto produto1 = new Produto("Coca-cola", 12, 1.99);
		Produto produto2 = new Produto("Coxinha", 25, 1.99);
		Produto produto3 = new Produto("Pão Pizza", 20, 2.50);
		daoproduto.create(produto1);
		daoproduto.create(produto2);
		daoproduto.create(produto3);

		//CADASTRAR PEDIDO
		Pedido pedido1 = new Pedido();
		pedido1.setPessoa(pessoa1);
		Pedido pedido2 = new Pedido();
		pedido2.setPessoa(pessoa2);
		Pedido pedido3 = new Pedido();
		pedido3.setPessoa(pessoa3);
		daopedido.create(pedido1);
		daopedido.create(pedido2);
		daopedido.create(pedido3);

		//CADASTRAR ITEM
		Item item1 = new Item(pedido1, produto1, 4);
		Item item2 = new Item(pedido2, produto2, 5);
		Item item3 = new Item(pedido3, produto3, 6);
		daoitem.create(item1);
		daoitem.create(item2);
		daoitem.create(item3);
		DAO.commit();

		// ADICIONAR ITEM A PEDIDO
		DAO.open();
		DAO.begin();
		Item a = daoitem.read(Integer.toString(1));
	    Pedido p1 = daopedido.read(Integer.toString(1));
	    p1.addItem(a);
	    daopedido.update(p1);
	    
	    Item b = daoitem.read(Integer.toString(2));
	    Pedido p2 = daopedido.read(Integer.toString(2));
	    p2.addItem(b);
	    daopedido.update(p2);
	    
	    Item c = daoitem.read(Integer.toString(3));
	    Pedido p3 = daopedido.read(Integer.toString(3));
	    p3.addItem(c);
	    daopedido.update(p3);
		DAO.commit();
	}
}
