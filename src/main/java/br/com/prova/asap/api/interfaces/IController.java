package br.com.prova.asap.api.interfaces;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.abstracts.AService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IController  <Service extends AService, Dto extends ADTO>{

    public ResponseEntity find() ;


    public ResponseEntity findById(Integer id) ;


    public ResponseEntity post(Dto dto);

    public ResponseEntity put(Integer id, Dto object);


    public ResponseEntity remove(Integer id) ;
}
