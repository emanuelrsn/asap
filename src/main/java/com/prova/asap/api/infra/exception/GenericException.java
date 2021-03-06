/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Santana
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GenericException extends RuntimeException{
    public GenericException(String message){super(message);}
    public GenericException(String message, Throwable cause){super(message, cause);}
}
