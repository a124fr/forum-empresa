package br.com.empresa.forum.empresa.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.empresa.forum.empresa.modelo.Usuario;
import br.com.empresa.forum.empresa.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUsuario = this.usuarioRepository.findByEmail(username);
		
		if (optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos");
	}
	
	
}
