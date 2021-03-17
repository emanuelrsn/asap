/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.dtos;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.apolice.models.Apolice;
import br.com.prova.asap.api.apolice.util.Constantes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApoliceConsultaDTO extends ADTO<Apolice, ApoliceConsultaDTO>{
    
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
    


}
