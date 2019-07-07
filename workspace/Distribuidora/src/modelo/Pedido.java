package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PEDIDO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Pessoa pessoa;

	@Column(name = "ITENS")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	private List<Item> itens;

	public Pedido() {
		super();
	}

	public Pedido(Pessoa pessoa, List<Item> itens) {
		super();
		this.pessoa = pessoa;
		this.itens = itens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void addItem(Item item) {
		this.itens.add(item);
		item.setPedido(this);
	}

}
