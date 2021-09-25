package br.com.impacta.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente,Long> {

	
List<Cliente> findByNome(String nome);
//Cliente findById(long id);
//	
//	ContaCorrente findByIdhg(Long id);  
//
//	
//	@Modifying
//	@Query("update conta u set u.saldo = ?1 where u.id = ?2")
//	 int  setBySaldo(Double firstname, Long lastname);
	
}
