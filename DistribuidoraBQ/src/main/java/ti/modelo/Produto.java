package ti.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Produto {
	@Id
	@SequenceGenerator(name = "produto_gen", sequenceName = "produto_seq", allocationSize = 1)
	@GeneratedValue(generator = "produto_gen", strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotBlank(message = "Campo Nome não pode ser vazio")
	private String nome;
	private String descricao;
	private Double preco;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return String.format("Produto [id=%s, nome=%s, descricao=%s, preco=%s]", id, nome, descricao, preco);
	}		
}
