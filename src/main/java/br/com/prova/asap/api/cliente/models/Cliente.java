/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.models;


import java.io.Serializable;
import javax.validation.constraints.NotNull;

import br.com.prova.asap.api.abstracts.AModel;
import br.com.prova.asap.api.infra.exception.GenericException;
import br.com.prova.asap.api.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Document(collection = "cliente")
@Data
@AllArgsConstructor
@Service
public class Cliente extends AModel implements Serializable {

    @NotNull
    @Indexed(unique=true)
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private String cidade;
    @NotNull
    private String uf;

    public Cliente(){

    }

    public void setCpf(String cpf){
        if (!Util.isCPF(cpf)) {
            throw new GenericException("O CPF informado é inválido.");
        } else {
            this.cpf = cpf;
        }
    }



}
