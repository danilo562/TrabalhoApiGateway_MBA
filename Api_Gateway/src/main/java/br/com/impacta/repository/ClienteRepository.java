package br.com.impacta.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente,Long> {

	
List<Cliente> findByNome(String nome);



	
}
