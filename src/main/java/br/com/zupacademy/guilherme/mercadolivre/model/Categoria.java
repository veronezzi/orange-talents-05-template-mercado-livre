package br.com.zupacademy.guilherme.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.Nullable;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	@ManyToOne
	@Nullable
	private Categoria categoriaMae;

	public Categoria(@NotBlank String nome,@Nullable Categoria idCategoriaMae) {
		this.nome = nome;
		this.categoriaMae = idCategoriaMae;

	}

	@Deprecated
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}

}
