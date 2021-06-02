package br.com.zupacademy.guilherme.mercadolivre.seguranca;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	// Recebe Usuario e senha para ser autenticado

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);

			String token = ((br.com.zupacademy.guilherme.mercadolivre.seguranca.TokenService) tokenService).gerarToken(authentication);

			
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		} catch (AuthenticationException e) {

			return ResponseEntity.badRequest().build();

		}

	}

}
