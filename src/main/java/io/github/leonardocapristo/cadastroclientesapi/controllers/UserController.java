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

import io.github.leonardocapristo.cadastroclientesapi.dto.InsertUserDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UpdateUserDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UserDTO;
import io.github.leonardocapristo.cadastroclientesapi.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    @Autowired
    private UserServices services;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> buscarTodos() {
        return services.buscarTodos();
    }
    
    @GetMapping(value = "/filtros")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> buscarTodosPaginaFiltros(Pageable pageable) {
        return services.buscarTodosPaginaFiltros(pageable);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO buscarPorId(@PathVariable Long id) {
        return services.buscarPorId(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO adicionarNovo(@RequestBody InsertUserDTO insertUserDTO) {
        return services.adicionarNovo(insertUserDTO);
    }
    
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO atualizar(@PathVariable Long id,@RequestBody UpdateUserDTO updateUserDTO) {
        return services.atualizar(id, updateUserDTO);
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        services.deletar(id);
    }

}
