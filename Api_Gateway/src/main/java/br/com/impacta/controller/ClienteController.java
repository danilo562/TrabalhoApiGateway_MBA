package br.com.impacta.controller;

import java.util.List;
import java.util.Optional;

import br.com.impacta.exception.ClienteNotFoundException;
import br.com.impacta.model.Cliente;
import br.com.impacta.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.impacta.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name="Clientes-endpoint")
@RestController
@RequestMapping("clientes/v1")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService){
		this.clienteService = clienteService;
	}

	private Logger logger= LoggerFactory.getLogger(ClienteController.class);

	@Operation(summary = "Incluir clientes na base")
	@PostMapping
	public Cliente criarClientes(@RequestBody Cliente cliente) {

		return clienteService.criarCliente(cliente);
	}

	@Operation(summary = "Consultar todos os clientes")
	@GetMapping
	public List<Cliente> consultarClientes() {

		return clienteService.consultarTodosClientes();

	}

	@Operation(summary = "Consultar clientes por nome")
	@GetMapping(value = "/nome/{nome}")
	public List<Cliente> consultarClientesNome(@PathVariable("nome") String nome) {

		return clienteService.consultarClientesNome(nome);

	}
	
	@Operation(summary = "Consultar clientes por ID")
	@GetMapping(value = "/id/{id}")
	public Optional<Cliente> consultarClientesId(@PathVariable("id") int id) {

		return clienteService.consultarClientesId(id);

	}

	@Operation(summary = "Alterar nome e data de nascimento dos clientes por ID ")
	@PutMapping("/{id}")
	public Cliente alterarDadosClienteId(@PathVariable(value = "id") int id,
										 @NotNull @Valid @RequestBody Cliente cliente) {

		return clienteService.alterarDadosClienteId(id, cliente);
	}
	
	@Operation(summary = "Deletar clientes por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarClienteId(@PathVariable(value = "id") int id) {

		try {
			clienteService.deletarClienteId(id);
			return new ResponseEntity<>("Cliente DELETADO", HttpStatus.OK);
		}catch (ClienteNotFoundException e){
			throw new ClienteNotFoundException("Cliente não encontrado pelo ID informado!");
		}

	}

}
