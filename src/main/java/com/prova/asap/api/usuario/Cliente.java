/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Santana
 */
@Document(collection = "cliente")
@Data
@AllArgsConstructor
public class Cliente implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "cliente_sequence";
    
    @Id
    @Field("_id")
    private String id;
    @NotNull
    private Integer idCliente;
    @NotNull
    @Indexed(unique=true)
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private String cidade;
    @NotNull
    private String uf;

    public Cliente() {
    }

    public void setId(String id) {
        this.id = id;
        this.idCliente = Integer.parseInt(id);
    }

}
