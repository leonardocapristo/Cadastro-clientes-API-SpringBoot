package io.github.leonardocapristo.cadastroclientesapi.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Data
@Table(name = "tb_clientes")
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	
	private String nome;
	private String email;
	private Long telefone;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	

}
