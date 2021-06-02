package br.com.zupacademy.guilherme.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.mercadolivre.form.CategoriaForm;
import br.com.zupacademy.guilherme.mercadolivre.form.UsuarioForm;
import br.com.zupacademy.guilherme.mercadolivre.model.Categoria;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/cadastrarCategoria")
public class NovaCategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String cadastrarCliente(@RequestBody @Valid CategoriaForm form) {
		Categoria categoria = form.toModel(manager);
		manager.persist(categoria);
		return categoria.toString();

	}

}
