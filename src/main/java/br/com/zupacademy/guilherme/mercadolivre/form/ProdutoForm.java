package br.com.zupacademy.guilherme.mercadolivre.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;
import br.com.zupacademy.guilherme.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.guilherme.mercadolivre.model.Categoria;

public class ProdutoForm {

	@NotBlank
	@UniqueValue(domainClass = Produto.class,fieldName = "nome")
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	//ExistId(domaindClass = Categoria.class,fieldName="id")
	private Long idCategoria;
	private LocalDateTime dataCadastro = LocalDateTime.now();

	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaForm> caracteristicas = new ArrayList<>();

	public ProdutoForm(@NotBlank String nome, @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, LocalDateTime dataCadastro, Long idCategoria,
			List<NovaCaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public List<NovaCaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModel(EntityManager manager,Usuario dono) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome, valor, quantidade, descricao,categoria,dono,caracteristicas);
	}
	
	
	
	
	

	public boolean caracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		for (NovaCaracteristicaForm caracteristica : caracteristicas) {
			if (!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}
		return false;
	}

}
