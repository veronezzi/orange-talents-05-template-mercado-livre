package br.com.zupacademy.guilherme.mercadolivre.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import com.sun.istack.Nullable;

import br.com.zupacademy.guilherme.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.guilherme.mercadolivre.model.Categoria;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Nullable
	private Long idCategoriaMae;

	public CategoriaForm(@NotBlank String nome,@Nullable Long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public Categoria toModel(EntityManager manager) {
		if (idCategoriaMae !=null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			return new Categoria(this.nome, categoriaMae);
		}
		else {
			return new Categoria(this.nome,null);
		}
	

	}

}
