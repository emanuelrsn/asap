/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.repositorys;

import br.com.prova.asap.api.apolice.models.Apolice;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Santana
 */
public  interface ApoliceRepository extends MongoRepository<Apolice, Integer> {
    public Optional<Apolice> findByNumero(String numero);
}
