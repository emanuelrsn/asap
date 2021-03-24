/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.models;

import br.com.prova.asap.api.abstracts.ADataBaseSequence;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Document(collection = "cliente")
@Service
public class ClienteSequence extends ADataBaseSequence {

    public ClienteSequence(){
        SEQUENCE_NAME = "cliente_sequence";
    }


}