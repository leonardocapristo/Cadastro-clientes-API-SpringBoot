package io.github.leonardocapristo.cadastroclientesapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import io.github.leonardocapristo.cadastroclientesapi.dto.InsertUserDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UpdateUserDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UserDTO;
import io.github.leonardocapristo.cadastroclientesapi.entities.User;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.FiltrosInvalidosException;
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

	public Page<UserDTO> buscarTodosPaginaFiltros(Pageable pageable) {

		try {
			// parametros page,size e sort

			Page<User> lista = repository.findAll(pageable);
			return lista.map(user -> new UserDTO(user));

		} catch (PropertyReferenceException e) {
			throw new FiltrosInvalidosException("Filtros de busca inválidos");
		}

	}

	public UserDTO buscarPorId(Long id) {

		User entity = repository.findById(id)
				.orElseThrow(() -> new IdNaoEncontradoException("ID do usuario não encontrado"));
		return new UserDTO(entity);

	}

	public UserDTO adicionarNovo(InsertUserDTO insertUserDTO) {

		User entity = new User();

		entity.setFirstName(insertUserDTO.getEmail());
		entity.setLastName(insertUserDTO.getLastName());
		entity.setEmail(insertUserDTO.getEmail());
		entity.setPassword(insertUserDTO.getPassword());

		repository.save(entity);

		return new UserDTO(entity);

	}
	
	public UserDTO atualizar(Long id, UpdateUserDTO updateUserDTO) {
		
		User entity = repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID do cliente não encontrado"));
		
		entity.setFirstName(updateUserDTO.getFirstName());
		entity.setLastName(updateUserDTO.getLastName());
		entity.setEmail(updateUserDTO.getEmail());
		entity.setPassword(updateUserDTO.getPassword());

		
		repository.save(entity);
		
		return new UserDTO(entity);
		
	}

	public void deletar(Long id) {

		User entity = repository.findById(id)
				.orElseThrow(() -> new IdNaoEncontradoException("ID do usuario não encontrado"));

		repository.deleteById(entity.getId());

	}
}
