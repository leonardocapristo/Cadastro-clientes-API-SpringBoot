package io.github.leonardocapristo.cadastroclientesapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {
	
	private Long id;
	private String nome;
	private String email;
	private Long telefone;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(type = "string", format = "date", example = "31/12/2000")
	private LocalDate dataNascimento;
	private String cpf;
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.cpf = cliente.getCpf(); 
	}

}
