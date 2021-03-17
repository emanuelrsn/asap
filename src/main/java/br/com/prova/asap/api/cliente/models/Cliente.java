/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.models;

import br.com.prova.asap.api.apolice.models.ApoliceSequence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class Cliente extends ClienteSequence implements Serializable {

    
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
        SEQUENCE_NAME = "cliente_sequence";
    }

    public void setIdSequence(String id) {
        setId(id);
        this.idCliente = Integer.parseInt(id);
    }

}
