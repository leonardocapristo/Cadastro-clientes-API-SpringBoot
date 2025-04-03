package io.github.leonardocapristo.cadastroclientesapi.exceptions.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.leonardocapristo.cadastroclientesapi.exceptions.IdNaoEncontradoException;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.FiltrosInvalidosException;

@ControllerAdvice
public class GlobalExceptionReceiver {

	@ExceptionHandler(IdNaoEncontradoException.class)
    public ResponseEntity<String> handleIdNaoEncontradoException(IdNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
	
	@ExceptionHandler(FiltrosInvalidosException.class)
    public ResponseEntity<String> FiltrosInvalidosException(FiltrosInvalidosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
