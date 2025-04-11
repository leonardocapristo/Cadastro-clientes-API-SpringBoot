package io.github.leonardocapristo.cadastroclientesapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertClienteDTO {

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

	@NotNull(message = "A data de nascimento é obrigatória.")
	@Past(message = "A data de nascimento deve ser no passado.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(type = "string", format = "date", example = "31/12/2000")
	private LocalDate dataNascimento;

	@NotBlank(message = "O CPF é obrigatório.")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00.")
	@Schema(type = "string", example = "000.000.000-00")
	private String cpf;

	public InsertClienteDTO(Cliente cliente) {

		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.cpf = cliente.getCpf();
	}

}
