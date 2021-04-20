/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.controllers;

import br.com.prova.asap.api.abstracts.AController;
import br.com.prova.asap.api.apolice.dtos.ApoliceDTO;
import br.com.prova.asap.api.apolice.services.ApoliceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Santana
 */
@RestController
@RequestMapping("/api/v1/apolice")
@Api(value = "/api/v1/apolice", description = "Operações da Apólice", consumes="application/json")
public class ApoliceController extends AController<ApoliceService, ApoliceDTO> {

    @Autowired
    public ApoliceController(ApoliceService service, ApoliceDTO apoliceDTO) {
        super(service);
    }

    @ApiOperation(value = "Retorna uma apólice por Número")
    @GetMapping("/ApolicePorNumero/{numero}")
    public ResponseEntity findByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(this.service.getApoliceByNumero(numero));
    }



}
