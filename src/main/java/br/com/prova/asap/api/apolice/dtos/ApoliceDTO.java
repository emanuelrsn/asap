/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.dtos;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.apolice.models.Apolice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Service
public class ApoliceDTO extends ADTO<Apolice,ApoliceDTO>{
    
    @JsonIgnore
    private Integer idApolice;
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
}
