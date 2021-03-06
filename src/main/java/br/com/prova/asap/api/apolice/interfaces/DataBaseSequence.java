/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.interfaces;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Santana
 */
@Data
public class DataBaseSequence {
    
    @Id
    private String id;

    private int seq;
    
}
