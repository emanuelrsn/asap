/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.models;

import br.com.prova.asap.api.abstracts.ADataBaseSequence;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Document(collection = "apolice")
@Service
public class ApoliceSequence extends ADataBaseSequence {
    public ApoliceSequence(){
        SEQUENCE_NAME = "apolice_sequence";
    }
}