/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

import br.com.prova.asap.api.apolice.util.GenerateSequence;
import br.com.prova.asap.api.apolice.util.Util;
import com.prova.asap.api.infra.exception.GenericException;
import com.prova.asap.api.infra.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Service
public class ClienteService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ClienteRepository clienteRepository;
    
    
    public void isCpfValido(String cpf){
        if(!Util.isCPF(cpf)){
            throw new GenericException("O CPF informado é inválido.");
        }
    }
    

    public ClienteDTO insert(ClienteDTO clienteDto) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        
        isCpfValido(clienteDto.getCpf());
        
        Cliente usu = clienteRepository.findByCpf(clienteDto.getCpf()).orElse(null);
        if (Objects.nonNull(usu)) {
            throw new GenericException("Já existe um cliente com este CPF.");
        }
        
        GenerateSequence<ClienteSequence, MongoOperations>
                seq=new GenerateSequence<>(new ClienteSequence(), mongoOperations);

        Integer idGerado = seq.generateSequence(Cliente.SEQUENCE_NAME);
        
        Cliente cliente = ClienteDTO.create(clienteDto);
        cliente.setId(String.valueOf(idGerado));
        ClienteDTO clientereturn = ClienteDTO.create(clienteRepository.save(cliente));
        return clientereturn;

    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO update(Integer id, ClienteDTO clienteDto) {

        isCpfValido(clienteDto.getCpf());
        
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ObjectNotFoundException("Cliente não encontrado.");
        }
        Cliente bd = optional.get();

        bd.setCidade(clienteDto.getCidade());
        bd.setCpf(clienteDto.getCpf());
        bd.setNome(clienteDto.getNome());
        bd.setUf(clienteDto.getUf());
        return ClienteDTO.create(clienteRepository.save(bd));
    }

    public ClienteDTO getClientesById(Integer id) {
        return clienteRepository.findById(id).map(ClienteDTO::create)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));

    }

    public List<ClienteDTO> getClientes() {
        
        List<ClienteDTO> lista = clienteRepository.findAll().stream()
                .map(f -> ClienteDTO.create(f)).collect(Collectors.toList());
        if(!lista.isEmpty()){
            lista.remove(0);
        }
        return lista;
    }
}
