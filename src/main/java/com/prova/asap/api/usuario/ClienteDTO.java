/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Santana
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    
    private int idCliente;
    private String nome;
    private String cpf;
    private String cidade;
    private String uf;


    public static Cliente create(ClienteDTO usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, Cliente.class);
    }

    public static ClienteDTO create(Cliente usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, ClienteDTO.class);
    }
}
