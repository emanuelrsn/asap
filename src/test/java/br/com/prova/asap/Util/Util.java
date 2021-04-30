
package br.com.prova.asap.Util;

import br.com.prova.asap.cliente.DTO.ClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;

@Component
public class Util {

    @Autowired
    protected TestRestTemplate rest;

    public ResponseEntity<?> get(String url, Class<?> classz){

        return rest.getForEntity(url,classz);
    }

    public ResponseEntity<List<?>> gets(String url){
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<?>>() {
                });
    }

    public ResponseEntity post(String url, HttpEntity dto,
                               Class<?> classz){
        return rest.exchange(
                url,
                HttpMethod.POST,
                dto ,
                classz);
    }
}

