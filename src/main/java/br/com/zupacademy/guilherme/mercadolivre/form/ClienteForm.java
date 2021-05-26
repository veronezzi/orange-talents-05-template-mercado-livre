package br.com.zupacademy.guilherme.mercadolivre.form;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.guilherme.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.guilherme.mercadolivre.model.Cliente;



public class ClienteForm {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Cliente.class, fieldName = "email")
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;

	public ClienteForm(@NotBlank @Email String email, @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;

	}

	public Cliente toModel() {
		return new Cliente(this.email, this.senha);
	}
}
