/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApoliceDTO {
    
    @NotNull(message = "A data de inicio da vigigencia é obrigatório.")
    private Date vigenciaInicio;
    @NotNull(message = "A data final da vigigencia é obrigatório.")
    private Date vigenciaFim;
    @NotBlank(message = "A placa do veiculo é obrigatório.")
    private String placaVeiculo;
    @NotNull(message = "O valor é obrigatório.")
    private BigDecimal valor;
    @NotNull(message = "O código do cliente é obrigatório.")
    private Integer idCliente;
    
    @JsonIgnore
    public static Apolice create(ApoliceDTO apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, Apolice.class);
    }

    @JsonIgnore
    public static ApoliceDTO create(Apolice apolice) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(apolice, ApoliceDTO.class);
    }
}
