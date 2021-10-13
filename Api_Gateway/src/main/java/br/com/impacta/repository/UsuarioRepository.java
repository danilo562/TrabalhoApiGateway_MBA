package br.com.impacta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import br.com.impacta.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
