package br.com.impacta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.impacta.entities.Usuario;
import br.com.impacta.repository.RoleRepository;
import br.com.impacta.repository.UserLogadoRepository;
import br.com.impacta.repository.UsuarioRepository;
import ch.qos.logback.core.encoder.Encoder;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository repository_Role;
	
	@Autowired
	private UserLogadoRepository repository_user_logado;
	
	@Transactional
	public Usuario salvar(Usuario usu) {
		return repository.save(usu);
		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario =  repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("usuario nao encontrado."));
//		var userLog = repository_user_logado.findByUser_id(usuario.getId());
//		String rol="";
//		var role1 = repository_Role.getById(userLog.getRole_id());
//		System.out.println("Role NAme >> "+ role1.getClass().getName().toString());
//		rol =  role1.getRoleName();
//		System.out.println("Role NAme >> "+ role1.getRoleName());
//		
//		System.out.println("Rol >> "+ rol);
		
		
//		for(int i=0; i<  userLog.size();i++) {
//			System.out.println("id role >> "+userLog.get(i).getRole_id());
//			var role = repository_Role.getById(userLog.get(i).getRole_id());
//			System.out.println("id role >> "+userLog.get(i).getRole_id());
//			System.out.println("Role NAme >> "+ role.getRoleName());
//			rol = rol+""+ role.getRoleName()+",";
//			
//		}
		
//		System.out.println("Role completo >> "+ rol);
		String roles ="USER";
		
		return User.builder().username(usuario.getEmail())
				.password(encoder.encode(usuario.getPassword()) )
				.roles(roles)
				.build();
	}

}
