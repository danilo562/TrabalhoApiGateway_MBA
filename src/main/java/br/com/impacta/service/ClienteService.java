package br.com.impacta.service;

import br.com.impacta.controller.ClienteController;
import br.com.impacta.exception.ClienteNotFoundException;
import br.com.impacta.entities.Cliente;
import br.com.impacta.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    private Logger logger= LoggerFactory.getLogger(ClienteController.class);

    //CRIAR CLIENTES
    public Cliente criarCliente(Cliente cliente){

        logger.info("Criando cliente [nome: {}] [dataNascimento: {}]", cliente.getNome(), cliente.getDataNascimento());

        return clienteRepository.save(cliente);
    }

    //GET PARA CONSULTAR TODOS CLIENTES
    public List<Cliente> consultarClientes() {

        logger.info("Consultando todos clientes");

        var cliente = clienteRepository.findAll();
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há clientes cadastrados na base");

        return cliente;
    }

    //GET PARA CONSULTAR CLIENTE POR NOME
    public List<Cliente> consultarClienteNome(String nome) {

        logger.info("Consultando cliente por nome: {}", nome);

        var cliente = clienteRepository.findByNomeStartingWith(nome);
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há cliente cadastrado com nome: " + nome);

        return cliente;
    }

    //GET PARA CONSULTAR CLIENTE POR ID
    public Optional<Cliente> consultarClienteId(int id) {

        logger.info("Consultando cliente por id: {}", id);

        var cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há cliente cadastrado com id: " + id);

        return cliente;
    }

    //ALTERAR CLIENTE ATRAVES DO ID
    public Cliente alterarDadosClienteId(int id,  Cliente cliente) {

        logger.info("Alterando cliente id {} : [nome: {}] [dataNascimento: {}]", id, cliente.getNome(), cliente.getDataNascimento());

        var consultaCliente = clienteRepository.findById(id).isPresent();

        if(!consultaCliente){
            throw new ClienteNotFoundException("Não encontrado cliente cadastrado com id informado.");
        }else{
            var clienteAtualizado = consultarClienteId(id).get();
            clienteAtualizado.setNome(cliente.getNome());
            clienteAtualizado.setDataNascimento(cliente.getDataNascimento());

            logger.info("Cliente atualizado: [nome: {}] [dataNascimento: {}]", clienteAtualizado.getNome(), clienteAtualizado.getDataNascimento());

            return clienteRepository.save(clienteAtualizado);
        }
    }

    //DELETAR CLIENTE ATRAVES DO ID
    public void deletarClienteId(int id){

        var consultaCliente = clienteRepository.findById(id).isPresent();

        if(!consultaCliente){
            throw new ClienteNotFoundException("Não encontrado clientes cadastrados com id informado.");
        }else{
            var clienteDelete = consultarClienteId(id).get();

            logger.info("Cliente deletado [nome: {}] [id: {}]", clienteDelete.getNome(), clienteDelete.getIdCliente());

             clienteRepository.delete(clienteDelete);
        }
    }
}
