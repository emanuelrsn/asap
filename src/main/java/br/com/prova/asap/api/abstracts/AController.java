package br.com.prova.asap.api.abstracts;

import br.com.prova.asap.api.interfaces.IController;
import br.com.prova.asap.api.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

public abstract class AController <Service extends AService, Dto extends ADTO>
        implements IController<Service, Dto> {

    protected Service service;
    protected Dto dto;
    public AController(Service service, Dto dto){
        this.service = service;
        this.dto=dto;
    }

    @GetMapping
    public ResponseEntity find() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @PostMapping
    public ResponseEntity post(Dto object){
        ADTO f = service.insert((Dto)object);
        URI location = Util.getUri(Integer.parseInt(f.getId()), "id");
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity put(Integer id,Dto object) {
        return ResponseEntity.ok(service.update(id,(Dto) object));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}


