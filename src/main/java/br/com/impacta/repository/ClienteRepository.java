package br.com.impacta.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.entities.Cliente;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeStartingWith(String nome);
}
