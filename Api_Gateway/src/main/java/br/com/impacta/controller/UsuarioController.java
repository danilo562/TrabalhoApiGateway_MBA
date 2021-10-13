package br.com.impacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.entities.User;
import br.com.impacta.entities.UserRoles;
import br.com.impacta.entities.Usuario;
import br.com.impacta.entities.Role;
import br.com.impacta.entities.UsuarioLogado;
import br.com.impacta.repository.RoleRepository;
import br.com.impacta.repository.UserLogadoRepository;
import br.com.impacta.repository.UserRepository;
import br.com.impacta.repository.UsuarioRepository;
import br.com.impacta.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="API_GATEWAY endpoint")
@RestController
@RequestMapping("api_gateway_user")
public class UsuarioController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository repository_Role;
	
	@Autowired
	private UserLogadoRepository repository_user_logado;
	
	private  UsuarioServiceImpl usuarioService;
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repository_user;
	
	@Operation(summary = "Login Usuario pelo email e senha")
	@GetMapping(value = "/login_usu/{email}/{password}")
	public UsuarioLogado getLogin(
			@PathVariable("email") String email,
			@PathVariable("password") String password) {

		UsuarioLogado usuLog = new UsuarioLogado();
		var user = repository.findByEmail(email);
		if (user == null) { throw new RuntimeException("Nao Encontrou o Usuario ============== <<<<<<<<<<<");
		
		}else if(user.getPassword().equals(password)) {
			
			var userLog = repository_user_logado.findByUser_id(user.getId());
			var role = repository_Role.getById(userLog.getRole_id());
			
			usuLog.setUser_id(user.getId());
			usuLog.setName(user.getName());
			usuLog.setEmail(user.getEmail());
			usuLog.setRole_id(role.getId());
			usuLog.setRoleName(role.getRoleName());
			
		}

		return usuLog;

	}
	
	@Operation(summary = "Para criar o usuario")
	@PostMapping("/Usuario/Created/{email}/{password}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		Usuario usu = new Usuario();
		String senhaCriptografada = passwordEncoder.encode(password);
		usu.setPassword(senhaCriptografada);
		usu.setEmail(email);
		usu.setNome("TESTE");
		var cad = usuarioService.salvar(usu);
		return cad;
		
		
	}
	

}
