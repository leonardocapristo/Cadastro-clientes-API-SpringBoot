package io.github.leonardocapristo.cadastroclientesapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateClienteDTO {
	

	@NotBlank(message = "O nome é obrigatório.")
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
	@Schema(type = "string", example = "Leonardo")
	private String nome;
	
	@NotBlank(message = "O e-mail é obrigatório.")
	@Email(message = "O e-mail deve ser válido.")
	@Schema(type = "string", example = "leonardo@gmail.com")
	private String email;
	
	@NotNull(message = "O telefone é obrigatório.")
	@Digits(integer = 11, fraction = 0, message = "O telefone deve conter até 11 dígitos numéricos.")
	@Schema(type = "integer",example = "11987654321")
	private Long telefone;

	
	public UpdateClienteDTO(Cliente cliente) {

		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();

	}

}
