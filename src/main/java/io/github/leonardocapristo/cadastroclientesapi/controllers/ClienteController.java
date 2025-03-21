package io.github.leonardocapristo.cadastroclientesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.leonardocapristo.cadastroclientesapi.dto.ClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.services.ClienteServices;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServices services;

	
	@GetMapping(value = "/filtros")
	public Page<ClienteDTO> buscarTodosPaginaFiltros(
		    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
		    @RequestParam(value = "camposPorPagina", defaultValue = "6") Integer camposPorPagina,
		    @RequestParam(value = "direcao", defaultValue = "ASC") String direcao, // Default para ASC mas pode ser DESC
		    @RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor
		) {
		    


		    PageRequest pageRequest = PageRequest.of(pagina, camposPorPagina, Direction.valueOf(direcao),ordenarPor);


		    return services.buscarTodosPaginaFiltros(pageRequest);
	}




	
	@GetMapping
	public List<ClienteDTO> buscarTodos() {
		return services.buscarTodos();
	}
	
	@GetMapping(value = "/{id}")
	public ClienteDTO buscarPorId(@PathVariable Long id) {
		return services.buscarPorId(id);
	}
	
	
	@PostMapping
	public ClienteDTO adicionarNovo(@RequestBody ClienteDTO clienteDTO) {
		return services.adicionarNovo(clienteDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ClienteDTO atualizar(@PathVariable Long id ,@RequestBody ClienteDTO clienteDTO) {
		return services.atualizar(id, clienteDTO);
		
		
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ClienteDTO deletar(@PathVariable Long id) {
		return services.deletar(id);
		
	}
	
	
	
	
}
