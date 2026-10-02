package com.luispedro.tarefas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luispedro.tarefas.exeption.RegraNegocioException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Map<String, String>> handleRegraNegocio(RegraNegocioException ex) {
		Map<String, String> erro = new HashMap<>();
		erro.put("erro", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
