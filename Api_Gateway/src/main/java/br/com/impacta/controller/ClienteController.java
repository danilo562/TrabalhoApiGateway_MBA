package br.com.impacta.controller;

import java.lang.annotation.Retention;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import br.com.impacta.model.Cliente;
import br.com.impacta.repository.ClienteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="API_GATEWAY endpoint")
@RestController
@RequestMapping("api_gateway")
public class ClienteController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ClienteRepository repository;
	

	private Logger logger= LoggerFactory.getLogger(ClienteController.class);
	
	
	@Operation(summary = "Inclui cliente na base ")
	 @PostMapping("/IncluirCliente/{nome}/{datanasc}")
	    public Cliente CreatCli(@PathVariable("nome") String nome,
	    		@PathVariable(value = "datanasc")  String datanasc )
	   {

	   	var cli = new Cliente();
          cli.setNome(nome);
		  cli.setDatanasc(datanasc);
	      var updatedNote = repository.save(cli);

	        return updatedNote;
	    }
	

	@Operation(summary = "Pesquisa Cliente pelo nome ")
	@GetMapping(value = "/pesq_nome/{nome}")
	public List<Cliente> getConta(
			@PathVariable("nome") String nome) {

		var cliente = repository.findByNome(nome);
		if (cliente == null) throw new RuntimeException("Nao Encontrou o Cliente ============== <<<<<<<<<<<");

		return cliente;

	}
	
	@Operation(summary = "Pesquisa Cliente pelo id ")
	@GetMapping(value = "/pesq_id/{id}")
	public Cliente getId(
			@PathVariable("id") long id) {

		var cliente = repository.findById(id).orElseThrow();
		if (cliente == null) throw new RuntimeException("Nao Encontrou o Cliente ============== <<<<<<<<<<<");

		return cliente;

	}
	
	@Operation(summary = "Altera nome e data nascimento pelo id ")
	 @PutMapping("/AlterarCliente/{id}/{nome}/{datanasc}")
	    public Cliente alterarCli(@PathVariable(value = "id") long id,
	    		@PathVariable("nome") String nome,
	    		@PathVariable(value = "datanasc")  String datanasc )
	   {

		 Cliente cliEn = repository.findById(id).orElseThrow();		 
	   	var cli = new Cliente();
       cli.setNome(nome);
		  cli.setDatanasc(datanasc);
		  cli.setId(cliEn.getId());
	      var updatedNote = repository.save(cli);

	        return updatedNote;
	    }
	
	@Operation(summary = "Deleta o cliente pelo id ")
	 @DeleteMapping("/DeletaCliente/{id}")
	    public ResponseEntity<String> DeletaCli(@PathVariable(value = "id") long id
	    		)
	   {

		 Cliente cliEn = repository.findById(id).orElseThrow();		 
	   	 var cli = new Cliente();
		  cli.setId(cliEn.getId());
		  int val=0;
		  
		  if(cliEn.getId() != null ) {
			  repository.delete(cliEn);
			  val=1;
		  }
				   
		    if(val ==1) {
		    	return new ResponseEntity<>("Cliente DELETADO", HttpStatus.OK);
		    }else {
		    	return new ResponseEntity<>("Id Cliente não encontrado ", HttpStatus.NOT_FOUND);
		    }
			
	        
	    }

	   
	
	
	

}
