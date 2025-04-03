package io.github.leonardocapristo.cadastroclientesapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateClienteDTO {
	

	private String nome;
	private String email;
	private Long telefone;

	
	public UpdateClienteDTO(Cliente cliente) {

		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();

	}

}
