/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.cliente.controllers;

import br.com.prova.asap.api.abstracts.AController;
import br.com.prova.asap.api.cliente.dtos.ClienteDTO;
import br.com.prova.asap.api.util.Util;
import br.com.prova.asap.api.cliente.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/cliente")
@Api(value = "/api/v1/cliente", description = "Operações do Cliente", consumes="application/json")
public class ClienteController  extends AController<ClienteService, ClienteDTO>  {

    @Autowired
    public ClienteController(ClienteService service, ClienteDTO clienteDTO){
        super(service, clienteDTO);
    }

    @ApiOperation(value = "Retorna uma lista de cliente")
    @Override
    public ResponseEntity find() {
        return super.find();
    }
    
    @ApiOperation(value = "Retorna uma lista de cliente por ID")
    @Override
    public ResponseEntity findById(@PathVariable Integer id) {
        return super.findById(id);
    }

    @ApiOperation(value = "Incluir um novo cliente")
    @Override
    public ResponseEntity post(@RequestBody @Valid ClienteDTO cliente) {
        return super.post(cliente);
    }
    
    @ApiOperation(value = "Alterar um cliente")
    @Override
    public ResponseEntity put(@PathVariable Integer id, @Valid @RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok(service.update(id, cliente));
    }
    
    @ApiOperation(value = "Deletar um cliente")
    @Override
    public ResponseEntity remove(@PathVariable Integer id) {
        return super.remove(id);

    }

}
