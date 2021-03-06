/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prova.asap.api.usuario.ClienteDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApoliceConsultaDTO {
    
    //@JsonIgnore
    private Integer numero;
    private Integer idApolice;
    private Date vigencia_inicio;
    private Date vigencia_fim;
    private String placa_veiculo;
    private BigDecimal valor;
    private ClienteDTO cliente;


    public static Apolice create(ApoliceConsultaDTO apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, Apolice.class);
    }

    public static ApoliceConsultaDTO create(Apolice apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, ApoliceConsultaDTO.class);
    }
}
