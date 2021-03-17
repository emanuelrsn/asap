/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author Santana
 * @param <T>
 * @param <D>
 */
public interface IDTO<T, D> {
    public T create();
    public D create(T o);
}
