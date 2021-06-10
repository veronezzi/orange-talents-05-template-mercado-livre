package br.com.zupacademy.guilherme.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.mercadolivre.form.OpiniaoForm;
import br.com.zupacademy.guilherme.mercadolivre.form.UsuarioForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Opiniao;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;
import br.com.zupacademy.guilherme.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/cadastrarProduto/{id}/adicionaOpiniao")
public class NovaOpiniãoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public String adicionaOpiniao(@RequestBody @Valid OpiniaoForm form, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario consumidor = usuarioRepository.findByEmail("guilherme.veronezzi@zup.com.br").get();

		Opiniao novaOpiniao = form.toModel(produto, consumidor);
		manager.persist(novaOpiniao);
		return novaOpiniao.toString();

	}

}
