/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.models;

import br.com.prova.asap.api.abstracts.AEntity;
import br.com.prova.asap.api.cliente.models.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Document(collection = "apolice")
@Data
@AllArgsConstructor
@Service
public class Apolice extends AEntity implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "apolice_sequence";

    @NotNull
    @Indexed(unique = true)
    private String numero;
    @NotNull
    private Date vigenciaInicio;
    @NotNull
    private Date vigenciaFim;
    @NotNull
    private String placaVeiculo;
    @NotNull
    private BigDecimal valor;

    @DBRef(lazy = true)
    private Cliente cliente;


    public Apolice(){

    }
}
