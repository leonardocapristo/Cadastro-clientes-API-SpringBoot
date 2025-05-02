package io.github.leonardocapristo.cadastroclientesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Data
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
