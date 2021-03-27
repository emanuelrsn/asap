/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.dtos;

import br.com.prova.asap.api.util.Util;
import com.fasterxml.jackson.annotation.JsonInclude;
import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
import lombok.Data;
/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApoliceConsultaDTO extends ApoliceDTO {

    private String numero;
    private String situacao ;
    private ClienteDTO cliente;

    public String getSituacao(){
        Long dias = Util.diferencaDias(this.getVigenciaFim());
        return dias<0 ? "Apólice vencida há: " + (dias*-1) + " dias" : "Apólice válida por: "+dias+" dias";
    }

}
