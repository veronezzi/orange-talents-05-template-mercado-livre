package br.com.zupacademy.guilherme.mercadolivre.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.guilherme.mercadolivre.model.Pergunta;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;

public class PerguntaForm {

	@NotBlank
	private String titulo;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "PerguntaForm [titulo=" + titulo + "]";
	}

	public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario interessado) {
		return new Pergunta(titulo, interessado, produto);
	}

}
