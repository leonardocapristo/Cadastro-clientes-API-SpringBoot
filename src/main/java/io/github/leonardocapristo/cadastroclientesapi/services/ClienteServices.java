package io.github.leonardocapristo.cadastroclientesapi.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.leonardocapristo.cadastroclientesapi.dto.ClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.github.leonardocapristo.cadastroclientesapi.repositories.ClienteRepository;

@Service
public class ClienteServices {
	
	@Autowired
	private ClienteRepository repository;
	
	public Page<ClienteDTO> buscarTodosPaginaFiltros(PageRequest pageRequest) {
		
	    Page<Cliente> lista = repository.findAll(pageRequest);
	    
	    return lista.map(cliente -> new ClienteDTO(cliente));
	}

	
	public List<ClienteDTO> buscarTodos() {
		
		List<Cliente> lista = repository.findAll();
		
		List<ClienteDTO> listaDTO = new ArrayList<>();
		
		for (Cliente cliente : lista) {
			listaDTO.add(new ClienteDTO(cliente));
		}
		
		return listaDTO;
		
		
		/*
		 * 
		 *    	OU PODE SER FEITO ASSIM : 
		 
		 
		return repository.findAll().stream()
                     .map(cliente -> new ClienteDTO(cliente))
                     .collect(Collectors.toList());
                     
                     
                     
		 * 
		 * */
	}
	
	
	public ClienteDTO buscarPorId(Long id) {
		
	    Cliente entity = repository.findById(id).get();
	    
		return new ClienteDTO(entity);

	}
	
	
	public ClienteDTO adicionarNovo(ClienteDTO clienteDTO) {
		
		Cliente entity = new Cliente();
		
		entity.setNome(clienteDTO.getNome());
		entity.setEmail(clienteDTO.getEmail());
		entity.setTelefone(clienteDTO.getTelefone());
		
		repository.save(entity);
		
		
		return new ClienteDTO(entity);
		
	}
	
	
	public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
		
		Cliente entity = repository.findById(id).get();
		
		entity.setNome(clienteDTO.getNome());
		entity.setEmail(clienteDTO.getEmail());
		entity.setTelefone(clienteDTO.getTelefone());
		
		repository.save(entity);
		
		return new ClienteDTO(entity);
		
	}
	
	public ClienteDTO deletar (Long id) {
		
		Cliente entity = repository.findById(id).get();
		
		repository.deleteById(entity.getId());
		
		ClienteDTO dto = new ClienteDTO(entity);
		
		
		
		return dto;
		
	}

	
	

	
	
	
	
	

}
