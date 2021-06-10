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
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.guilherme.mercadolivre.form.OpiniaoForm;
import br.com.zupacademy.guilherme.mercadolivre.form.PerguntaForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Emails;
import br.com.zupacademy.guilherme.mercadolivre.model.Opiniao;
import br.com.zupacademy.guilherme.mercadolivre.model.Pergunta;
import br.com.zupacademy.guilherme.mercadolivre.model.Produto;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;
import br.com.zupacademy.guilherme.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/cadastrarProduto/{id}/adicionaPergunta")
public class NovaPerguntaController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Emails emails;

	@PostMapping
	@Transactional
	public String adicionaOpiniao(@RequestBody @Valid PerguntaForm form, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario interessado = usuarioRepository.findByEmail("guilherme.veronezzi@zup.com.br").get();

		Pergunta novaPergunta = form.toModel(produto, interessado);
		manager.persist(novaPergunta);

		emails.novaPergunta(novaPergunta);

		return novaPergunta.toString();

	}

}
