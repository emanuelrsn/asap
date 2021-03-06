/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.asap.api.usuario;

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
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getClientes());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getClientesById(id));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ClienteDTO cliente) {
       
        ClienteDTO f = service.insert(cliente);
        
        URI location = getUri(f.getIdCliente());
        return ResponseEntity.created(location).build() ;

    }
    
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id,@RequestBody ClienteDTO cliente) {

        ClienteDTO f = service.update(id, cliente);
        return ResponseEntity.ok(f);

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        service.delete(id);
        return ResponseEntity.ok().build();

    }
    
    private URI getUri(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
