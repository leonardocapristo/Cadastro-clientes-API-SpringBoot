package io.github.leonardocapristo.cadastroclientesapi.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leonardocapristo.cadastroclientesapi.dto.ClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.InsertClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.dto.UpdateClienteDTO;
import io.github.leonardocapristo.cadastroclientesapi.services.ClienteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
    @Parameters({
        @Parameter(name = "page", description = "Número da página (começa em 0)", example = "1"),
        @Parameter(name = "size", description = "Quantidade de itens por página", example = "5"),
        @Parameter(name = "sort", description = "Ordenação dos itens (ex: nome,asc)", example = "nome,asc")
    })
    public Page<ClienteDTO> buscarTodosPaginaFiltros(Pageable pageable) {
        return services.buscarTodosPaginaFiltros(pageable);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
        })
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
    
    
    
    
    @GetMapping("/filtros/campos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteDTO> filtrarClientes(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String cpf,
        @RequestParam(required = false) Long telefone,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
        Pageable pageable
    ) {
        return services.buscarPorCampos(nome, email, cpf, telefone, dataInicio, dataFim, pageable);
    }
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar um novo cliente", description = "Cria um novo cliente e retorna os dados salvos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clientes criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inseridos inválidos")
        })
    
    public ClienteDTO adicionarNovo(@Valid @RequestBody InsertClienteDTO insertClienteDTO) {
        return services.adicionarNovo(insertClienteDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar um cliente", description = "Atualiza os dados de um cliente existente com base no ID informado, por regra de negócio"
    		+ " é apenas permitido atualizar nome, telefone e email. Campos como data de nascimento e CPF não podem ser alterados.")
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inseridos inválidos")
        })
    public ClienteDTO atualizar(@PathVariable Long id,@Valid @RequestBody UpdateClienteDTO updateClienteDTO) {
        return services.atualizar(id, updateClienteDTO);
    }

    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um cliente", description = "Remove um cliente do sistema com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
        })
    public void deletar(@PathVariable Long id) {
        services.deletar(id);
    }
}
