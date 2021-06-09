package br.com.zupacademy.guilherme.mercadolivre.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.guilherme.mercadolivre.config.validacao.CaracteristicasValidator;
import br.com.zupacademy.guilherme.mercadolivre.form.ImagemForm;
import br.com.zupacademy.guilherme.mercadolivre.form.ProdutoForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;
import br.com.zupacademy.guilherme.mercadolivre.repository.UsuarioRepository;

@RestController
//@RequestMapping("/cadastrarProduto")
public class NovoProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepostiory;
	@Autowired
	private updlloader uploaderFake;

	@InitBinder(value = "ProdutoForm")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new CaracteristicasValidator());
	}

	@PostMapping(value = "/cadastrarProduto")
	@Transactional
	public String cadastrarProduto(@RequestBody @Valid ProdutoForm form) {
		Usuario dono = usuarioRepostiory.findByEmail("guilherme.veronezzi@zup.com.br").get();
		
		
		Produto produto = form.toModel(manager,dono);
		manager.persist(produto);

		return produto.toString();

	}

	@Transactional
	@PostMapping(value = "/cadastrarProduto/{id}/imagens")
	public String adicionaImagens(@PathVariable("id") Long id, @Valid ImagemForm form) {
		
		Produto produto = manager.find(Produto.class, id);
		System.out.println(produto);
		Usuario dono = usuarioRepostiory.findByEmail("guilherme.veronezzi@zup.com.br").get();
		
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Set<String> links = uploaderFake.envia(form.getImagens());
		
		produto.associaImagens(links);

		manager.merge(produto);

		return produto.toString();
	}
}
