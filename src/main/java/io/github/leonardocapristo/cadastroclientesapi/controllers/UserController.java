package io.github.leonardocapristo.cadastroclientesapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO buscarPorId(@PathVariable Long id) {
        return services.buscarPorId(id);
    }

}
