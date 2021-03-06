/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import br.com.prova.asap.api.apolice.util.Util;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Santana
 */
@RestController
@RequestMapping("/api/v1/apolice")
public class ApoliceController {

    @Autowired
    ApoliceService service;

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getApolices());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getApolicesById(id));
    }
    
    @GetMapping("/ApolicePorNumero/{numero}")
    public ResponseEntity getById(@PathVariable String numero) {
        return ResponseEntity.ok(service.getApolicesByNumero(numero));
    }
    @PostMapping
    public ResponseEntity post(@RequestBody @Valid ApoliceDTO apolice) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ApoliceConsultaDTO f = service.insert(apolice);
        URI location = Util.getUri(f.getIdApolice(),"id");
        return ResponseEntity.created(location).build() ;

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id,@RequestBody @Valid ApoliceDTO apolice) {

        ApoliceConsultaDTO f = service.update(id, apolice);
        return ResponseEntity.ok(f);

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        service.delete(id);
        return ResponseEntity.ok().build();

    }
}
