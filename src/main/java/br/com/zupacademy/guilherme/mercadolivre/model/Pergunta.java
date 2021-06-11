package br.com.zupacademy.guilherme.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@ManyToOne
	@NotNull
	@Valid
	private Usuario interessado;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	private LocalDate instante;

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario interessado, @NotNull @Valid Produto produto) {
		this.titulo = titulo;
		this.interessado = interessado;
		this.produto = produto;
		this.instante = LocalDate.now();
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", interessado=" + interessado + ", produto=" + produto
				+ "]";
	}

	public Usuario getInteressada() {
		return interessado;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	public Object getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interessado == null) ? 0 : interessado.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (interessado == null) {
			if (other.interessado != null)
				return false;
		} else if (!interessado.equals(other.interessado))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
