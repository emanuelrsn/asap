package br.com.prova.asap.api.interfaces;

import br.com.prova.asap.api.abstracts.ADTO;
import br.com.prova.asap.api.abstracts.ADataBaseSequence;
import br.com.prova.asap.api.abstracts.AModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ISerice <D extends AModel, T extends ADTO, S extends ADataBaseSequence>{
    String generateSequence();

    T insert(T object);

    @Transactional
    T insert(D object) ;

    Object update(Integer id, ADTO object) ;

    boolean delete(Integer id) ;

    T getById(Integer id) ;

    D getEntityById(Integer id);

    List<T> getAll() ;

    abstract void validationsBeforeInsert(T t);

    abstract void validationsBeforeUpdate(T t);
}
