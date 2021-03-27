package br.com.prova.asap.api.abstracts;

import br.com.prova.asap.api.interfaces.IController;
import br.com.prova.asap.api.util.Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

public abstract class AController <Service extends AService, Dto extends ADTO>
implements IController<AService,ADTO> {

    protected Service service;
    protected Dto dto;
    public AController(Service service, Dto dto){
        this.service = service;
        this.dto=dto;
    }

    @ApiOperation(value = "Retorna uma lista")
    @GetMapping
    public ResponseEntity find() {
        return ResponseEntity.ok(service.getAll());
    }

    @ApiOperation(value = "Retorna um registro por ID")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    public ResponseEntity post(@RequestBody @Valid ADTO object){
        ADTO f = service.insert((Dto)object);
        URI location = Util.getUri(Integer.parseInt(f.getId()), "id");
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id, @RequestBody ADTO object) {
        return ResponseEntity.ok(service.update(id,(Dto) object));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Integer id) {

        service.delete(id);
        return ResponseEntity.ok().build();

    }
}


