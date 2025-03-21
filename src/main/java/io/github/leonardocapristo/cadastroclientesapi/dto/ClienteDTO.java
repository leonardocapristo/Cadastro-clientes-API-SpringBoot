package io.github.leonardocapristo.cadastroclientesapi.dto;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
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
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
	}

}
