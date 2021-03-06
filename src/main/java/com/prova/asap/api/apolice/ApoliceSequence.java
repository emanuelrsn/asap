/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import br.com.prova.asap.api.apolice.interfaces.DataBaseSequence;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Santana
 */
@Document(collection = "apolice")
public class ApoliceSequence extends DataBaseSequence{

}