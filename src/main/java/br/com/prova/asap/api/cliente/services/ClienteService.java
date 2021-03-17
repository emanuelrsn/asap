/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.services;

import br.com.prova.asap.api.abstracts.AService;
import br.com.prova.asap.api.cliente.models.Cliente;
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
public class ClienteService extends AService<Cliente, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository mongoRepository, ClienteDTO t, Cliente d) {
        super(mongoRepository, t, d);
    }

    public void isCpfValido(String cpf) {
        if (!Util.isCPF(cpf)) {
            throw new GenericException("O CPF informado é inválido.");
        }
    }

    @Override
    public ClienteDTO insert(ClienteDTO clienteDto) {
        Integer idGerado = this.generateSequence();
        Cliente cliente = clienteDto.create();
        cliente.setIdSequence(String.valueOf(idGerado));
        return super.insert(clienteDto);
    }

    @Override
    public void validationsBeforeInsert(ClienteDTO cliente) {
        isCpfValido(cliente.getCpf());
        Cliente usu = clienteRepository.findByCpf(cliente.getCpf()).orElse(null);
        if (Objects.nonNull(usu)) {
            throw new GenericException("Já existe um cliente com este CPF.");
        }
    }

//    public void delete(Integer id) {
//        clienteRepository.deleteById(id);
//    }
//    public ClienteDTO update(Integer id, ClienteDTO clienteDto) {
//
//        isCpfValido(clienteDto.getCpf());
//        
//        Optional<Cliente> optional = clienteRepository.findById(id);
//        if (!optional.isPresent()) {
//            throw new ObjectNotFoundException("Cliente não encontrado.");
//        }
//        Cliente bd = optional.get();
//
//        bd.setCidade(clienteDto.getCidade());
//        bd.setCpf(clienteDto.getCpf());
//        bd.setNome(clienteDto.getNome());
//        bd.setUf(clienteDto.getUf());
//        return ClienteDTO.create(clienteRepository.save(bd));
//    }
//    public ClienteDTO getClientesById(Integer id) {
//        return clienteRepository.findById(id).map(ClienteDTO::create)
//                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
//
//    }
//    public List<ClienteDTO> getClientes() {
//        
//        List<ClienteDTO> lista = clienteRepository.findAll().stream()
//                .map(f -> ClienteDTO.create(f)).collect(Collectors.toList());
//        if(!lista.isEmpty()){
//            lista.remove(0);
//        }
//        return lista;
//    }
    @Override
    public void validationsBeforeUpdate(ClienteDTO t) {
    }
}
