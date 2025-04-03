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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {
    
    @Autowired
    private ClienteServices services;

    @GetMapping(value = "/filtros")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Buscar clientes com paginação",
            description = "Retorna uma lista paginada de clientes com possibilidade de filtros e ordenação"
        )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Filtros de busca inválidos")
    })
    public Page<ClienteDTO> buscarTodosPaginaFiltros(Pageable pageable) {
        return services.buscarTodosPaginaFiltros(pageable);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    
    public List<ClienteDTO> buscarTodos() {
        return services.buscarTodos();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar cliente por ID", description = "Retorna os detalhes de um cliente específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "ID do cliente não encontrado")
        })
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return services.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar um novo cliente", description = "Cria um novo cliente e retorna os dados salvos.")
    
    public ClienteDTO adicionarNovo(@RequestBody ClienteDTO clienteDTO) {
        return services.adicionarNovo(clienteDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar um cliente", description = "Atualiza os dados de um cliente existente com base no ID informado.")
    
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        return services.atualizar(id, clienteDTO);
    }

    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um cliente", description = "Remove um cliente do sistema com base no ID informado.")
    
    public ClienteDTO deletar(@PathVariable Long id) {
        return services.deletar(id);
    }
}
