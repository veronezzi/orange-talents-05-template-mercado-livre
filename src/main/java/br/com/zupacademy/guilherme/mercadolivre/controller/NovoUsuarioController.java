package br.com.zupacademy.guilherme.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.mercadolivre.form.ClienteForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Cliente;

@RestController
@RequestMapping("/cadastrarCliente")
public class NovoUsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String cadastrarCliente(@RequestBody @Valid ClienteForm form) {
		Cliente cliente = form.toModel();
		manager.persist(cliente);
		return cliente.toString();

	}

}
