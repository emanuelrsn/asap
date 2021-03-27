/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api.apolice.controllers;

import br.com.prova.asap.api.abstracts.AController;
import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.apolice.dtos.ApoliceDTO;
import br.com.prova.asap.api.apolice.services.ApoliceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        super(service, apoliceDTO);
    }

    @ApiOperation(value = "Retorna uma lista de apólices")
    @Override
    public ResponseEntity find() {
        return super.find();
    }

    @ApiOperation(value = "Retorna uma apólice por ID")
    @Override
    public ResponseEntity findById(@PathVariable Integer id) {
        return super.findById(id);
    }

    @ApiOperation(value = "Retorna uma apólice por Número")
    @GetMapping("/ApolicePorNumero/{numero}")
    public ResponseEntity findByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(this.service.getApoliceByNumero(numero));
    }

    @ApiOperation(value = "Incluir uma nova apólice")
    @Override
    public ResponseEntity post(@RequestBody @Valid ApoliceDTO apolice){
        return super.post(apolice);
    }

    @ApiOperation(value = "Alterar uma apólice")
    @Override
    public ResponseEntity put(@PathVariable Integer id, @RequestBody @Valid ApoliceDTO apolice) {
        return super.put(id,apolice);
    }

    @ApiOperation(value = "Deletar uma apólice")
    @Override
    public ResponseEntity remove(@PathVariable Integer id) {
        return super.remove(id);
    }
}
