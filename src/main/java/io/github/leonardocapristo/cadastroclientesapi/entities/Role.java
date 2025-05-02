package io.github.leonardocapristo.cadastroclientesapi.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String authority;
}
