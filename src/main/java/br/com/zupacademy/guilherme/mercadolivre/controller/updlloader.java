package br.com.zupacademy.guilherme.mercadolivre.controller;

import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class updlloader {

	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem -> "http://buket.io/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
