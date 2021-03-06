/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.infra.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;

/**
 *
 * @author Santana
 */
@AllArgsConstructor
public class ObjetoErro implements Serializable{
    String mensagemErro;
    String campo;
    Object valor;
}
