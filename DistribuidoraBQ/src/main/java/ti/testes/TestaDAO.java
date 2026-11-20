package ti.testes;

import ti.dao.DAO;
import ti.modelo.Produto;

public class TestaDAO {
	public static void main(String[] args) {
		Produto produto = new Produto();
		produto.setNome("Biscoito");
		produto.setDescricao("Biscoito Integral");
		produto.setPreco(8.0);
		
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.adiciona(produto);
	}
}
