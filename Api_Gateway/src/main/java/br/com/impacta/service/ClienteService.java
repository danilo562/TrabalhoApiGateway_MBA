package br.com.impacta.service;

import br.com.impacta.exception.ClienteNotFoundException;
import br.com.impacta.model.Cliente;
import br.com.impacta.repository.ClienteRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //CRIAR CLIENTES
    public Cliente criarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //GET PARA CONSULTAR TODOS CLIENTES
    public List<Cliente> consultarTodosClientes() {

        var cliente = clienteRepository.findAll();
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há clientes cadastrados na base");

        return cliente;

    }

    //GET PARA CONSULTAR CLIENTE POR NOME
    public List<Cliente> consultarClientesNome(String nome) {

        var cliente = clienteRepository.findByNome(nome);
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há clientes cadastrados com nome: " + nome);

        return cliente;

    }

    //GET PARA CONSULTAR CLIENTE POR ID
    public Optional<Cliente> consultarClientesId(int id) {

        var cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) throw new ClienteNotFoundException("Não há clientes cadastrados com id: " + id);

        return cliente;

    }

    //ALTERARA CLIENTE ATRAVES DO ID
    public Cliente alterarDadosClienteId(int id,  Cliente cliente) {

        var consultaCliente = clienteRepository.findById(id).isPresent();

        if(!consultaCliente){
            throw new ClienteNotFoundException("Não encontrado clientes cadastrados com id informado.");
        }else{
            var clienteAtualiza = consultarClientesId(id).get();
            clienteAtualiza.setNome(cliente.getNome());
            clienteAtualiza.setDataNascimento(cliente.getDataNascimento());

            return clienteRepository.save(clienteAtualiza);
        }

    }


    public void deletarClienteId(int id){

        var consultaCliente = clienteRepository.findById(id).isPresent();

        if(!consultaCliente){
            throw new ClienteNotFoundException("Não encontrado clientes cadastrados com id informado.");
        }else{
            var clienteDelete = consultarClientesId(id).get();
             clienteRepository.delete(clienteDelete);
        }
    }

}
