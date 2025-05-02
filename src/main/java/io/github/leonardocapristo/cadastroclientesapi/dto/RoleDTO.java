package io.github.leonardocapristo.cadastroclientesapi.dto;

import io.github.leonardocapristo.cadastroclientesapi.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RoleDTO {

	private Long id;
	private String authority;
	
	
	public RoleDTO(Role role) {

		this.id = role.getId();
		this.authority = role.getAuthority();
	}
	
	
	
	
}
