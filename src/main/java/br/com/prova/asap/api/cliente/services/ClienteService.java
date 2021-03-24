/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.services;

import br.com.prova.asap.api.abstracts.AService;
import br.com.prova.asap.api.cliente.models.Cliente;
import br.com.prova.asap.api.cliente.models.ClienteSequence;
import br.com.prova.asap.api.cliente.repositorys.ClienteRepository;
import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
import br.com.prova.asap.api.apolice.util.Util;
import br.com.prova.asap.api.infra.exception.GenericException;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Service
public class ClienteService extends AService<Cliente, ClienteDTO, ClienteSequence> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository mongoRepository, ClienteDTO t, Cliente d, ClienteSequence s) {
        super(mongoRepository, t, d, s);
    }

    public void isCpfValido(String cpf) {
        if (!Util.isCPF(cpf)) {
            throw new GenericException("O CPF informado é inválido.");
        }
    }

    @Override
    public void validationsBeforeInsert(ClienteDTO cliente) {
        isCpfValido(cliente.getCpf());
        Cliente usu = clienteRepository.findByCpf(cliente.getCpf()).orElse(null);
        if (Objects.nonNull(usu)) {
            throw new GenericException("Já existe um cliente com este CPF.");
        }
    }

    @Override
    public void validationsBeforeUpdate(ClienteDTO t) {
    }
}
