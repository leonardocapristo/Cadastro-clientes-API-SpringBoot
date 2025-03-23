package io.github.leonardocapristo.cadastroclientesapi.exceptions;

public class IdNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public IdNaoEncontradoException (String message) {
		super(message);
	}

}
