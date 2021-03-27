/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.dtos;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.cliente.models.Cliente;
import br.com.prova.asap.api.infra.exception.GenericException;
import br.com.prova.asap.api.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Service
public class ClienteDTO extends ADTO<Cliente,ClienteDTO> {


    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;
    @NotBlank(message = "A cidade é obrigatório.")
    private String cidade;
    @NotBlank(message = "A UF é obrigatório.")
    private String uf;
  
}
