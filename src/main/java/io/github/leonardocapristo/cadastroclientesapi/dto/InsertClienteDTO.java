package io.github.leonardocapristo.cadastroclientesapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertClienteDTO {
	

	private String nome; 
	private String email;
	private Long telefone;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(type = "string", format = "date", example = "31/12/2000")
	private LocalDate dataNascimento;
	@Schema(type = "string", format = "date", example = "000.000.000-00")
	private String cpf;
	
	public InsertClienteDTO(Cliente cliente) {

		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.cpf = cliente.getCpf();
	}

}
