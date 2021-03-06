/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.infra.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Santana
 */
@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponse apiError
                = new ErrorResponse(HttpStatus.BAD_REQUEST, "Argumento inválido", errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler({
        EmptyResultDataAccessException.class,
        ObjectNotFoundException.class
    })
    public ResponseEntity errorNotFound(Exception ex) {
        List<String> listaErro = new ArrayList<>();
        listaErro.add(ex.getMessage());
        return new ResponseEntity<>(new ExceptionError(listaErro), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
        GenericException.class
    })
    public ResponseEntity errorUniqueException(Exception ex) {
        List<String> listaErro = new ArrayList<>();
        listaErro.add(ex.getMessage());
        return new ResponseEntity<>(new ExceptionError(listaErro), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception ex) {
        return ResponseEntity.badRequest().build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> listaErro = new ArrayList<>();
        listaErro.add(ex.getMessage());
        return new ResponseEntity<>(listaErro, HttpStatus.METHOD_NOT_ALLOWED);
    }

    class ExceptionError implements Serializable {

        private List<String> errors;

        ExceptionError(List<String> errors) {
            this.errors = errors;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

}
