package ti.mb;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import ti.dao.DAO;
import ti.modelo.ItemNota;
import ti.modelo.NotaFiscal;
import ti.modelo.Produto;

@Named
@SessionScoped
public class NotaFiscalMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NotaFiscal notaFiscal = new NotaFiscal();
	private ItemNota itemNota = new ItemNota();
	private Long idProduto;
	
	public void grava() {
		DAO<NotaFiscal> daoNota = new DAO<NotaFiscal>(NotaFiscal.class);
		daoNota.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
	}
	
	public void guardaItem() {
		DAO<Produto> daoProduto = new DAO<Produto>(Produto.class);
		Produto produto = daoProduto.buscaPorId(idProduto);
		this.itemNota.setProduto(produto);
		this.itemNota.setValorUnitario(produto.getPreco());
		this.itemNota.setNotaFiscal(notaFiscal);
		this.notaFiscal.getItens().add(itemNota);
		this.itemNota = new ItemNota();
	}
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public ItemNota getItemNota() {
		return itemNota;
	}
	public void setItemNota(ItemNota itemNota) {
		this.itemNota = itemNota;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}		
}
