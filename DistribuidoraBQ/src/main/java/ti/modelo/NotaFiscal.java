package ti.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class NotaFiscal {
	@Id
	@SequenceGenerator(name = "nf_gen", sequenceName = "nf_seq", allocationSize = 1)
	@GeneratedValue(generator = "nf_gen", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String cnpj;
	private LocalDate data = LocalDate.now();
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "notaFiscal")
	private List<ItemNota> itens = new ArrayList<ItemNota>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public List<ItemNota> getItens() {
		return itens;
	}
	public void setItens(List<ItemNota> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return String.format("NotaFiscal [id=%s, cnpj=%s, data=%s, itens=%s]", id, cnpj, data, itens);
	}		
}
