package br.com.zupacademy.guilherme.mercadolivre.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.guilherme.mercadolivre.config.Mailer;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {

		mailer.send("<html>...</html>", "Nova Pergunta ...", pergunta.getInteressada().getEmail(),
				"novapergunta@nossomercadolivre.com", pergunta.getDonoProduto().getEmail());

	}

}
