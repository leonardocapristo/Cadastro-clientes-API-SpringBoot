package io.github.leonardocapristo.cadastroclientesapi.dto;

import java.util.HashSet;
import java.util.Set;

import io.github.leonardocapristo.cadastroclientesapi.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UpdateUserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@Setter(AccessLevel.NONE)
	Set<RoleDTO> rolesDTO = new HashSet<>();
	
	public UpdateUserDTO (User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		
	}
}


