package br.com.zupacademy.guilherme.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.mercadolivre.config.validacao.CaracteristicasValidator;
import br.com.zupacademy.guilherme.mercadolivre.form.ProdutoForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;

@RestController
@RequestMapping("/cadastrarProduto")
public class NovoProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new CaracteristicasValidator());
	}
	
	@PostMapping
	@Transactional
	public String cadastrarCliente(@RequestBody @Valid ProdutoForm form) {
		Produto produto = form.toModel(manager);
		manager.persist(produto);
		
		manager.persist(produto);
		return produto.toString();

	}

}
