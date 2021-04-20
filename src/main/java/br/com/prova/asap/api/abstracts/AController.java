package br.com.prova.asap.api.abstracts;

import br.com.prova.asap.api.interfaces.IController;
import br.com.prova.asap.api.util.Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

public abstract class AController <Service extends AService, Dto extends ADTO>
        implements IController<Service, Dto> {

    protected Service service;
    public AController(Service service){
        this.service = service;
    }

    @ApiOperation(value = "Retornar uma lista de registros")
    @GetMapping
    public ResponseEntity find() {
        return ResponseEntity.ok(service.getAll());
    }

    @ApiOperation(value = "Retornar um registro por ID")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @ApiOperation(value = "Incluir um novo registro")
    @PostMapping
    public ResponseEntity post(@RequestBody @Valid Dto object){
        ADTO f = service.insert((Dto)object);
        URI location = Util.getUri(Integer.parseInt(f.getId()), "id");
        return ResponseEntity.created(location).build();

    }

    @ApiOperation(value = "Atualizar um registro")
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Integer id,@RequestBody @Valid Dto object) {
        return ResponseEntity.ok(service.update(id,(Dto) object));

    }

    @ApiOperation(value = "Remover um registro")
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}


