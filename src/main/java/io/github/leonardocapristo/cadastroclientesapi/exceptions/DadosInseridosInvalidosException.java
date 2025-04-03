package io.github.leonardocapristo.cadastroclientesapi.exceptions;

public class DadosInseridosInvalidosException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public DadosInseridosInvalidosException (String message) {
		super(message);
	}

}
