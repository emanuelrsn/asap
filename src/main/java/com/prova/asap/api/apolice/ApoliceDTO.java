/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ApoliceDTO {
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date vigencia_inicio;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date vigencia_fim;
    private String placa_veiculo;
    private BigDecimal valor;
    private Integer idCliente;


    public static Apolice create(ApoliceDTO apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, Apolice.class);
    }

    public static ApoliceDTO create(Apolice apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, ApoliceDTO.class);
    }
}
