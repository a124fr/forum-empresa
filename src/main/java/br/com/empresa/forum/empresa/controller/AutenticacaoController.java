package br.com.empresa.forum.empresa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.forum.empresa.config.security.TokenService;
import br.com.empresa.forum.empresa.controller.dto.TokenDTO;
import br.com.empresa.forum.empresa.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();		
		
		try {
			Authentication authentication = this.authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
						
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));	
			
		} catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
