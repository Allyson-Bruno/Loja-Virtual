package ti.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ItemNota {
	@Id
	@SequenceGenerator(name = "item_gen", sequenceName = "item_seq", allocationSize = 1)
	@GeneratedValue(generator = "item_gen", strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne
	private NotaFiscal notaFiscal;
	@ManyToOne
	private Produto produto;
	private Integer quantidade;
	private Double valorUnitario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getTotal() {
		if (quantidade != null && valorUnitario != null)
			return quantidade * valorUnitario;
		else
			return null;
	}
}
