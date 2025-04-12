package io.github.leonardocapristo.cadastroclientesapi.services;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import io.github.leonardocapristo.cadastroclientesapi.dto.ClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.InsertClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UpdateClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.FiltrosInvalidosException;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.IdNaoEncontradoException;
import io.github.leonardocapristo.cadastroclientesapi.repositories.ClienteRepository;

@Service
public class ClienteServices {
	
	@Autowired
	private ClienteRepository repository;
	
	public Page<ClienteDTO> buscarTodosPaginaFiltros(Pageable pageable) {
		
		
		try {
			//parametros page,size e sort
			
		    Page<Cliente> lista = repository.findAll(pageable);
		    return lista.map(cliente -> new ClienteDTO(cliente));
		    
		    
		} catch (PropertyReferenceException e) {
			throw new FiltrosInvalidosException("Filtros de busca inválidos");
		}

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
		
		
	    Cliente entity = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID do cliente não encontrado"));
		return new ClienteDTO(entity);

	}
	
	public Page<ClienteDTO> buscarPorCampos(
	        String nome, String email, String cpf, Long telefone,
	        LocalDate dataInicio, LocalDate dataFim, Pageable pageable
	    ) {
	        Page<Cliente> clientesPage = repository.filtrarClientes(
	            nome, email, cpf, telefone, dataInicio, dataFim, pageable
	        );
	        
	        // Converte Page<Cliente> para Page<ClienteDTO>
	        return clientesPage.map(ClienteDTO::new);
	    }
	
	
	public ClienteDTO adicionarNovo(InsertClienteDTO insertClienteDTO) {

			Cliente entity = new Cliente();
			
			entity.setNome(insertClienteDTO.getNome());
			entity.setEmail(insertClienteDTO.getEmail());
			entity.setTelefone(insertClienteDTO.getTelefone());
			entity.setDataNascimento(insertClienteDTO.getDataNascimento());
			entity.setCpf(insertClienteDTO.getCpf());
			
			repository.save(entity);
		
			return new ClienteDTO(entity);

		
	}
	
	
	public ClienteDTO atualizar(Long id, UpdateClienteDTO updateClienteDTO) {
		
		Cliente entity = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID do cliente não encontrado"));
		
		entity.setNome(updateClienteDTO.getNome());
		entity.setEmail(updateClienteDTO.getEmail());
		entity.setTelefone(updateClienteDTO.getTelefone());

		
		repository.save(entity);
		
		return new ClienteDTO(entity);
		
	}
	
	public void deletar (Long id) {
		
		Cliente entity = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID do cliente não encontrado"));
		
		repository.deleteById(entity.getId());
		

		
	}

	
	

	
	
	
	
	

}
