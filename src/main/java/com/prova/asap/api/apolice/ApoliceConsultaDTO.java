/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import br.com.prova.asap.api.apolice.util.Constantes;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    
    private String numero;
    private Integer idApolice;
    @JsonFormat(pattern=Constantes.DATE_PATTERN)
    private Date vigenciaInicio;
    @JsonFormat(pattern=Constantes.DATE_PATTERN)
    private Date vigenciaFim;
    private String placaVeiculo;
    private BigDecimal valor;
    private ClienteDTO cliente;
    private String situacao ;
    


    public static Apolice create(ApoliceConsultaDTO apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, Apolice.class);
    }

    public static ApoliceConsultaDTO create(Apolice apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, ApoliceConsultaDTO.class);
    }
}
