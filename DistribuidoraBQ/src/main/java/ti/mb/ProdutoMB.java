package ti.mb;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import ti.dao.DAO;
import ti.modelo.Produto;

@Named
@SessionScoped
public class ProdutoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Produto produto = new Produto();
//	private List<Produto> produtos;

	public void grava() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		System.out.println(produto);
		if (produto.getId() == null)
			dao.adiciona(produto);
		else
			dao.altera(produto);
		this.produto = new Produto();
	}
	
	public List<Produto> getProdutos(){
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	
	public void remove(Produto p) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.remove(p);
	}
	
	public void cancela() {
		this.produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
}
