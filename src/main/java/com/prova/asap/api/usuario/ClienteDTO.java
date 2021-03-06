/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    
    @JsonIgnore
    private int idCliente;
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;
    @NotBlank(message = "A cidade é obrigatório.")
    private String cidade;
    @NotBlank(message = "A UF é obrigatório.")
    private String uf;

    @JsonIgnore
    public static Cliente create(ClienteDTO usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, Cliente.class);
    }

    @JsonIgnore
    public static ClienteDTO create(Cliente usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, ClienteDTO.class);
    }
}
