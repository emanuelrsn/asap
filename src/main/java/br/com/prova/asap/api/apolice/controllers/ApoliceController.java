/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.controllers;

import br.com.prova.asap.api.apolice.dtos.ApoliceDTO;
import br.com.prova.asap.api.apolice.dtos.ApoliceConsultaDTO;
import br.com.prova.asap.api.apolice.services.ApoliceService;
import br.com.prova.asap.api.apolice.util.Util;
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
@RequestMapping("/api/v1/apolice")
public class ApoliceController {

    @Autowired
    ApoliceService service;

    @ApiOperation(value = "Retorna uma lista de apólices")
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getAll());
    }

    @ApiOperation(value = "Retorna uma apólice por ID")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) throws Throwable {
        return ResponseEntity.ok(service.getById(id));
    }

    @ApiOperation(value = "Retorna uma apólice por Número")
    @GetMapping("/ApolicePorNumero/{numero}")
    public ResponseEntity getById(@PathVariable String numero) {
        return ResponseEntity.ok(service.getApolicesByNumero(numero));
    }

    @ApiOperation(value = "Incluir uma nova apólice")
    @PostMapping
    public ResponseEntity post(@RequestBody ApoliceDTO apolice) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ApoliceDTO f = service.insert(apolice);
        URI location = Util.getUri(f.getIdApolice(), "id");
        return ResponseEntity.created(location).build();

    }

    @ApiOperation(value = "Alterar uma apólice")
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id, @RequestBody ApoliceDTO apolice) {
        return ResponseEntity.ok(service.update(id, apolice));

    }

    @ApiOperation(value = "Deletar uma apólice")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        service.delete(id);
        return ResponseEntity.ok().build();

    }
}
