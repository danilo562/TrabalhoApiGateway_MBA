package br.com.impacta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
