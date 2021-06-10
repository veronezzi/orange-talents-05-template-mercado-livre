package br.com.zupacademy.guilherme.mercadolivre.form;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.guilherme.mercadolivre.model.Opiniao;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;

public class OpiniaoForm {

	@Min(1)
	@Max(5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoForm(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
		return new Opiniao(nota, titulo, descricao, produto, consumidor);
	}

}
