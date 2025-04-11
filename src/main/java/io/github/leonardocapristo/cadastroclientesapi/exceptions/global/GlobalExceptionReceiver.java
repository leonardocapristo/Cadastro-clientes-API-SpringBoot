package io.github.leonardocapristo.cadastroclientesapi.exceptions.global;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.leonardocapristo.cadastroclientesapi.exceptions.IdNaoEncontradoException;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.FiltrosInvalidosException;
import io.github.leonardocapristo.cadastroclientesapi.exceptions.DadosInseridosInvalidosException;

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

	
	@ExceptionHandler(DadosInseridosInvalidosException.class)
    public ResponseEntity<String> DadosInseridosInvalidosException(DadosInseridosInvalidosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inseridos inválidos");
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
	    List<String> erros = new ArrayList<>();

	    for (FieldError fieldError : e.getFieldErrors()) {
	        String mensagem = fieldError.getField() + ": " + fieldError.getDefaultMessage();
	        erros.add(mensagem);
	    }

	    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
	}
}





