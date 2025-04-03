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
public class AdicionarClienteDTO {
	
	private String nome;
	private String email;
	private Long telefone;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(example = "03/04/2025")
	private LocalDate dataNascimento;
	
	public AdicionarClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
	}

}
