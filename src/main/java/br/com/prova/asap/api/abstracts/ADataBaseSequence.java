/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.abstracts;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santana
 */
@Data
public abstract class ADataBaseSequence {
    
    @Id
    @Field("_id")
    private String id;

    private int seq;

    @Transient
    public String SEQUENCE_NAME;
    
}
