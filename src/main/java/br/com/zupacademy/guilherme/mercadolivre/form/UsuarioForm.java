package br.com.zupacademy.guilherme.mercadolivre.form;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.zupacademy.guilherme.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.guilherme.mercadolivre.model.Usuario;

public class UsuarioForm {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;

	public UsuarioForm(@NotBlank @Email String email, @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;

	}

	public Usuario toModel() {
		return new Usuario(this.email, this.senha);
	}

}
