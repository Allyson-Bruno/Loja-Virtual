package ti.mb;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import ti.dao.DAO;
import ti.dao.NotaFiscalDAO;
import ti.modelo.ItemNota;
import ti.modelo.NotaFiscal;
import ti.modelo.Produto;

@Named
@SessionScoped
public class NotaFiscalMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private NotaFiscal notaFiscal = new NotaFiscal();
	private ItemNota itemNota = new ItemNota();
	private Long idProduto;
	
	public void grava() {

	    // Validação do CNPJ.
	    String cnpj = notaFiscal.getCnpj();

	    if (cnpj == null || cnpj.isBlank()) {
	        mensagemErro("Informe o CNPJ do cliente.");
	        return;
	    }

	    // Verifica se o CNPJ possui 14 dígitos.
	    String numeros = cnpj.replaceAll("\\D", "");

	    if (numeros.length() != 14) {
	        mensagemErro("O CNPJ deve possuir 14 dígitos.");
	        return;
	    }

	    // Validação da data.
	    if (notaFiscal.getData() == null) {
	        mensagemErro("Informe a data da nota fiscal.");
	        return;
	    }

	    // Verifica se a nota possui itens.
	    if (notaFiscal.getItens() == null || notaFiscal.getItens().isEmpty()) {
	        mensagemErro("Adicione pelo menos um produto à nota fiscal.");
	        return;
	    }

	    // Valida os itens antes da gravação.
	    for (ItemNota item : notaFiscal.getItens()) {

	        if (item == null || item.getProduto() == null 
	        		|| item.getQuantidade() == null
	                || item.getQuantidade() <= 0
	                || item.getValorUnitario() == null
	                || item.getValorUnitario() < 0) {

	            mensagemErro("A nota fiscal possui um item inválido.");
	            return;
	        }
	    }

	    DAO<NotaFiscal> daoNota = new DAO<>(NotaFiscal.class);

	    if (daoNota.adiciona(notaFiscal)) {

	        this.notaFiscal = new NotaFiscal();
	        this.itemNota = new ItemNota();
	        this.idProduto = null;

	        mensagemSucesso("Nota fiscal gravada com sucesso!");

	    } else {
	        mensagemErro("Não foi possível gravar a nota fiscal.");
	    }
	}
	
	public void guardaItem() {
		
		//Verifica se o produto foi selecionado.
		if (idProduto == null) {
			mensagemErro("Selecione um produto.");
			return;
		}
		
		//Verifica se a quantidade é valida.
		if (itemNota.getQuantidade() == null || itemNota.getQuantidade() <= 0) {
			mensagemErro("Informe uma quantidade maior que zero.");
			return;
		}
		
		DAO<Produto> daoProduto = new DAO<Produto>(Produto.class);
		Produto produto = daoProduto.buscaPorId(idProduto);
		
		//Verifica se o produto existe no banco.
		if (produto == null) {
			mensagemErro("Produto não encontrado.");
			return;
		}
		
		//Verifica o preço.
		if (produto.getPreco() == null || produto.getPreco() < 0) {
			mensagemErro("O produto possui um preço inválido");
			return;
		}
		
		//Associa o produto ao item.
		this.itemNota.setProduto(produto);
		this.itemNota.setValorUnitario(produto.getPreco());
		
		//Associa o item à nota fiscal.
		this.itemNota.setNotaFiscal(notaFiscal);
		
		//Adiciona o item à lista.
		this.notaFiscal.getItens().add(itemNota);
		
		//Prepara um novo item.
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
	
	public List<NotaFiscal> getNotasFiscais() {

	    NotaFiscalDAO dao = new NotaFiscalDAO();

	    return dao.listaComItens();
	}
	
	public Double calcularTotal(NotaFiscal nota) {

	    double total = 0;

	    for (ItemNota item : nota.getItens()) {

	        if (item.getTotal() != null) {
	            total += item.getTotal();
	        }
	    }

	    return total;
	}
	
	private void mensagemSucesso(String mensagem) {

	    FacesContext.getCurrentInstance().addMessage(
	        null,
	        new FacesMessage(
	            FacesMessage.SEVERITY_INFO,
	            mensagem,
	            null
	        )
	    );
	}
	
	private void mensagemErro(String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(
				null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
				mensagem,
				null));
	}
}
