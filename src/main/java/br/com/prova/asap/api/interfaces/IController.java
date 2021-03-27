package br.com.prova.asap.api.interfaces;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.abstracts.AService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IController  <Service extends AService, Dto extends ADTO>{
    @ApiOperation(value = "Retorna uma lista")
    @GetMapping
    public ResponseEntity find() ;

    @ApiOperation(value = "Retorna um registro por ID")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) ;

    @ApiOperation(value = "Incluir um novo regitro")
    public ResponseEntity post(@RequestBody @Valid ADTO dto);

    @ApiOperation(value = "Alterar um registro")
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id, @RequestBody ADTO dto) ;

    @ApiOperation(value = "Deletar uma registro")
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Integer id) ;
}
