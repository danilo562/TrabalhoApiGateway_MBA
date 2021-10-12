package br.com.impacta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
