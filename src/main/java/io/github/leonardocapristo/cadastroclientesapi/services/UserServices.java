package io.github.leonardocapristo.cadastroclientesapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.leonardocapristo.cadastroclientesapi.dto.UserDTO;
import io.github.leonardocapristo.cadastroclientesapi.entities.User;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.IdNaoEncontradoException;
import io.github.leonardocapristo.cadastroclientesapi.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> buscarTodos() {
		
		List<User> lista = repository.findAll();
		
		List<UserDTO> listaDTO = new ArrayList<>();
		
		for (User user : lista) {
			listaDTO.add(new UserDTO(user));
		}
		
		return listaDTO;
	
	}
	
	
	public UserDTO buscarPorId(Long id) {
		
		
	    User entity = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID do cliente não encontrado"));
		return new UserDTO(entity);

	}
}
