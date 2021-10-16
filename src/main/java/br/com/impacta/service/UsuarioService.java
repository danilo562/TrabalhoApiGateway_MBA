package br.com.impacta.service;

import javax.transaction.Transactional;

import br.com.impacta.exception.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.impacta.entities.Usuario;
import br.com.impacta.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioRepository repository;

	@Transactional
	public Usuario salvar(Usuario usu) {
		return repository.save(usu);

	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario =  repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("usuario nao encontrado."));

		String roles ="USER";

		return User.builder().username(usuario.getEmail())
				.password(encoder.encode(usuario.getPassword()) )
				.roles(usuario.getRoles())
				.build();
	}






	/*
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {

		var criarUsuario = usuarioRepository.findById(usuario.getId()).isPresent();

		if(!criarUsuario){
			throw new ClienteNotFoundException("Usuário já existente na base de dados!");
		}

		return usuarioRepository.save(usuario);

	}
	*/

}
