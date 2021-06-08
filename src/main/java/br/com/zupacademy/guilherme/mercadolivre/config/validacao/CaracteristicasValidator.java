package br.com.zupacademy.guilherme.mercadolivre.config.validacao;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.guilherme.mercadolivre.controller.NovoProdutoController;
import br.com.zupacademy.guilherme.mercadolivre.form.ProdutoForm;

public class CaracteristicasValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			if (errors.hasErrors()) {
				return;
				
			}
		ProdutoForm form = (ProdutoForm) target;
		if (form.caracteristicasIguais()) {
			errors.rejectValue("caracteristicas", null,"Voce possui caracteristicas iguais");
			
		}
	}

}
