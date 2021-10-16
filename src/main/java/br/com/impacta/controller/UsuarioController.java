package br.com.impacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.impacta.entities.Usuario;
import br.com.impacta.repository.UsuarioRepository;
import br.com.impacta.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Usuário-endpoint")
@RestController
@RequestMapping("api_gateway_user")
public class UsuarioController {

	private UsuarioService usuarioService;
	private  PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository repository_user;

	@Operation(summary = "Criar usuário")
	@PostMapping("/Usuario/Created/{email}/{password}/{nome}/{role}")
	public Usuario salvar(@PathVariable("email") String email,
						  @PathVariable("password") String password,
						  @PathVariable("nome") String nome,
						  @PathVariable("role") String role) {
		Usuario user = new Usuario();
		//	String senhaCriptografada = passwordEncoder.encode(password);
		user.setPassword(password);
		user.setEmail(email);
		user.setNome(nome);
		user.setRoles(role);
		var cad = repository_user.save(user);
		return cad;
	}


	/*
	@Operation(summary = "Criar usuarios sistemico com regras de privilegios")
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {

		return usuarioService.salvarUsuario(usuario);

	}

	 */

}
