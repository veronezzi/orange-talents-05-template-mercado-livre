package br.com.zupacademy.guilherme.mercadolivre.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

	void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom, @NotBlank @Email String from,
			@NotBlank @Email String to);

}
