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
		this.instante= LocalDate.now();
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

}
