/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.apolice;

import com.prova.asap.api.usuario.*;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
//    
//    @GetMapping("/{cpf}")
//    public ResponseEntity getByCpf(@PathVariable String cpf) {
//        return ResponseEntity.ok(service.getUsuariosByCpf(cpf));
//    }

    @PostMapping
    public ResponseEntity post(@RequestBody ApoliceDTO apolice) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       
        ApoliceDTO f = service.insert(apolice);
        return ResponseEntity.ok().build();
        
//        URI location = getUri(f.getCpf());
//        return ResponseEntity.created(location).build() ;

    }
    
//    @PutMapping("/{cpf}")
//    public ResponseEntity put(@PathVariable String cpf,@RequestBody UsuarioDTO usuario) {
//
//        UsuarioDTO f = service.update(cpf, usuario);
//        return ResponseEntity.ok(f);
//
//    }
//    
//    @DeleteMapping("/{cpf}")
//    public ResponseEntity delete(@PathVariable String cpf) {
//
//        service.delete(cpf);
//        return ResponseEntity.ok().build();
//
//    }
//    
//    private URI getUri(String cpf) {
//        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}")
//                .buildAndExpand(cpf).toUri();
//    }
}
