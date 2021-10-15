package br.com.impacta.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNome(String nome);

}
