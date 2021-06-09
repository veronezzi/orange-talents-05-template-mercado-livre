package br.com.zupacademy.guilherme.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.guilherme.mercadolivre.form.NovaCaracteristicaForm;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	// @ExistId(domaindClass = Categoria.class,fieldName="id")
	// @NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @Valid Categoria categoria, @NotNull @Valid Usuario dono,
			@Size(min = 3) @Valid Collection<NovaCaracteristicaForm> caracteristicas) {

		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dono = dono;

		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));

	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dono=" + dono + ", caracteristicas="
				+ caracteristicas + ", imagens=" + imagens + "]";
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);

	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

	public Usuario getDono() {
		return this.dono;
	}

}
