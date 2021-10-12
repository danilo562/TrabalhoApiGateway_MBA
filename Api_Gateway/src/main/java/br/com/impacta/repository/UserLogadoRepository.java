package br.com.impacta.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.entities.UserRoles;


public interface UserLogadoRepository extends JpaRepository<UserRoles, Long> {
	
	//@Query("SELECT * FROM projeto_api.tb_user_role where user_id =:user_id")
	 @Query(value = "SELECT * FROM projeto_api.tb_user_role where user_id = ?1", nativeQuery = true)
	UserRoles findByUser_id(Long user_id);
	
 

}
