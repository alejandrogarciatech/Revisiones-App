package com.pfc.revisiones.app.inventario.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pfc.revisiones.app.inventario.entities.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError(e.getMessage());
        error.setMessage("Error en la petición");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.internalServerError().body("error 500");
    }
}