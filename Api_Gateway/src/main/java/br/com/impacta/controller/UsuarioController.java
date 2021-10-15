package br.com.impacta.controller;

import br.com.impacta.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.impacta.entities.Usuario;
import br.com.impacta.repository.UsuarioRepository;
import br.com.impacta.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="API_GATEWAY endpoint")
@RestController
@RequestMapping("api_gateway_user")
public class UsuarioController {

	private  UsuarioServiceImpl usuarioService;
	private  PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository repository_user;

	@Operation(summary = "Para criar o usuario")
	@PostMapping("/Usuario/Created/{email}/{password}/{nome}/{role}")
//	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@PathVariable("email") String email,
						  @PathVariable("password") String password,
						  @PathVariable("nome") String nome,
						  @PathVariable("role") String role) {
		Usuario usu = new Usuario();
		//	String senhaCriptografada = passwordEncoder.encode(password);
		usu.setPassword(password);
		usu.setEmail(email);
		usu.setNome(nome);
		usu.setRoles(role);
		var cad = repository_user.save(usu);
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
