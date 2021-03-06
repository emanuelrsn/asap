/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

import com.prova.asap.api.infra.exception.GenericException;
import com.prova.asap.api.infra.exception.ObjectNotFoundException;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
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

    public int generateSequence(String seqName) {
        ClienteSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                ClienteSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public ClienteDTO insert(ClienteDTO clienteDto) {

        Cliente usu = clienteRepository.findById(clienteDto.getIdCliente()).orElse(null);
        if (Objects.nonNull(usu)) {
            throw new GenericException("Já existe um cliente com este CPF.");
        }

        Cliente cliente = ClienteDTO.create(clienteDto);
        cliente.setId(String.valueOf(generateSequence(Cliente.SEQUENCE_NAME)));
        ClienteDTO clientereturn = ClienteDTO.create(clienteRepository.save(cliente));
        return clientereturn;

    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO update(Integer id, ClienteDTO clienteDto) {

        Optional<Cliente> optional = clienteRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ObjectNotFoundException("Cliente não encontrado.");
        }
        Cliente bd = optional.get();

        bd.setCidade(clienteDto.getCidade());
        bd.setCpf(clienteDto.getCpf());
        bd.setNome(clienteDto.getNome());
        bd.setUf(clienteDto.getUf());

        clienteRepository.save(bd);

        return ClienteDTO.create(bd);
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
