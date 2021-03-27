/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.models;

import br.com.prova.asap.api.abstracts.AModel;
import br.com.prova.asap.api.cliente.models.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;

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
@Service
@Data
public class Apolice extends AModel implements Serializable {

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

    public void setId(String id){
        this.id=id;
        getNumero();
    }

    public String  getNumero(){

        if(Objects.nonNull(this.getId()) && Objects.isNull(this.numero) && Objects.nonNull(cliente)) {
            Integer ano=null;
            ano = Calendar.getInstance().get(Calendar.YEAR);
            this.numero = ano + cliente.getCpf().substring(0, 3) + String.format("%010d", Integer.parseInt(this.getId()));
        }
        return this.numero;
    }
}
