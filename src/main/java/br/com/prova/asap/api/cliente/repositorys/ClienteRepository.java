/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.repositorys;

import br.com.prova.asap.api.cliente.models.Cliente;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Santana
 */
public  interface ClienteRepository extends MongoRepository<Cliente, Integer> {
    public Optional<Cliente> findByCpf(String cpf);
}
