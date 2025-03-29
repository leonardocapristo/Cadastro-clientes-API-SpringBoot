package io.github.leonardocapristo.cadastroclientesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leonardocapristo.cadastroclientesapi.dto.ClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.services.ClienteServices;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServices services;

	
	@GetMapping(value = "/filtros")
	@ResponseStatus(HttpStatus.OK)
	public Page<ClienteDTO> buscarTodosPaginaFiltros(Pageable pageable) {
		//parametros page,size e sort
		
		    return services.buscarTodosPaginaFiltros(pageable);
	}

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteDTO> buscarTodos() {
		return services.buscarTodos();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public ClienteDTO buscarPorId(@PathVariable Long id) {
		return services.buscarPorId(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ClienteDTO adicionarNovo(@RequestBody ClienteDTO clienteDTO) {
		return services.adicionarNovo(clienteDTO);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDTO atualizar(@PathVariable Long id ,@RequestBody ClienteDTO clienteDTO) {
		return services.atualizar(id, clienteDTO);
		
		
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ClienteDTO deletar(@PathVariable Long id) {
		return services.deletar(id);
		
	}
	
	
	
	
	
	/*
	PODEMOS ENCAPSULAR OS MÉTODOS DENTRO DE UM RESPONSE ENTITY PAAR PERSONALIZAR AS REPOSTAS
	
	EX : 
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteDTO> buscarTodos() {
		return services.buscarTodos();
	}
			|
			|
			|
			
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodos(){
		return ResponseEntity.ok().body(services.buscarTodos());
	}
	
	
	
	
	 * */
	
	
}
