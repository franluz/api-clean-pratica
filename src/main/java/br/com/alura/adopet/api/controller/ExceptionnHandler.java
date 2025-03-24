package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.exception.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionnHandler {
    @ExceptionHandler(value = {ValidacaoException.class})
     public ResponseEntity<String> validacaoException(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
